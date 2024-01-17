package com.boldbit.bitharvester.Services.codeanalysis;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CodeExtractor {

    public String readJavaCode(String filePath) {
        String fileContent = "";

        try {
            fileContent = Files.readString(Paths.get(filePath));
            // System.out.println("File content stored in a variable:\n" + fileContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileContent;
    }

    public static List<String> extractClassNames(String javaCode) {
        List<String> classNames = new ArrayList<>();
        Pattern pattern = Pattern.compile("class\\s+([A-Za-z_$][A-Za-z\\d_$]*)\\s*\\{");
        Matcher matcher = pattern.matcher(javaCode);

        while (matcher.find()) {
            classNames.add(matcher.group(1));
        }
        return classNames.isEmpty() ? null : classNames;
    }

    public static List<String> extractMethodNames(String javaCode) {
        List<String> methodSignatures = new ArrayList<>();
        Pattern pattern = Pattern.compile("\\b([A-Za-z_$][A-Za-z\\d_$]*)\\s+([A-Za-z_$][A-Za-z\\d_$]*)\\(([^)]*)\\)\\s*\\{");
        Matcher matcher = pattern.matcher(javaCode);

        while (matcher.find()) {
            String methodName = matcher.group(2);
            String arguments = matcher.group(3);
            String methodSignature = methodName + "(" + arguments + ")";
            methodSignatures.add(methodSignature);
        }

        return methodSignatures.isEmpty() ? null : methodSignatures;
    }

    public static void main(String[] args) {
        String filePath = "/Users/cavisson/Documents/Projects/bitharvester/bitharvester_backend/bitharvester/src/main/java/com/boldbit/bitharvester/assets/DataWarehouse.java";

        CodeExtractor objCodeExtractor = new CodeExtractor();
        String content = objCodeExtractor.readJavaCode(filePath);

        List<String> className = CodeExtractor.extractClassNames(content);
        System.out.println("Class name is : " + className);

        List<String> methodName = CodeExtractor.extractMethodNames(content);
        System.out.println("Methods in code : " + methodName);

    }
}
