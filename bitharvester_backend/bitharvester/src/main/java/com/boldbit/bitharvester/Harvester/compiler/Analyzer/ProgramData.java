package com.boldbit.bitharvester.Harvester.compiler.Analyzer;

import java.util.ArrayList;

public class ProgramData {
    public final String fileName;
    public final String fileSize;
    public final String packageName;
    public final int totalLinesCode;
    public final int totalImports;
    public final int totalClasses;
    public final int totalVariables;
    public final int totalMethods;
    public final int totalMethodCalls;
    public final int totalObjectCalls;
    public final ArrayList<ClassInfo> classes;

    public ProgramData(String fileName, String fileSize, String packageName, int totalLinesCode, int totalImports,
            int totalClasses, int totalVariables, int totalMethods, int totalMethodCalls, int totalObjectCalls,
            ArrayList<ClassInfo> classes) {
        this.fileName = fileName;
        this.fileSize = fileSize;
        this.packageName = packageName;
        this.totalLinesCode = totalLinesCode;
        this.totalImports = totalImports;
        this.totalClasses = totalClasses;
        this.totalVariables = totalVariables;
        this.totalMethods = totalMethods;
        this.totalMethodCalls = totalMethodCalls;
        this.totalObjectCalls = totalObjectCalls;
        this.classes = classes;
    }
}
