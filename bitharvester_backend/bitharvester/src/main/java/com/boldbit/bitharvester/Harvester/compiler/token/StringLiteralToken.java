package com.boldbit.bitharvester.Harvester.compiler.token;

public class StringLiteralToken extends LiteralToken {

    public final boolean hasUnescapedUnicodeLineOrParagraphSeparator;

    public StringLiteralToken(String value, TreeLocation location, boolean hasUnescapedUnicodeLineOrParagraphSeparator) {
        super(TokenType.STRING_LITERAL, value, location);
        this.hasUnescapedUnicodeLineOrParagraphSeparator = hasUnescapedUnicodeLineOrParagraphSeparator;
    }

    public String toString() {
        return value;
    }

    public boolean hasUnescapedUnicodeLineOrParagraphSeparator() {
        return hasUnescapedUnicodeLineOrParagraphSeparator;
    }

}
