package com.boldbit.bitharvester.Harvester.compiler.lexer;

import java.util.ArrayList;
import java.util.List;

import com.boldbit.bitharvester.Harvester.compiler.parser.LineNumberScanner;
import com.boldbit.bitharvester.Harvester.compiler.token.ClassIdentifierToken;
import com.boldbit.bitharvester.Harvester.compiler.token.Comment;
import com.boldbit.bitharvester.Harvester.compiler.token.IdentifierToken;
import com.boldbit.bitharvester.Harvester.compiler.token.Keywords;
import com.boldbit.bitharvester.Harvester.compiler.token.LiteralToken;
import com.boldbit.bitharvester.Harvester.compiler.token.SourceFile;
import com.boldbit.bitharvester.Harvester.compiler.token.SourcePosition;
import com.boldbit.bitharvester.Harvester.compiler.token.TreeLocation;
import com.boldbit.bitharvester.Harvester.compiler.token.StringLiteralToken;
import com.boldbit.bitharvester.Harvester.compiler.token.Token;
import com.boldbit.bitharvester.Harvester.compiler.token.TokenType;

public class Scanner {

    SourceFile sourceFile;
    LineNumberScanner lineNumberScanner;
    CommentRecorder commentRecorder;
    String contents;
    int index;
    int contentsLength;
    List<Token> tokensList = new ArrayList<>();
    Token prevToken = null;
    private int typeParameterLevel;

    public Scanner(SourceFile sourceFile, int offset, CommentRecorder commentRecorder) {
        this.sourceFile = sourceFile;
        this.contents = sourceFile.contents;
        this.contentsLength = sourceFile.contents.length();
        this.index = offset;
        this.typeParameterLevel = 0;
        this.lineNumberScanner = new LineNumberScanner(sourceFile);
        this.commentRecorder = commentRecorder;
    }

    public SourcePosition getPosition() {
        return tokensList.isEmpty() ? getPosition(index) : peekToken().location.start;
    }

    private SourcePosition getPosition(int offset) {
        return lineNumberScanner.getSourcePosition(offset);
    }

    private Token peekToken() {
        return peekToken(0);
    }

    public Token peekToken(int index) {
        while (tokensList.size() <= index) {
            tokensList.add(scanToken());
        }
        return tokensList.get(index);
    }

