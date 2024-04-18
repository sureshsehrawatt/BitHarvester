package com.boldbit.bitharvester.Harvester.compiler.Analyzer;

import java.util.ArrayList;
import java.util.HashMap;

public class ClassInfo {
    public final String className;
    public final String superClassName;
    public final int linesCode;
    public final int classes;
    public final ArrayList<String> variables;
    public final HashMap<Integer, String> methods;
    public final HashMap<Integer, ArrayList<String>> methodCalls;
    public final HashMap<String, Integer> objectCalls;

    public ClassInfo(String className, String superClassName, int linesCode, int classes,
            ArrayList<String> variablesList, HashMap<Integer, String> methods,
            HashMap<Integer, ArrayList<String>> methodCalls, HashMap<String, Integer> objectCalls) {
        this.className = className;
        this.superClassName = superClassName;
        this.linesCode = linesCode;
        this.classes = classes;
        this.variables = variablesList;
        this.methods = methods;
        this.methodCalls = methodCalls;
        this.objectCalls = objectCalls;
    }
}
