package com.boldbit.bitharvester.Harvester.compiler.token;

public class LiteralToken extends Token{

    public final String value;

    public LiteralToken(TokenType tokenType, SourceRange location, String value) {
        super(tokenType, location);
        this.value = value;
    }

    public String toString(){
        return value;
    }
    
}
