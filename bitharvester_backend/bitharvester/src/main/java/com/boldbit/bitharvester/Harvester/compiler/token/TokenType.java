package com.boldbit.bitharvester.Harvester.compiler.token;

/* public enum TokenType {
    // Keywords
    ABSTRACT,
    ASSERT,
    BOOLEAN,
    BREAK,
    BYTE,
    CASE,
    CATCH,
    CHAR,
    CLASS,
    CONST,
    CONTINUE,
    DEFAULT,
    DO,
    DOUBLE,
    ELSE,
    ENUM,
    EXTENDS,
    FINAL,
    FINALLY,
    FLOAT,
    FOR,
    IF,
    IMPLEMENTS,
    IMPORT,
    INSTANCEOF,
    INT,
    INTERFACE,
    LONG,
    NATIVE,
    NEW,
    NULL,
    PACKAGE,
    PRIVATE,
    PROTECTED,
    PUBLIC,
    RETURN,
    SHORT,
    STATIC,
    STRICTFP,
    SUPER,
    SWITCH,
    SYNCHRONIZED,
    THIS,
    THROW,
    THROWS,
    TRANSIENT,
    TRY,
    VOID,
    VOLATILE,
    WHILE,

    // Operators
    LEFT_PAREN,
    RIGHT_PAREN,
    LEFT_BRACKET,
    RIGHT_BRACKET,
    LEFT_BRACE,
    RIGHT_BRACE,
    INCREMENT,
    DECREMENT,
    PLUS_ASSIGN,
    MINUS_ASSIGN,
    MULTIPLY_ASSIGN,
    DIVIDE_ASSIGN,
    MODULO,
    EQUAL_TO,
    NOT_EQUAL_TO,
    LESS_THAN_OR_EQUAL_TO,
    GREATER_THAN_OR_EQUAL_TO,
    LOGICAL_AND,
    LOGICAL_OR,
    PLUS,
    MINUS,
    MULTIPLY,
    DIVIDE,
    BITWISE_AND,
    BITWISE_OR,
    LESS_THAN,
    GREATER_THAN,
    NEGATION,
    ASSIGN,
    TERNARY_CONDITIONAL,
    TERNARY_ELSE,
    DOT,
    COMMA,
    SEMICOLON,

    // Extra
    LITERAL,
    IDENTIFIER,
    WHITESPACE,
    REGULAR_EXPRESSION,

    // Comment
    MULTILINE_COMMENT,
    SINGLE_LINE_COMMENT, END_OF_FILE,

    
} */

public enum TokenType {
    // Special Characters & Operators
    OPEN_CURLY,
    CLOSE_CURLY,
    OPEN_PAREN,
    CLOSE_PAREN,
    OPEN_SQUARE,
    CLOSE_SQUARE,
    PERIOD,
    COLON,
    SEMI_COLON,
    COMMA,
    QUESTION_MARK,
    BANG,
    STAR,
    PERCENT,
    CARET,
    SLASH,
    AMPERSAND,
    AND,
    BAR,
    OR,
    POUND,
    PLUS,
    MINUS,
    PLUS_EQUAL,
    MINUS_EQUAL,
    MINUS_MINUS,
    PLUS_PLUS,
    EQUAL,
    EQUAL_EQUAL,
    GREATER_THAN,
    LESS_THAN,
    NOT_EQUAL,
    LESS_EQUAL,
    GREATER_EQUAL,
    LEFT_SHIFT,
    RIGHT_SHIFT,
    LEFT_SHIFT_EQUAL,
    RIGHT_SHIFT_EQUAL,
    UNSIGNED_RIGHT_SHIFT,
    UNSIGNED_RIGHT_SHIFT_EQUAL,

    // Data Types
    BIGINT,

    // other
    LITERAL,
    STRING_LITERAL,

    // EOF
    END_OF_FILE,

    // Keywords
    ASSERT,
    BREAK,
    CASE,
    CATCH,
    CLASS,
    CONST,
    CONTINUE,
    DEFAULT,
    DO,
    DOUBLE,
    ELSE,
    ENUM,
    EXTENDS,
    FINALLY,
    FOR,
    GOTO,
    IF,
    IMPLEMENTS,
    IMPORT,
    INSTANCEOF,
    INTERFACE,
    NEW,
    NULL,
    PACKAGE,
    RETURN,
    SUPER,
    SWITCH,
    THIS,
    THROW,
    THROWS,
    TRY,
    VOID,
    WHILE,
    IDENTIFIER,