    private Token scanToken() {
        skipComments();
        int beginToken = index;
        if (isAtEnd()) {
            return createToken(TokenType.END_OF_FILE, beginToken);
        }
        char ch = nextChar();
        switch (ch) {
            case '{':
                return createToken(TokenType.OPEN_CURLY, beginToken);
            case '}':
                return createToken(TokenType.CLOSE_CURLY, beginToken);
            case '(':
                return createToken(TokenType.OPEN_PAREN, beginToken);
            case ')':
                return createToken(TokenType.CLOSE_PAREN, beginToken);
            case '[':
                return createToken(TokenType.OPEN_SQUARE, beginToken);
            case ']':
                return createToken(TokenType.CLOSE_SQUARE, beginToken);
            case '.':
                if (isDecimalDigit(peekChar()))
                    return scanNumberPostPeriod(beginToken);
                return createToken(TokenType.PERIOD, beginToken);
            case ':':
                return createToken(TokenType.COLON, beginToken);
            case ';':
                return createToken(TokenType.SEMI_COLON, beginToken);
            case ',':
                return createToken(TokenType.COMMA, beginToken);
            // TODO - hanlde this
            case '?':
                return createToken(TokenType.QUESTION_MARK, beginToken);
            case '<':
                switch (peekChar()) {
                    case '<':
                        nextChar();
                        if (peek('=')) {
                            nextChar();
                            return createToken(TokenType.LEFT_SHIFT_EQUAL, beginToken);
                        }
                        return createToken(TokenType.LEFT_SHIFT, beginToken);
                    case '=':
                        nextChar();
                        return createToken(TokenType.LESS_EQUAL, beginToken);
                    default:
                        return createToken(TokenType.LESS_THAN, beginToken);
                }
            case '>':
                if (typeParameterLevel > 0)
                    return createToken(TokenType.GREATER_THAN, beginToken);
                switch (peekChar()) {
                    case '>':
                        nextChar();
                        switch (peekChar()) {
                            case '=':
                                nextChar();
                                return createToken(TokenType.RIGHT_SHIFT_EQUAL, beginToken);
                            case '>':
                                nextChar();
                                if (peek('=')) {
                                    nextChar();
                                    return createToken(TokenType.UNSIGNED_RIGHT_SHIFT_EQUAL, beginToken);
                                }
                                return createToken(TokenType.UNSIGNED_RIGHT_SHIFT, beginToken);
                            default:
                                return createToken(TokenType.RIGHT_SHIFT, beginToken);
                        }
                    case '=':
                        nextChar();
                        return createToken(TokenType.GREATER_EQUAL, beginToken);
                    default:
                        return createToken(TokenType.GREATER_THAN, beginToken);
                }
            case '=':
                switch (peekChar()) {
                    case '=':
                        nextChar();
                        return createToken(TokenType.EQUAL_EQUAL, beginToken);
                    default:
                        return createToken(TokenType.EQUAL, beginToken);
                }
            case '!':
                if (peek('=')) {
                    nextChar();
                    return createToken(TokenType.NOT_EQUAL, beginToken);
                }
                return createToken(TokenType.BANG, beginToken);
            case '*':
                if (peek('=')) {
                    nextChar();
                    return createToken(TokenType.STAR_EQUAL, beginToken);
                }
                return createToken(TokenType.STAR, beginToken);
            case '/':
                if (peek('=')) {
                    nextChar();
                    return createToken(TokenType.SLASH_EQUAL, beginToken);
                }
                return createToken(TokenType.SLASH, beginToken);
            case '%':
                return createToken(TokenType.PERCENT, beginToken);
            case '^':
                return createToken(TokenType.CARET, beginToken);
            case '+':
                switch (peekChar()) {
                    case '+':
                        nextChar();
                        return createToken(TokenType.PLUS_PLUS, beginToken);
                    case '=':
                        nextChar();
                        return createToken(TokenType.PLUS_EQUAL, beginToken);
                    default:
                        return createToken(TokenType.PLUS, beginToken);
                }
            case '-':
                switch (peekChar()) {
                    case '-':
                        nextChar();
                        return createToken(TokenType.MINUS_MINUS, beginToken);
                    case '=':
                        nextChar();
                        return createToken(TokenType.MINUS_EQUAL, beginToken);
                    default:
                        return createToken(TokenType.MINUS, beginToken);
                }
            case '&':
                switch (peekChar()) {
                    case '&':
                        return createToken(TokenType.AND, beginToken);
                    default:
                        return createToken(TokenType.AMPERSAND, beginToken);
                }
            case '|':
                switch (peekChar()) {
                    case '|':
                        return createToken(TokenType.OR, beginToken);
                    default:
                        return createToken(TokenType.BAR, beginToken);
                }
            case '#':
                return createToken(TokenType.POUND, beginToken);
            case '0':
            case '1':
            case '2':
            case '3':
            case '4':
            case '5':
            case '6':
            case '7':
            case '8':
            case '9':
                return scanPostDigit(beginToken);
            // TODO - implement string tokenization
            case '"':
            case '\'':
                return scanStringLiteral(beginToken, ch);
            case '`':
                return scanTemplateLiteral(beginToken);
            default:
                return scanIdentifierOrKeyword(beginToken, ch);

        }
    }

