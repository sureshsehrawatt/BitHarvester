package com.boldbit.bitharvester.Harvester.compiler.token;

public class Token {

    public final TokenType tokenType;
    public final SourceRange location;

    public Token(TokenType tokenType, SourceRange location){
        this.tokenType = tokenType;
        this.location = location;
    }

    public SourcePosition getStart(){
        return location.start;
    }


    // public String group, value, tokenNumber, prevTokenNumber;
    // public int lineNumber, colNumber;

    // public Token(String group, String value, int lineNumber) {
    //     this.group = group;
    //     this.value = value;
    //     this.lineNumber = lineNumber;
    // }

    // @Override
    // public String toString() {
    //     return "Token{" +
    //             "group='" + group + '\'' +
    //             ",\t\t value='" + value + '\'' +
    //             ",\t\t lineNumber='" + lineNumber + '\'' +
    //             '}';
    // }

    // public Token(String value, String tokenNumber, String prevTokenNumber, int lineNumber, int colNumber) {
    //     this.value = value;
    //     this.tokenNumber = tokenNumber;
    //     this.prevTokenNumber = prevTokenNumber;
    //     this.lineNumber = lineNumber;
    //     this.colNumber = colNumber;
    // }

    // @Override
    // public String toString() {
    //     return "Token{" +
    //             "value='" + value + '\'' +
    //             ", tokenNumber='" + tokenNumber + '\'' +
    //             ", prevTokenNumber='" + prevTokenNumber + '\'' +
    //             ", lineNumber='" + lineNumber + '\'' +
    //             ", colNumber=" + colNumber +
    //             '}';
    // }
}
