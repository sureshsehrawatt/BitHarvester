package com.boldbit.bitharvester.Harvester.compiler.Analyzer;

import com.boldbit.bitharvester.Harvester.compiler.trees.ProgramTree;

public class Program {
    public final ProgramData programData;
    public final ProgramTree programTree;

    public Program(ProgramData programData, ProgramTree programTree) {
        this.programData = programData;
        this.programTree = programTree;
    }
}
