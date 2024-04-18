package com.boldbit.bitharvester.Harvester.compiler.Analyzer;

import java.util.ArrayList;
import java.util.HashMap;

import com.boldbit.bitharvester.Harvester.compiler.token.IdentifierToken;
import com.boldbit.bitharvester.Harvester.compiler.trees.ClassDeclarationTree;
import com.boldbit.bitharvester.Harvester.compiler.trees.ClassElements;
import com.boldbit.bitharvester.Harvester.compiler.trees.ConstructorDeclarationTree;
import com.boldbit.bitharvester.Harvester.compiler.trees.ImportDeclarationsTree;
import com.boldbit.bitharvester.Harvester.compiler.trees.MethodCallTree;
import com.boldbit.bitharvester.Harvester.compiler.trees.MethodCallsListTree;
import com.boldbit.bitharvester.Harvester.compiler.trees.MethodDeclarationTree;
import com.boldbit.bitharvester.Harvester.compiler.trees.ObjectDeclarationTree;
import com.boldbit.bitharvester.Harvester.compiler.trees.PackageDeclarationTree;
import com.boldbit.bitharvester.Harvester.compiler.trees.ParseTree;
import com.boldbit.bitharvester.Harvester.compiler.trees.ParseTreeType;
import com.boldbit.bitharvester.Harvester.compiler.trees.ProgramTree;
import com.boldbit.bitharvester.Harvester.compiler.trees.VariableDeclarationTree;

public class Analyzer {

    public String fileName;
    public String fileSize;

    public ProgramData processProgramTree(ProgramTree programTree) {
        int linesCode = programTree.treeLocation.end.line + 1;
        String packageName = "";
        int imports = 0, classCount = 0, totalVariables = 0, totalMethods = 0, totalMethodCalls = 0,
                totalObjectCalls = 0;
        ArrayList<ClassInfo> classes = new ArrayList<>();

        if (programTree.sourceElements.get(0).parseTreeType == ParseTreeType.PACKAGE_DECLARARTION) {
            packageName = processPackageName(programTree.sourceElements.get(0));
        }

        int i = 0;
        while (i < programTree.sourceElements.size()) {
            if (programTree.sourceElements.get(i).parseTreeType == ParseTreeType.IMPORT_DECLARATION) {
                ImportDeclarationsTree im = (ImportDeclarationsTree) programTree.sourceElements.get(i);
                imports = processImports(im.importDeclarationsTrees);
            }
            if (programTree.sourceElements.get(i).parseTreeType == ParseTreeType.CLASS_DECLARATION) {
                classCount++;
                ClassDeclarationTree classDeclarationTree = (ClassDeclarationTree) programTree.sourceElements
                        .get(i);
                classes.add(processClassDeclaration(classDeclarationTree));
                ClassElements ce = (ClassElements) classDeclarationTree.classElements;

                totalVariables += ce.variablesListTree.variablesList.size();
                totalMethods += ce.methodsListTree.methodsList.size();
                ArrayList<ParseTree> mcs = ce.callsListTree.methodCallsList;
                int j = 0;
                while (j < mcs.size()) {
                    MethodCallsListTree a = (MethodCallsListTree) mcs.get(j);
                    totalMethodCalls += a.methodCallsInExpression.size();
                    j++;
                }
            }
            i++;
        }

        return new ProgramData(fileName, fileSize, linesCode, packageName, imports, classCount, totalVariables,
                totalMethods,
                totalMethodCalls, totalObjectCalls, classes);
    }

    private ClassInfo processClassDeclaration(ClassDeclarationTree classDeclarationTree) {
        ClassElements classElements = (ClassElements) classDeclarationTree.classElements;

        String className = classDeclarationTree.className.value;
        String superClassName = null;
        if (classDeclarationTree.superClass != null) {
            superClassName = classDeclarationTree.superClass.value;
        }
        int linesOfCode = classDeclarationTree.treeLocation.end.line - classDeclarationTree.treeLocation.start.line + 1;
        int classes = 0; // TODO:
        ArrayList<String> variablesList = processVariables(classElements.variablesListTree.variablesList);
        HashMap<Integer, String> methods = processMethods(classElements.methodsListTree.methodsList);
        HashMap<Integer, ArrayList<String>> methodCalls = processMethodCalls(
                classElements.callsListTree.methodCallsList);
        HashMap<String, Integer> objectCalls = new HashMap<>();

        return new ClassInfo(className, superClassName, linesOfCode, classes, variablesList, methods, methodCalls,
                objectCalls);
    }

