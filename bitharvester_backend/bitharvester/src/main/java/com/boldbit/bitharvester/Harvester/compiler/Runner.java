package com.boldbit.bitharvester.Harvester.compiler;

import java.io.FileWriter;

import com.boldbit.bitharvester.Harvester.compiler.Analyzer.Program;
import com.boldbit.bitharvester.Harvester.compiler.Analyzer.ProgramData;
import com.boldbit.bitharvester.Harvester.compiler.parser.Parser;
import com.boldbit.bitharvester.Harvester.compiler.source.StaticSourceFile;
import com.boldbit.bitharvester.Harvester.compiler.token.SourceFile;
import com.boldbit.bitharvester.Harvester.compiler.trees.ProgramTree;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Runner {

    public static void parse(StaticSourceFile staticSourceFile) {
        SourceFile sourceFile = new SourceFile(staticSourceFile.fileName, staticSourceFile.sourceString,
                staticSourceFile.fileSize);
        try {
            Parser parser = new Parser(sourceFile);
            Program program = parser.parseProgram();
            ProgramData programData = program.programData;
            ProgramTree programTree = program.programTree;

            ObjectMapper objectMapper = new ObjectMapper();
            String programDataJsonString = objectMapper.writeValueAsString(programData);
            String programTreeJsonString = objectMapper.writeValueAsString(programTree);

            String programDataFilePath = "bitharvester_backend/bitharvester/src/main/java/com/boldbit/bitharvester/Harvester/compiler/doc/programData.json";
            String programTreeFilePath = "bitharvester_backend/bitharvester/src/main/java/com/boldbit/bitharvester/Harvester/compiler/doc/programTree.json";

            FileWriter fileWriter;

            fileWriter = new FileWriter(programDataFilePath);
            fileWriter.write(programDataJsonString);
            fileWriter.close();

            fileWriter = new FileWriter(programTreeFilePath);
            fileWriter.write(programTreeJsonString);
            fileWriter.close();

            System.out.println("JSON saved!!!");

        } catch (Exception e) {
            System.out.println("Exception in parsing");
            System.out.println("Exception:- " + e);
        }
    }
}
