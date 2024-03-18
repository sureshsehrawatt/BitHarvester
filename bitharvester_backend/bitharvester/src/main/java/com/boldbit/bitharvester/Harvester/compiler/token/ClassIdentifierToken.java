package com.boldbit.bitharvester.Harvester.compiler.token;

public class ClassIdentifierToken extends Token {
    public final String value;

    public ClassIdentifierToken(String value, TreeLocation location) {
        super(TokenType.CLASS_IDENTIFIER, location);
        this.value = value;
    }

    public String toString() {
        return value;
    }
}
