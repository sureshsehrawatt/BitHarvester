package com.boldbit.bitharvester.Harvester.compiler.token;

public class TokenImages {

    public static final String KEYWORDS_REGEX = "\\b(?:abstract|assert|boolean|break|byte|case|catch|char|class|const|continue|default|do|double|else|enum|extends|final|finally|float|for|if|implements|import|instanceof|int|interface|long|native|new|null|package|private|protected|public|return|short|static|strictfp|super|switch|synchronized|this|throw|throws|transient|try|void|volatile|while)\\b";
    public static final String IDENTIFIER_REGEX = "\\b[a-zA-Z_]\\w*\\b";
    public static final String LITERAL_REGEX = "\\b(?:true|false|null)\\b|\\b(?:[0-9]+(?:\\.[0-9]+)?(?:[eE][+-]?[0-9]+)?|0[xX][0-9a-fA-F]+)\\b";
    public static final String OPERATOR_REGEX = "\\+\\+|--|\\+=|-=|\\*=|/=|%|\\+=|-=|==|!=|<=|>=|&&|\\|\\||[+\\-*/%=&|<>!]=|\\?|:|\\.|,|;|\\{|}|\\(|\\)|\\[|\\]";
    public static final String COMMENT_REGEX = "//.*|/\\*(?:.|[\\n\\r])*?\\*/";
    public static final String WHITESPACE_REGEX = "\\s+";

    // Combined regex pattern
    public static final String TOKEN_REGEX = String.join("|",
            COMMENT_REGEX,
            KEYWORDS_REGEX,
            IDENTIFIER_REGEX,
            LITERAL_REGEX,
            OPERATOR_REGEX,
            WHITESPACE_REGEX);

}
