package com.boldbit.bitharvester.Harvester.ByteCodeParser.Parser;

import java.io.*;

public class JavaToBytecode {
    public static void main(String[] args) {
        String javaFilePath = "/Users/cavisson/Documents/Projects/bitharvester/bitharvester_backend/bitharvester/src/main/java/com/boldbit/bitharvester/Harvester/Extractor/Fields/Data/FieldsData.java";

        String className = javaFilePath.substring(0, javaFilePath.lastIndexOf('.'));
        String bytecodeFilePath = className + ".txt";

        compile(javaFilePath);
        disassemble(className, bytecodeFilePath);
        System.out.println("Bytecode saved to: " + bytecodeFilePath);
    }

    private static void compile(String javaFilePath) {
        try {
            ProcessBuilder builder = new ProcessBuilder("javac", javaFilePath);
            Process process = builder.inheritIO().start();
            int exitCode = process.waitFor();
            if (exitCode != 0) {
                System.err.println("Compilation failed!");
                System.exit(1);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    private static void disassemble(String className, String bytecodeFilePath) {
        try {
            ProcessBuilder builder = new ProcessBuilder("javap", "-v", className);
            Process process = builder.redirectOutput(ProcessBuilder.Redirect.to(new File(bytecodeFilePath))).start();
            int exitCode = process.waitFor();
            if (exitCode != 0) {
                System.err.println("Disassembly failed!");
                System.exit(1);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}

