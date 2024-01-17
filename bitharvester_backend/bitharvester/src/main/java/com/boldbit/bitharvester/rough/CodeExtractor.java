package com.boldbit.bitharvester.rough;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CodeExtractor {

    public static void extract() {
        try {
            // Specify the path to your Java file
            String filePath = "/Users/cavisson/Documents/Projects/bitharvester/bitharvester_backend/bitharvester/src/main/java/com/boldbit/bitharvester/assets/DataWarehouse.java";

            // Read the Java file
            List<String> lines = readLines(filePath);

            // Extract information
            extractInformation(lines);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static List<String> readLines(String filePath) throws IOException {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        }
        return lines;
    }

    private static void extractInformation(List<String> lines) {
        for (String line : lines) {
            // Extract class names
            extractClassNames(line);

            // Extract method names and arguments
            extractMethods(line);

            // Extract variable names
            extractVariables(line);

            // Extract method calls
            extractMethodCalls(line);
        }
    }

    private static void extractClassNames(String line) {
        Pattern classPattern = Pattern.compile("class\\s+(\\w+)");
        Matcher matcher = classPattern.matcher(line);
        while (matcher.find()) {
            System.out.println("Class Name: " + matcher.group(1));
        }
    }

    private static void extractMethods(String line) {
        Pattern methodPattern = Pattern.compile("\\b(\\w+)\\s+(\\w+)\\s*\\(([^)]*)\\)\\s*\\{");
        Matcher matcher = methodPattern.matcher(line);
        while (matcher.find()) {
            String returnType = matcher.group(1);
            String methodName = matcher.group(2);
            String arguments = matcher.group(3);
            System.out.println("Method Name: " + methodName);
            System.out.println("Return Type: " + returnType);
            System.out.println("Arguments: " + arguments);
        }
    }

    private static void extractVariables(String line) {
        Pattern variablePattern = Pattern.compile("\\b(\\w+)\\s+(\\w+)\\s*=");
        Matcher matcher = variablePattern.matcher(line);
        while (matcher.find()) {
            System.out.println("Variable Name: " + matcher.group(2));
        }
    }

    private static void extractMethodCalls(String line) {
        Pattern methodCallPattern = Pattern.compile("(\\w+)\\s*\\(");
        Matcher matcher = methodCallPattern.matcher(line);
        while (matcher.find()) {
            System.out.println("Method Call: " + matcher.group(1));
        }
    }
}