    // Access Modifiers
    PRIVATE,
    PROTECTED,
    PUBLIC,

    // Non-Access Modifiers:
    ABSTRACT,
    FINAL,
    NATIVE,
    STRICTFP,
    SYNCHRONIZED,
    STATIC,
    TRANSIENT,
    VOLATILE,

    // type
    BOOLEAN,
    BYTE,
    CHAR,
    FLOAT,
    INT,
    LONG,
    SHORT,
    STRING,

    N,
    CLASS_IDENTIFIER,
    ;

    public static boolean isModifier(TokenType tokenType) {
        return tokenType == TokenType.PUBLIC ||
                tokenType == TokenType.PRIVATE ||
                tokenType == TokenType.PROTECTED ||
                tokenType == TokenType.ABSTRACT ||
                tokenType == TokenType.FINAL ||
                tokenType == TokenType.NATIVE ||
                tokenType == TokenType.STRICTFP ||
                tokenType == TokenType.SYNCHRONIZED ||
                tokenType == TokenType.STATIC ||
                tokenType == TokenType.TRANSIENT ||
                tokenType == TokenType.VOLATILE ||
                false; // Return false if the current token is not a modifier
    }

    public static boolean isCompareOperator(TokenType tokenType) {
        return tokenType == TokenType.EQUAL_EQUAL ||
                tokenType == TokenType.NOT_EQUAL ||
                tokenType == TokenType.GREATER_EQUAL ||
                tokenType == TokenType.LESS_EQUAL ||
                tokenType == TokenType.GREATER_THAN ||
                tokenType == TokenType.LESS_THAN ||
                false; // Return false if the current token is not a modifier
    }

    public static boolean isOperator(TokenType tokenType) {
        return tokenType == TokenType.EQUAL ||
                tokenType == TokenType.PLUS ||
                tokenType == TokenType.MINUS ||
                tokenType == TokenType.STAR ||
                tokenType == TokenType.SLASH ||
                tokenType == TokenType.PERCENT ||
                tokenType == TokenType.PLUS_PLUS ||
                tokenType == TokenType.MINUS_MINUS ||
                false; // Return false if the current token is not a modifier
    }

    public static boolean isArithmeticOperator(TokenType tokenType) {
        return tokenType == TokenType.PLUS ||
                tokenType == TokenType.MINUS ||
                tokenType == TokenType.STAR ||
                tokenType == TokenType.SLASH ||
                tokenType == TokenType.PERCENT ||
                false; // Return false if the current token is not a modifier
    }

    public static boolean isUnaryOperator(TokenType tokenType) {
        return tokenType == TokenType.PLUS_PLUS ||
                tokenType == TokenType.MINUS_MINUS ||
                false; // Return false if the current token is not a modifier
    }

    public static boolean peekAssignmentOperator(TokenType tokenType) {
        switch (tokenType) {
            case EQUAL:
            case PLUS_EQUAL:
            case MINUS_EQUAL:
            case LEFT_SHIFT_EQUAL:
            case RIGHT_SHIFT_EQUAL:
            case UNSIGNED_RIGHT_SHIFT_EQUAL:
                return true;
            default:
                return false;
        }
    }

    public static boolean isType(TokenType tokenType) {
        switch (tokenType) {
            case BOOLEAN:
            case BYTE:
            case CHAR:
            case FLOAT:
            case INT:
            case LONG:
            case SHORT:
            case STRING:
            case VOID:
                return true;
            default:
                return false;
        }
    }

    public static boolean isBinaryOperator(TokenType tokenType) {
        switch (tokenType) {
            case PERIOD: // .
            case PLUS: // +
            case MINUS: // -
            case STAR: // *
            case SLASH: // /
            case PERCENT: // %
            case CARET: // ^
            case AMPERSAND: // &
            case BAR: // |
            case LESS_THAN: // <
            case GREATER_THAN: // >
            case EQUAL: // =
            case NOT_EQUAL: // !=
            case LESS_EQUAL: // <=
            case GREATER_EQUAL: // >=
            case LEFT_SHIFT: // <<
            case RIGHT_SHIFT: // >>
            case UNSIGNED_RIGHT_SHIFT: // >>>
            case PLUS_EQUAL: // +=
            case MINUS_EQUAL: // -=
            case LEFT_SHIFT_EQUAL: // <<=
            case RIGHT_SHIFT_EQUAL: // >>=
            case UNSIGNED_RIGHT_SHIFT_EQUAL: // >>>=
                return true;
            default:
                return false;
        }
    }
}

