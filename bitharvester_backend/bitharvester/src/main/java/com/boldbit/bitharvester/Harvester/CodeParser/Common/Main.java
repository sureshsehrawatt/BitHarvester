package com.boldbit.bitharvester.Harvester.CodeParser.Common;

import com.boldbit.bitharvester.Harvester.CodeParser.Parsers.ClassInfo.ClassInfo;

public class Main {
    public static void main(String[] args) {
        String filePath = "/Users/cavisson/Documents/Projects/bitharvester/bitharvester_backend/bitharvester/src/main/java/com/boldbit/bitharvester/rough/Demo.java";
        try {
            ClassInfo.filePath = filePath;
            ClassInfo.mainMethod();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
