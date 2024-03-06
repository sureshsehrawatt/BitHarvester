package com.boldbit.bitharvester.Harvester.compiler.token;

public class IdentifierToken extends Token {

    public final String value;

    public IdentifierToken(TokenType tokenType, String value, SourceRange location) {
        super(tokenType, location);
        this.value = value;
    }

    public String toString(){
        return value;
    }
    
}
