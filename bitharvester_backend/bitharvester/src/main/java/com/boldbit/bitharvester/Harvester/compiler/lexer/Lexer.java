// package com.boldbit.bitharvester.Harvester.compiler.lexer;

// import java.io.BufferedReader;
// import java.io.FileReader;
// import java.io.IOException;
// import java.util.ArrayList;
// import java.util.List;
// import java.util.regex.Matcher;
// import java.util.regex.Pattern;

// import com.boldbit.bitharvester.Harvester.compiler.token.SourceRange;
// import com.boldbit.bitharvester.Harvester.compiler.token.Token;
// import com.boldbit.bitharvester.Harvester.compiler.token.TokenImages;
// import com.boldbit.bitharvester.Harvester.compiler.token.TokenType;

// public class Lexer {

//     boolean multiLineFlag = false;
//     String multiLineString = "";
    
//     public Lexer(String filepath) {
//         List<Token> tokens;
//         try {
//             tokens = process(filepath);
//             parseTokens(tokens);
//             // for (Token token : tokens)
//             //     System.out.println(token);
//         } catch (IOException e) {
//             e.printStackTrace();
//         }
//     }

//     List<Token> process(String filepath) throws IOException {
//         List<Token> tokens = new ArrayList<>();

//         BufferedReader reader = new BufferedReader(new FileReader(filepath));
//         String line;
//         int lineNumber = 1;

//         while ((line = reader.readLine()) != null) {
//             if (multiLineFlag) {
//                 if (line.contains("*/")) {
//                     String commentedBeforeString = line.substring(0, line.indexOf("*/") + 2);
//                     multiLineString += commentedBeforeString;
//                     // FixMe
//                     SourceRange sourceRange = null;
//                     tokens.add(new Token(TokenType.MULTILINE_COMMENT, sourceRange));
//                     // tokens.add(new Token("MULTILINE_COMMENT", multiLineString, lineNumber));
//                     String unCommentedString = line.substring(line.indexOf("*/") + 2);
//                     tokens.addAll(tokenizeLine(unCommentedString, lineNumber));
//                     multiLineString = "";
//                     multiLineFlag = false;
//                 } else {
//                     multiLineString += line + "\n";
//                 }
//             } else if (line.contains("/*")) {
//                 if (line.contains("*/")) {
//                     String unCommentedBeforeString = line.substring(0, line.indexOf("/*"));
//                     String commentedString = line.substring(line.indexOf("/*") - 2, line.indexOf("*/") + 2);
//                     String unCommentedAfterString = line.substring(line.indexOf("*/") + 2);
//                     tokens.addAll(tokenizeLine(unCommentedBeforeString, lineNumber));
//                     // FixMe
//                     SourceRange sourceRange = null;
//                     tokens.add(new Token(TokenType.MULTILINE_COMMENT, sourceRange));
//                     // tokens.add(new Token("MULTILINE_COMMENT", commentedString, lineNumber));
//                     tokens.addAll(tokenizeLine(unCommentedAfterString, lineNumber));
//                 } else {
//                     multiLineFlag = true;
//                     String unCommentedString = line.substring(0, line.indexOf("/*"));
//                     tokens.addAll(tokenizeLine(unCommentedString, lineNumber));
//                     multiLineString += line.substring(line.indexOf("/*")) + "\n";
//                 }
//             } else {
//                 tokens.addAll(tokenizeLine(line, lineNumber));
//             }
//             lineNumber++;
//         }

//         reader.close();
//         return tokens;
//     }

//     private List<Token> tokenizeLine(String line, int lineNumber) {
//         List<Token> tokens = new ArrayList<>();
//         Pattern pattern = Pattern.compile(TokenImages.TOKEN_REGEX);
//         Matcher matcher = pattern.matcher(line);

//         while (matcher.find()) {
//             String token = matcher.group().trim();
//             if (!token.isEmpty()) {
//                 tokens.add(processToken(token, lineNumber));
//             }
//         }
//         return tokens;
//     }

//     private Token processToken(String word, int lineNumber) {
//         // Pattern pattern = Pattern.compile(TokenImages.KEYWORDS_REGEX);
//         // Matcher matcher = pattern.matcher(word);

//         Pattern pattern = Pattern.compile(TokenImages.COMMENT_REGEX);
//         Matcher matcher = pattern.matcher(word);
//         while (matcher.find()) {
//             return new Token("SINGLE_LINE_COMMENT", word, lineNumber);
//         }

//         pattern = Pattern.compile(TokenImages.KEYWORDS_REGEX);
//         matcher = pattern.matcher(word);
//         while (matcher.find()) {
//             String name = checkKeyword(word);
//             return new Token(name, word, lineNumber);
//         }

//         pattern = Pattern.compile(TokenImages.OPERATOR_REGEX);
//         matcher = pattern.matcher(word);
//         while (matcher.find()) {
//             String name = checkOperator(word);
//             return new Token(name, word, lineNumber);
//             // return new Token("OPERATOR", word, lineNumber);
//         }

//         pattern = Pattern.compile(TokenImages.LITERAL_REGEX);
//         matcher = pattern.matcher(word);
//         while (matcher.find()) {
//             return new Token("LITERAL", word, lineNumber);
//         }

//         // pattern = Pattern.compile(TokenImages.COMMENT_REGEX);
//         // matcher = pattern.matcher(word);
//         // while (matcher.find()) {
//         //     return new Token("SINGLE_LINE_COMMENT", word, lineNumber);
//         // }

//         pattern = Pattern.compile(TokenImages.IDENTIFIER_REGEX);
//         matcher = pattern.matcher(word);
//         while (matcher.find()) {
//             return new Token("IDENTIFIER", word, lineNumber);
//         }

//         pattern = Pattern.compile(TokenImages.WHITESPACE_REGEX);
//         matcher = pattern.matcher(word);
//         while (matcher.find()) {
//             return new Token("WHITESPACE", word, lineNumber);
//         }

//         return null;
//     }

        

//     void parseTokens(List<Token> tokens){
//         String file = "";
//         int fileLineNumber = tokens.get(0).lineNumber;
//         for(Token token : tokens){
//             if(token.lineNumber == fileLineNumber){
//                 file += token.group + " ";
//             } else {
//                 file += "\n";
//                 file += token.group + " ";
//                 fileLineNumber = token.lineNumber;
//             }
//         }
//         System.out.println(file);
//     }

// }
