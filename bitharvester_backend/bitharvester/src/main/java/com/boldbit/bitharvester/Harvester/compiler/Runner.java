package com.boldbit.bitharvester.Harvester.compiler;

import java.io.FileWriter;

import com.boldbit.bitharvester.Harvester.compiler.parser.Parser;
import com.boldbit.bitharvester.Harvester.compiler.source.StaticSourceFile;
import com.boldbit.bitharvester.Harvester.compiler.token.SourceFile;
import com.boldbit.bitharvester.Harvester.compiler.trees.ProgramTree;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Runner {

    public static void parse(StaticSourceFile staticSourceFile) {
        SourceFile sourceFile = new SourceFile(staticSourceFile.fileName, staticSourceFile.sourceString);
        try {
            Parser parser = new Parser(sourceFile);
            ProgramTree programTree = parser.parseProgram();

            ObjectMapper objectMapper = new ObjectMapper();
            String jsonString = objectMapper.writeValueAsString(programTree);

            String filePath = "bitharvester_backend/bitharvester/src/main/java/com/boldbit/bitharvester/Harvester/compiler/doc/output.json";

            FileWriter fileWriter = new FileWriter(filePath);
            fileWriter.write(jsonString);
            fileWriter.close();
            System.out.println("JSON saved to output.json");

        } catch (Exception e) {
            System.out.println("Exception in parsing");
        }
    }
}
