package com.boldbit.bitharvester.Harvester.compiler.token;

public class LiteralToken extends Token{

    public final String value;

    public LiteralToken(TokenType tokenType, String value, TreeLocation location) {
        super(tokenType, location);
        this.value = value;
    }

    public String toString(){
        return value;
    }
    
}