    private Token scanIdentifierOrKeyword(int beginIndex, char ch) {
        int identifierStartIndex = index - 1;
        boolean isValidIdentifierStart = Character.isJavaIdentifierStart(ch);
        if (isValidIdentifierStart) {
            while (Character.isJavaIdentifierPart(ch = nextChar()))
                ;
        } else {
            throw new UnsupportedOperationException("Invalid char at scanIdentifierOrKeyword: " + ch);
        }
        index--;
        String value = contents.substring(identifierStartIndex, index);
        Keywords keyword = Keywords.contains(value);

        if (Character.isUpperCase(value.charAt(0)) && prevToken().tokenType != TokenType.PERIOD) {
            return new ClassIdentifierToken(value, getTokenRange(beginIndex));
        } else if (!(keyword == Keywords.N)) {
            return new Token(keyword.getTokenType(), getTokenRange(beginIndex));
        }
        return new IdentifierToken(value, getTokenRange(beginIndex));
    }

    private Token scanStringLiteral(int beginIndex, char ch) {
        // String literals might span multiple lines.
        int identifierStartIndex = index;
        while (nextChar() != '"')
            ;
        String stringContent = contents.substring(identifierStartIndex, index - 1);

        return new StringLiteralToken(stringContent, getTokenRange(beginIndex));
    }