    private HashMap<Integer, ArrayList<String>> processMethodCalls(ArrayList<ParseTree> methodCallsList) {
        HashMap<Integer, ArrayList<String>> map = new HashMap<>();
        for (ParseTree parseTree : methodCallsList) {
            MethodCallsListTree methodCallsListTree = (MethodCallsListTree) parseTree;
            ArrayList<ParseTree> methodCallsInExpression = methodCallsListTree.methodCallsInExpression;
            for (ParseTree mc : methodCallsInExpression) {
                if (mc.parseTreeType == ParseTreeType.METHOD_CALL) {
                    MethodCallTree methodCallTree = (MethodCallTree) mc;
                    ArrayList<String> list = new ArrayList<>();
                    if (map.containsKey(methodCallTree.treeLocation.start.line + 1)) {
                        list = map.get(methodCallTree.treeLocation.start.line + 1);
                        list.add(methodCallTree.methodIdentifier.toString());
                        map.put(methodCallTree.treeLocation.start.line + 1, list);
                    } else {
                        list.add(methodCallTree.methodIdentifier.toString());
                        map.put(methodCallTree.treeLocation.start.line + 1, list);
                    }
                }
            }
        }
        return map;
    }

    private HashMap<Integer, String> processMethods(ArrayList<ParseTree> methodsList) {
        HashMap<Integer, String> map = new HashMap<>();
        for (ParseTree parseTree : methodsList) {
            if (parseTree.parseTreeType == ParseTreeType.METHOD_DECLARATION) {
                MethodDeclarationTree methodDeclarationTree = (MethodDeclarationTree) parseTree;
                String returnType = methodDeclarationTree.methodSignatureTree.returnType.tokenType.name();
                map.put(methodDeclarationTree.treeLocation.start.line + 1,
                        methodDeclarationTree.methodSignatureTree.methodName.value);
            } else if (parseTree.parseTreeType == ParseTreeType.CONSTRUCTOR_STATEMENT) {
                ConstructorDeclarationTree constructorDeclarationTree = (ConstructorDeclarationTree) parseTree;
                // map.put("void", constructorDeclarationTree.name.value);
                map.put(constructorDeclarationTree.treeLocation.start.line + 1, constructorDeclarationTree.name.value);
            }
        }
        return map;
    }

    private ArrayList<String> processVariables(ArrayList<ParseTree> variablesList) {
        ArrayList<String> list = new ArrayList<>();
        for (ParseTree parseTree : variablesList) {
            if (parseTree.parseTreeType == ParseTreeType.FIELD_DECLARATION) {
                VariableDeclarationTree variableDeclarationTree = (VariableDeclarationTree) parseTree;
                list.add(variableDeclarationTree.name.value);
            } else if (parseTree.parseTreeType == ParseTreeType.OBJECT_DECLARATION) {
                ObjectDeclarationTree objectDeclarationTree = (ObjectDeclarationTree) parseTree;
                list.add(objectDeclarationTree.name.value);
            }
        }
        return list;
    }

    private int processImports(ArrayList<ParseTree> importDeclarationsTrees) {
        return importDeclarationsTrees.size();
    }

    public String processPackageName(ParseTree parseTree) {
        PackageDeclarationTree packageDeclarationTree = (PackageDeclarationTree) parseTree;
        String packageName = "";

        int j = 0;
        while (j < packageDeclarationTree.qualifiedName.size()) {
            IdentifierToken token = (IdentifierToken) packageDeclarationTree.qualifiedName.get(j);
            packageName += "/" + token.value;
            j++;
        }

        return packageName;
    }

}