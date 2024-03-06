package com.boldbit.bitharvester.Harvester.compiler;

import com.boldbit.bitharvester.Harvester.compiler.lexer.Lexer;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("--------------------------------------< Lexer >-----------------------------------");
        new Lexer("src/rough/Example.java");
    }
}
