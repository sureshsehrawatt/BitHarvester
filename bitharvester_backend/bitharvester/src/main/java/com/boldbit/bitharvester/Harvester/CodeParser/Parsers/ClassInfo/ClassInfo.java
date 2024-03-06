package com.boldbit.bitharvester.Harvester.CodeParser.Parsers.ClassInfo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ClassInfo {
    public static String filePath = "";

    public static Map<String, String> mainMethod() throws Exception {
        Map<String, String> classInfo = new HashMap<>();
        classInfo.put("packageName", ClassInfo.getPackageName(getFileBufferedReader()));
        classInfo.put("publicClassName", ClassInfo.getPublicClassName(getFileBufferedReader()));
        classInfo.put("classModifiers", ClassInfo.getPublicClassModifiers(getFileBufferedReader(), classInfo.get("publicClassName")));

        System.out.println(classInfo.toString());
        return classInfo;
    }

    private static BufferedReader getFileBufferedReader() throws Exception {
        return new BufferedReader(new FileReader(filePath));
    }

    public static String getPackageName(BufferedReader file) {
        String line, packageName = "";
        try {
            while ((line = file.readLine()) != null) {
                if (line.startsWith("package ")) {
                    packageName = line.substring(8, line.lastIndexOf(';')).trim();
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return packageName;
    }
    public static String getPublicClassName(BufferedReader file) {
        String publicClassName = "";
        try {
            String line;
            while ((line = file.readLine()) != null) {
                line = line.trim(); // Trim leading and trailing whitespace
                if (line.startsWith("public class ") || line.startsWith("class ")) {
                    // Extract class name
                    int classNameStartIndex = line.indexOf("class") + "class".length();
                    int classNameEndIndex = line.indexOf("{");
                    publicClassName = line.substring(classNameStartIndex, classNameEndIndex).trim();
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return publicClassName;
    }

    public static String getPublicClassModifiers(BufferedReader file, String className) {
        int modifiers = 0;
        try {
            String line;
            while ((line = file.readLine()) != null) {
                if (line.trim().startsWith("public class " + className)
                        || line.trim().startsWith("class " + className)) {
                    String modifiersStr = line.substring(0, line.indexOf("class " + className)).trim();

                    if (modifiersStr.contains("public")) {
                        modifiers |= Modifier.PUBLIC;
                    }
                    if (modifiersStr.contains("abstract")) {
                        modifiers |= Modifier.ABSTRACT;
                    }
                    if (modifiersStr.contains("final")) {
                        modifiers |= Modifier.FINAL;
                    }

                    String modifierStr = Modifier.toString(modifiers);

                    System.out.println("Modifiers of " + className + ": " + modifierStr);
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Modifier.toString(modifiers);
    }

}
