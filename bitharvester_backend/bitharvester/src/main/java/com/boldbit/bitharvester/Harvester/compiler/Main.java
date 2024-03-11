package com.boldbit.bitharvester.Harvester.compiler;

import java.nio.file.Path;
import java.nio.file.Paths;

import com.boldbit.bitharvester.Harvester.compiler.source.StaticSourceFile;

public class Main {
    public static void main(String[] args) throws Exception {
        // new Lexer("src/rough/Example.java");
        System.out.println("--------------------------------------< Runner >-----------------------------------");
        
        String filePath = "/Users/cavisson/Documents/Projects/bitharvester/bitharvester_backend/bitharvester/src/main/java/com/boldbit/bitharvester/Harvester/compiler/rough/Example.java";
        Path path = Paths.get(filePath);
        
        StaticSourceFile staticSourceFile = new StaticSourceFile(path);
        Runner.parse(staticSourceFile);
        
        System.out.println("------------------------------------< Compiled!!! >--------------------------------");
    }
}
