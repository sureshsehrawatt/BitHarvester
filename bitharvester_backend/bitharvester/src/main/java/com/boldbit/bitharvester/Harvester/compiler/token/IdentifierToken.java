package com.boldbit.bitharvester.Harvester.compiler.token;

public class IdentifierToken extends Token {

    public final String value;

    public IdentifierToken(String value, SourceRange location) {
        super(TokenType.IDENTIFIER, location);
        this.value = value;
    }

    public String toString(){
        return value;
    }
    
}