class CheckTokenType {
    public String checkKeyword(String word) {
        switch (word) {
            case "abstract":
                return "ABSTRACT";
            case "assert":
                return "ASSERT";
            case "boolean":
                return "BOOLEAN";
            case "break":
                return "BREAK";
            case "byte":
                return "BYTE";
            case "case":
                return "CASE";
            case "catch":
                return "CATCH";
            case "char":
                return "CHAR";
            case "class":
                return "CLASS";
            case "const":
                return "CONST";
            case "continue":
                return "CONTINUE";
            case "default":
                return "DEFAULT";
            case "do":
                return "DO";
            case "double":
                return "DOUBLE";
            case "else":
                return "ELSE";
            case "enum":
                return "ENUM";
            case "extends":
                return "EXTENDS";
            case "final":
                return "FINAL";
            case "finally":
                return "FINALLY";
            case "float":
                return "FLOAT";
            case "for":
                return "FOR";
            case "if":
                return "IF";
            case "implements":
                return "IMPLEMENTS";
            case "import":
                return "IMPORT";
            case "instanceof":
                return "INSTANCEOF";
            case "int":
                return "INT";
            case "interface":
                return "INTERFACE";
            case "long":
                return "LONG";
            case "native":
                return "NATIVE";
            case "new":
                return "NEW";
            case "null":
                return "NULL";
            case "package":
                return "PACKAGE";
            case "private":
                return "PRIVATE";
            case "protected":
                return "PROTECTED";
            case "public":
                return "PUBLIC";
            case "return":
                return "RETURN";
            case "short":
                return "SHORT";
            case "static":
                return "STATIC";
            case "strictfp":
                return "STRICTFP";
            case "super":
                return "SUPER";
            case "switch":
                return "SWITCH";
            case "synchronized":
                return "SYNCHRONIZED";
            case "this":
                return "THIS";
            case "throw":
                return "THROW";
            case "throws":
                return "THROWS";
            case "transient":
                return "TRANSIENT";
            case "try":
                return "TRY";
            case "void":
                return "VOID";
            case "volatile":
                return "VOLATILE";
            case "while":
                return "WHILE";
            default:
                break;
        }
        return null;
    }

    public String checkOperator(String word) {
        switch (word) {
            case "(":
                return "LEFT_PAREN";
            case ")":
                return "RIGHT_PAREN";
            case "[":
                return "LEFT_BRACKET";
            case "]":
                return "RIGHT_BRACET";
            case "{":
                return "LEFT_BRACE";
            case "}":
                return "RIGHT_BRACE";
            case "++":
                return "INCREMENT";
            case "--":
                return "DECREMENT";
            case "+=":
                return "PLUS_ASSIGN";
            case "-=":
                return "MINUS_ASSIGN";
            case "*=":
                return "MULTIPLY_ASSIGN";
            case "/=":
                return "DIVIDE_ASSIGN";
            case "%":
                return "MODULO";
            case "==":
                return "EQUAL_TO";
            case "!=":
                return "NOT_EQUAL_TO";
            case "<=":
                return "LESS_THAN_OR_EQUAL_TO";
            case ">=":
                return "GREATER_THAN_OR_EQUAL_TO";
            case "&&":
                return "LOGICAL_AND";
            case "||":
                return "LOGICAL_OR";
            case "+":
                return "PLUS";
            case "-":
                return "MINUS";
            case "*":
                return "MULTIPLY";
            case "/":
                return "DIVIDE";
            case "&":
                return "BITWISE_AND";
            case "|":
                return "BITWISE_OR";
            case "<":
                return "LESS_THAN";
            case ">":
                return "GREATER_THAN";
            case "!":
                return "NEGATION";
            case "=":
                return "ASSIGN";
            case "?":
                return "TERNARY_CONDITIONAL";
            case ":":
                return "TERNARY_ELSE";
            case ".":
                return "DOT";
            case ",":
                return "COMMA";
            case ";":
                return "SEMICOLON";
            default:
                break;
        }
        return null;
    }
}
