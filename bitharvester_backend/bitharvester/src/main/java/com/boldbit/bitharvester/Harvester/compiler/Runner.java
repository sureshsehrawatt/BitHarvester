package com.boldbit.bitharvester.Harvester.compiler;

import com.boldbit.bitharvester.Harvester.compiler.parser.Parser;
import com.boldbit.bitharvester.Harvester.compiler.source.StaticSourceFile;
import com.boldbit.bitharvester.Harvester.compiler.token.SourceFile;
import com.boldbit.bitharvester.Harvester.compiler.trees.ProgramTree;

public class Runner {

    public static void parse(StaticSourceFile staticSourceFile){
        SourceFile sourceFile = new SourceFile(staticSourceFile.fileName, staticSourceFile.sourceString);
        try {
            Parser parser = new Parser(sourceFile);
            ProgramTree programTree = parser.parseProgram();
            System.out.println(programTree.toString());
        } catch (Exception e) {
            System.out.println("Exception in parsing");
        }
    }
}
