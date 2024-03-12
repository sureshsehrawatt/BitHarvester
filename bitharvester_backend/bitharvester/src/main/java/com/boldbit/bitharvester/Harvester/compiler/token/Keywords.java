package com.boldbit.bitharvester.Harvester.compiler.token;

public enum Keywords {
    ABSTRACT("abstract", TokenType.ABSTRACT),
    ASSERT("assert", TokenType.ASSERT),
    BOOLEAN("boolean", TokenType.BOOLEAN),
    BREAK("break", TokenType.BREAK),
    BYTE("byte", TokenType.BYTE),
    CASE("case", TokenType.CASE),
    CATCH("catch", TokenType.CATCH),
    CHAR("char", TokenType.CHAR),
    CLASS("class", TokenType.CLASS),
    CONST("const", TokenType.CONST),
    CONTINUE("continue", TokenType.CONTINUE),
    DEFAULT("default", TokenType.DEFAULT),
    DO("do", TokenType.DO),
    DOUBLE("double", TokenType.DOUBLE),
    ELSE("else", TokenType.ELSE),
    ENUM("enum", TokenType.ENUM),
    EXTENDS("extends", TokenType.EXTENDS),
    FINAL("final", TokenType.FINAL),
    FINALLY("finally", TokenType.FINALLY),
    FLOAT("float", TokenType.FLOAT),
    FOR("for", TokenType.FOR),
    GOTO("goto", TokenType.GOTO),
    IF("if", TokenType.IF),
    IMPLEMENTS("implements", TokenType.IMPLEMENTS),
    IMPORT("import", TokenType.IMPORT),
    INSTANCEOF("instanceof", TokenType.INSTANCEOF),
    INT("int", TokenType.INT),
    INTERFACE("interface", TokenType.INTERFACE),
    LONG("long", TokenType.LONG),
    NATIVE("native", TokenType.NATIVE),
    NEW("new", TokenType.NEW),
    NULL("null", TokenType.NULL),
    PACKAGE("package", TokenType.PACKAGE),
    PRIVATE("private", TokenType.PRIVATE),
    PROTECTED("protected", TokenType.PROTECTED),
    PUBLIC("public", TokenType.PUBLIC),
    RETURN("return", TokenType.RETURN),
    SHORT("short", TokenType.SHORT),
    STATIC("static", TokenType.STATIC),
    STRICTFP("strictfp", TokenType.STRICTFP),
    SUPER("super", TokenType.SUPER),
    SWITCH("switch", TokenType.SWITCH),
    SYNCHRONIZED("synchronized", TokenType.SYNCHRONIZED),
    THIS("this", TokenType.THIS),
    THROW("throw", TokenType.THROW),
    THROWS("throws", TokenType.THROWS),
    TRANSIENT("transient", TokenType.TRANSIENT),
    TRY("try", TokenType.TRY),
    VOID("void", TokenType.VOID),
    VOLATILE("volatile", TokenType.VOLATILE),
    WHILE("while", TokenType.WHILE),
    N("-1", TokenType.N);

    private final String keyword;
    private final TokenType tokenType;

    Keywords(String keyword, TokenType tokenType) {
        this.keyword = keyword;
        this.tokenType = tokenType;
    }

    public static Keywords contains(String value) {
        for (Keywords keyword : Keywords.values()) {
            if (keyword.keyword.equals(value.toLowerCase())) {
                return keyword;
            }
        }
        return N;
    }

    public TokenType getTokenType() {
        return tokenType;
    }

    public static boolean isKeyword(TokenType tokenType) {
        if(contains(tokenType.toString()).tokenType != TokenType.N)
            return true;
        return false;
    }

    public static Keywords get(TokenType tokenType) {
        return contains(tokenType.toString());
    }
}
