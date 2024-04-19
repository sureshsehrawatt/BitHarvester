package com.boldbit.bitharvester.Harvester.compiler;

import java.io.FileWriter;

import com.boldbit.bitharvester.Harvester.compiler.Analyzer.Program;
import com.boldbit.bitharvester.Harvester.compiler.parser.Parser;
import com.boldbit.bitharvester.Harvester.compiler.source.StaticSourceFile;
import com.boldbit.bitharvester.Harvester.compiler.token.SourceFile;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Runner {

    public static void parse(StaticSourceFile staticSourceFile) {
        try {
            SourceFile sourceFile = new SourceFile(staticSourceFile.fileName, staticSourceFile.sourceString,
                    staticSourceFile.fileSize);

            Parser parser = new Parser(sourceFile);
            Program program = parser.parseProgram();

            ObjectMapper objectMapper = new ObjectMapper();

            writeJsonToFile("programData", objectMapper.writeValueAsString(program.programData));
            writeJsonToFile("programTree", objectMapper.writeValueAsString(program.programTree));
            
            System.out.println("JSON saved!!!");
        } catch (Exception e) {
            System.out.println("Exception in parsing:- " + e);
        }
    }

    private static void writeJsonToFile(String fileName, String jsonString) {
        String filePath = "bitharvester_backend/bitharvester/src/main/java/com/boldbit/bitharvester/Harvester/compiler/doc/"
                + fileName + ".json";
        try (FileWriter fileWriter = new FileWriter(filePath)) {
            fileWriter.write(jsonString);
            fileWriter.close();
        } catch (Exception e) {
            System.out.println("Exception in Json writing:- " + e);
        }
    }
}