    private Token scanTemplateLiteral(int beginToken) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'scanTemplateLiteral'");
    }

    private boolean skipStringLiteralChar() {
        if (peek('\\')) {
            return skipStringLiteralEscapeSequence();
        }
        nextChar();
        return true;
    }

    private boolean skipStringLiteralEscapeSequence() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'skipStringLiteralEscapeSequence'");
    }

    private boolean peekStringLiteralChar(char terminator) {
        return !isAtEnd() && peekChar() != terminator && !isStringLineTerminator(peekChar());
    }

    private boolean isStringLineTerminator(char ch) {
        switch (ch) {
            case '\u2028': // Line Separator
            case '\u2029': // Paragraph Separator
                return false;
            default:
                return isLineTerminator(ch);
        }
    }

    private TreeLocation getTokenRange(SourcePosition position) {
        lineNumberScanner.rewindTo(position);
        return lineNumberScanner.getSourceRange(position.offset, index);
    }

    private Token scanPostDigit(int beginToken) {
        skipDecimalDigits();
        if (peek('n')) {
            nextChar();
            return new LiteralToken(TokenType.BIGINT, getTokenString(beginToken), getTokenRange(beginToken));
        }
        return scanFractionalNumericLiteral(beginToken);
    }

    private LiteralToken scanFractionalNumericLiteral(int beginToken) {
        if (peek('.')) {
            nextChar();
            skipDecimalDigits();
        }
        return scanExponentOfNumericLiteral(beginToken);
    }

    private boolean peek(char ch) {
        return peekChar() == ch;
    }

    private Token scanNumberPostPeriod(int beginToken) {
        skipDecimalDigits();
        return scanExponentOfNumericLiteral(beginToken);
    }

    private LiteralToken scanExponentOfNumericLiteral(int beginToken) {
        switch (peekChar()) {
            case 'e':
            case 'E':
                nextChar();
                switch (peekChar()) {
                    case '+':
                    case '-':
                        nextChar();
                        break;
                    default: // fall out
                }
                if (!isDecimalDigit(peekChar())) {
                    // TODO -
                    // reportError("Exponent part must contain at least one digit");
                    throw new UnsupportedOperationException("Exponent part must contain at least one digit");
                }
                skipDecimalDigits();
                break;
            default:
                break;
        }
        return new LiteralToken(TokenType.LITERAL, getTokenString(beginToken), getTokenRange(beginToken));
    }

    private String getTokenString(int beginIndex) {
        return this.contents.substring(beginIndex, index);
    }

    private void skipDecimalDigits() {
        char ch = peekChar();
        while (isDecimalDigit(ch) || ch == '_') {
            nextChar();
            if (ch == '_') {
                if (isDecimalDigit(peekChar())) {
                    nextChar();
                } else {
                    // TODO -
                    // reportError("Trailing numeric separator");
                    throw new UnsupportedOperationException("Trailing numeric separator");
                }
            }
            ch = peekChar();
        }
    }

    private boolean isDecimalDigit(char peekChar) {
        switch (peekChar) {
            case '0':
            case '1':
            case '2':
            case '3':
            case '4':
            case '5':
            case '6':
            case '7':
            case '8':
            case '9':
                return true;
            default:
                return false;
        }
    }

    private Token createToken(TokenType type, int beginToken) {
        return new Token(type, getTokenRange(beginToken));
    }

    private TreeLocation getTokenRange(int beginToken) {
        return lineNumberScanner.getSourceRange(beginToken, index);
    }

    private void skipComments() {
        while (skipComment()) {
        }
    }

    private boolean skipComment() {
        skipWhitespace();
        if (!isAtEnd() && peekChar(0) == '/') {
            switch (peekChar(1)) {
                case '/':
                    skipSingleLineComment();
                    return true;
                case '*':
                    skipMultiLineComment();
                    return true;
                default:
            }
        }
        return false;
    }

    private void skipMultiLineComment() {
        int startOffset = index;
        nextChar(); // '/'
        nextChar(); // '*'

        while (true) {
            if (isAtEnd()) {
                throw new UnsupportedOperationException("unterminated comment");
            }
            char currentChar = nextChar();
            if (currentChar == '*' && peekChar() == '/') {
                nextChar(); // Consume the '/'
                break;
            }
        }

        Comment.Type type = Comment.Type.BLOCK;
        if (index - startOffset > 4 && contents.charAt(startOffset + 2) == '*') {
            type = Comment.Type.DOC;
        }

        TreeLocation range = lineNumberScanner.getSourceRange(startOffset, index);
        String value = contents.substring(startOffset, index);
        recordComment(type, value, range);
    }

    private void skipSingleLineComment() {
        skipSingleLineComment(Comment.Type.LINE);
    }

    private void skipSingleLineComment(Comment.Type type) {
        int startOffset = index;
        while (!isAtEnd() && !isLineTerminator(peekChar())) {
            nextChar();
        }
        TreeLocation range = lineNumberScanner.getSourceRange(startOffset, index);
        String value = this.contents.substring(startOffset, index);
        recordComment(type, value, range);
    }

    private void recordComment(Comment.Type type, String value, TreeLocation range) {
        commentRecorder.recordComment(type, value, range);
    }

    private boolean skipWhitespace() {
        boolean foundLineTerminator = false;
        while (!isAtEnd() && peekWhitespace()) {
            if (isLineTerminator(nextChar())) {
                foundLineTerminator = true;
            }
        }
        return foundLineTerminator;
    }

    private boolean isLineTerminator(char nextChar) {
        switch (nextChar) {
            case '\n': // New Line
            case '\r': // Carriage Return
                return true;
            default:
                return false;
        }
    }

    private char nextChar() {
        if (isAtEnd()) {
            return '\0';
        }
        return contents.charAt(index++);
    }

    private boolean peekWhitespace() {
        return isWhitespace(peekChar());
    }

    private boolean isWhitespace(char peekChar) {
        switch (peekChar) {
            case '\t': // Tab
            case ' ': // Space
            case '\n': // New Line
            case '\r': // Carriage Return
                return true;
            default:
                return false;
        }
    }

    private char peekChar() {
        return peekChar(0);
    }

    private char peekChar(int offset) {
        return !isValidIndex(index + offset) ? '\0' : contents.charAt(index + offset);
    }

    private boolean isAtEnd() {
        return !isValidIndex(index);
    }

    private boolean isValidIndex(int index) {
        return index >= 0 && index < contentsLength;
    }

    public interface CommentRecorder {
        void recordComment(Comment.Type type, String value, TreeLocation range);
    }

    public Token nextToken() {
        prevToken = peekToken();
        return tokensList.remove(0);
    }

    public Token prevToken() {
        return prevToken;
    }

}
