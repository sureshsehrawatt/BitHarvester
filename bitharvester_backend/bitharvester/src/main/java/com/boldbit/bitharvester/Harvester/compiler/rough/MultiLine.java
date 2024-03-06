package com.boldbit.bitharvester.Harvester.compiler.rough;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MultiLine {
    public static void main(String[] args) {
        String code = "package rough;\n" +
              "\n" +
              "import java.util.Arrays;\n" +
              "\n" +
              "/*\n" +
              " * MultiLine comment\n" +
              " * comment\n" +
              " * comment\n" +
              " */\n" +
              "public class Example {\n" +
              "\n" +
              "    String name;\n" +
              "    int age;\n" +
              "\n" +
              "    public Example(String name, int age) {\n" +
              "        this.name = name;\n" +
              "        this.age = age;\n" +
              "    }\n" +
              "\n" +
              "    public void displayInfo() {\n" +
              "        System.out.println(\"Name: \" + name);\n" +
              "        System.out.println(\"Age: \" + age);\n" +
              "    }\n" +
              "\n" +
              "    public static void main(String[] args) {\n" +
              "        Example example = new Example(\"John\", 30);\n" +
              "        example.displayInfo();\n" +
              "        char[] arr = new char[10];\n" +
              "        StringBuilder sb = new StringBuilder();\n" +
              "        sb.reverse();\n" +
              "    }\n" +
              "}\n";


        String regex = "\\/\\*.*?\\*\\/";

        Pattern pattern = Pattern.compile(regex, Pattern.DOTALL);
        Matcher matcher = pattern.matcher(code);

        while (matcher.find()) {
            System.out.println("Multiline comment found: " + matcher.group());
        }
    }
}
