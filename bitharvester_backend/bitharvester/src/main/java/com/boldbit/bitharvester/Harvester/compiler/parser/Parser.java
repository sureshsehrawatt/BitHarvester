package com.boldbit.bitharvester.Harvester.compiler.parser;

import java.util.ArrayList;
import java.util.Stack;

import com.boldbit.bitharvester.Harvester.compiler.lexer.Scanner;
import com.boldbit.bitharvester.Harvester.compiler.token.Comment.Type;
import com.boldbit.bitharvester.Harvester.compiler.token.Comment;
import com.boldbit.bitharvester.Harvester.compiler.token.IdentifierToken;
import com.boldbit.bitharvester.Harvester.compiler.token.Keywords;
import com.boldbit.bitharvester.Harvester.compiler.token.SourceFile;
import com.boldbit.bitharvester.Harvester.compiler.token.SourcePosition;
import com.boldbit.bitharvester.Harvester.compiler.token.SourceRange;
import com.boldbit.bitharvester.Harvester.compiler.token.Token;
import com.boldbit.bitharvester.Harvester.compiler.token.TokenType;
import com.boldbit.bitharvester.Harvester.compiler.trees.BlockTree;
import com.boldbit.bitharvester.Harvester.compiler.trees.ClassDeclarationTree;
import com.boldbit.bitharvester.Harvester.compiler.trees.FieldDeclarationTree;
import com.boldbit.bitharvester.Harvester.compiler.trees.ImportDeclarationTree;
import com.boldbit.bitharvester.Harvester.compiler.trees.MethodDeclarationTree;
import com.boldbit.bitharvester.Harvester.compiler.trees.PackageDeclarationTree;
import com.boldbit.bitharvester.Harvester.compiler.trees.ParameterDeclarationTree;
import com.boldbit.bitharvester.Harvester.compiler.trees.ParseTree;
import com.boldbit.bitharvester.Harvester.compiler.trees.ParseTreeType;
import com.boldbit.bitharvester.Harvester.compiler.trees.ProgramTree;

public class Parser {

    Scanner scanner;
    SourcePosition lastSourcePosition;
    CommentRecorder commentRecorder = new CommentRecorder();

    public Parser(SourceFile sourceFile) {
        this.scanner = new Scanner(sourceFile, 0, commentRecorder);
        lastSourcePosition = scanner.getPosition();
    }

    public ProgramTree parseProgram() {
        SourcePosition start = lastSourcePosition;
        ArrayList<ParseTree> sourceElements = SourceElements();

        eat(TokenType.END_OF_FILE);
        // FIXME - implement commentRecorder
        // return new ProgramTree(sourceElements, commentRecorder.getComments(), getTreeLocation(start));
        return new ProgramTree(sourceElements, null, getTreeLocation(start));

    }

    private Token eat(TokenType expectedTokenType) {
        Token token = nextToken();
        if (token.tokenType != expectedTokenType) {
            // reportExpectedError(token, expectedTokenType);
            return null;
        }
        return token;
    }

    private Token nextToken() {
        Token token = scanner.nextToken();
        lastSourcePosition = token.location.end;
        return token;
    }

    private SourceRange getTreeLocation(SourcePosition start) {
        return new SourceRange(start, getTreeEndLocation());
    }

    private SourcePosition getTreeEndLocation() {
        return lastSourcePosition;
    }

    private ArrayList<ParseTree> SourceElements() {
        ArrayList<ParseTree> result = new ArrayList<>();
        while (!peek(TokenType.END_OF_FILE)) {
            result.add(parseElement());
        }
        return result;
    }

    private ParseTree parseElement() {
        if (peekPackageDeclaration()) {
            return parsePackageDeclaration();
        } else if (peekImportDeclaration()) {
            return parseImportDeclaration();
        } else {
            return parseClassDeclaration();
        }
    }

    // class syntax
    /*
     * [access_modifier] class ClassName [extends SuperClassName] [implements
     * Interface1, Interface2, ...] {
     * 
     * Class variables (fields)
     * 
     * Constructors
     * 
     * Methods
     * }
     */
    private ParseTree parseClassDeclaration() {
        SourcePosition start = getTreeStartLocation();
        ArrayList<Token> modifiersList = new ArrayList<>();
        IdentifierToken className = null;
        IdentifierToken superClass = null;

        while (!peek(TokenType.CLASS) && !peek(TokenType.ENUM) && !peek(TokenType.INTERFACE)) {
            modifiersList.add(peekToken());
            eat(peekToken().tokenType);
        }

        if (peek(TokenType.CLASS)) {
            eat(TokenType.CLASS);
        }

        if (peek(TokenType.IDENTIFIER)) {
            className = (IdentifierToken) peekToken();
            eat(TokenType.IDENTIFIER);
        } else {
            System.out.println("Unexpected token in class declaration");
        }

        if (peek(TokenType.EXTENDS)) {
            eat(TokenType.EXTENDS);
            if (peek(TokenType.IDENTIFIER)) {
                superClass = (IdentifierToken) peekToken();
                eat(TokenType.IDENTIFIER);
            } else {
                System.out.println("Unexpected token in super class declaration");
            }
        }

        eat(TokenType.OPEN_CURLY);
        ArrayList<ParseTree> elements = parseClassElements();
        // eat(TokenType.CLOSE_CURLY);
        return new ClassDeclarationTree(modifiersList, className, superClass, elements, getTreeLocation(start));
    }

    private ArrayList<ParseTree> parseClassElements() {
        ArrayList<ParseTree> classElements = new ArrayList<>();
        while (!peek(TokenType.CLOSE_CURLY)) {
            Token token = peekToken();
            if (token.tokenType == TokenType.SEMI_COLON) {
                eat(TokenType.SEMI_COLON);
                continue;
            }
            classElements.add(parseClassElement());
        }
        eat(TokenType.CLOSE_CURLY);
        return classElements;
    }

    private ParseTree parseClassElement() {
        SourcePosition start = getTreeStartLocation();
        ArrayList<Token> modifiersList = new ArrayList<>();
        Token type = null;
        IdentifierToken name = null;
        while (peekModifier()) {
            modifiersList.add(peekToken());
            eat(peekToken().tokenType);
        }

        // inner class
        if (peek(TokenType.CLASS)) {
            // FIXME - pass modifiersList of class
            return parseClassDeclaration();
        }

        // TODO - implement constructor parsing
        // constructor
        if (peek(TokenType.N)) {
            return parseConstructorDeclaration();
        }

        // return type
        if (peekIdOrKeyword()) {
            type = eatIdOrKeywordAsId();
        }

        // name
        if (peek(TokenType.IDENTIFIER)) {
            name = (IdentifierToken) peekToken();
            eat(TokenType.IDENTIFIER);
        }

        // field
        if (peek(TokenType.SEMI_COLON) || peekAssignmentOperator()) {
            return parseFieldDeclaration(modifiersList, type, name, start);
        }

        // method
        if (peek(TokenType.OPEN_PAREN)) {
            return parseMethodDeclaration(modifiersList, type, name, start);
        }

        return null;
    }

    private ParseTree parseMethodDeclaration(ArrayList<Token> modifiersList, Token returnType, IdentifierToken name,
            SourcePosition start) {

        eat(TokenType.OPEN_PAREN);
        ArrayList<ParseTree> parameters = parseParameters();
        eat(TokenType.CLOSE_PAREN);

        BlockTree body = null;
        if (peek(TokenType.OPEN_CURLY)) {
            body = parseBlock();
        } else {
            System.out.println("Open curly not found");
            // eat(TokenType.SEMI_COLON);
        }

        return new MethodDeclarationTree(modifiersList, false, returnType, name, parameters, body,
                getTreeLocation(start));
    }

    private BlockTree parseBlock() {
        // FIXME -
        // {
        // SourcePosition start = getTreeStartLocation();
        // eat(TokenType.OPEN_CURLY);
        // // Spec says Statement list. However functions are also embedded in the wild.
        // ImmutableList<ParseTree> result = parseSourceElementList();
        // eat(TokenType.CLOSE_CURLY);
        // return new BlockTree(getTreeLocation(start), result);
        // }

        SourcePosition start = getTreeStartLocation();
        eat(TokenType.OPEN_CURLY);
        ArrayList<ParseTree> statements = new ArrayList<>();
        Stack<TokenType> stack = new Stack<>();
        stack.push(TokenType.OPEN_CURLY);

        while (stack.size() != 0) {
            TokenType tokenType = nextToken().tokenType;
            if (tokenType == TokenType.OPEN_CURLY) {
                stack.push(TokenType.OPEN_CURLY);
            } else if (tokenType == TokenType.CLOSE_CURLY) {
                stack.pop();
            } 
        }

        // eat(TokenType.CLOSE_CURLY);

        return new BlockTree(statements, getTreeLocation(start));
    }

    private ArrayList<ParseTree> parseParameters() {
        ArrayList<ParseTree> parameters = new ArrayList<>();
        while (!peek(TokenType.CLOSE_PAREN)) {
            SourcePosition start = getTreeStartLocation();
            if (!TokenType.peekType(peekToken().tokenType)) {
                break;
            }
            Token type = peekToken();
            nextToken();
            if (!peek(TokenType.IDENTIFIER)) {
                break;
            }
            IdentifierToken name = (IdentifierToken) eat(TokenType.IDENTIFIER);
            // FIXME - implement ParameterDeclarationTree
            parameters.add(new ParameterDeclarationTree(type, name, null, getTreeLocation(start)));
            if (peek(TokenType.COMMA)) {
                eat(TokenType.COMMA);
            } else {
                break;
            }
        }
        return parameters;
    }

    private ParseTree parseConstructorDeclaration() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'parseConstructorDeclaration'");
    }

    private ParseTree parseFieldDeclaration(ArrayList<Token> modifiersList, Token type, IdentifierToken name,
            SourcePosition start) {
        Token value = null;
        if (!peek(TokenType.SEMI_COLON)) {
            nextToken();
            value = peekToken();
            // TODO - fix this
            eat(TokenType.IDENTIFIER);
        }
        eat(TokenType.SEMI_COLON);
        return new FieldDeclarationTree(modifiersList, false, type, name, value, getTreeLocation(start));
    }

    private boolean peekAssignmentOperator() {
        return TokenType.peekAssignmentOperator(peekToken().tokenType);
    }

    private IdentifierToken eatIdOrKeywordAsId() {
        Token token = nextToken();
        if (token.tokenType == TokenType.IDENTIFIER) {
            return (IdentifierToken) token;
        } else if (Keywords.isKeyword(token.tokenType)) {
            return new IdentifierToken(Keywords.get(token.tokenType).toString(), token.location);
        } else {
            System.out.println("Excepetion in eatIdOrKeywordAsId");
        }
        return null;
    }

    private boolean peekIdOrKeyword() {
        return peekIdOrKeyword(0);
    }

    private boolean peekIdOrKeyword(int index) {
        TokenType type = peekType(index);
        return TokenType.IDENTIFIER == type || Keywords.isKeyword(type);
    }

    private boolean peekModifier() {
        return TokenType.peekModifier(peekToken().tokenType);
    }

    private ParseTree parseSourceElement() {
        System.out.println("Unimplemented method 'parseSourceElement'");
        return null;
    }

    private ParseTree parsePackageDeclaration() {
        SourcePosition start = getTreeStartLocation();
        eat(TokenType.PACKAGE);

        while (!peek(TokenType.SEMI_COLON)) {
            if (peek(TokenType.IDENTIFIER)) {
                eat(TokenType.IDENTIFIER);
            } else if (peek(TokenType.PERIOD)) {
                eat(TokenType.PERIOD);
            } else {
                System.out.println("Unexpected token in package declaration");
            }
        }
        eat(TokenType.SEMI_COLON);
        return new PackageDeclarationTree(ParseTreeType.PACKAGE_DECLARARTION, getTreeLocation(start));
    }

    private SourcePosition getTreeStartLocation() {
        return peekToken().location.start;
    }

    private Token peekToken() {
        return peekToken(0);
    }

    private boolean peekPackageDeclaration() {
        return peek(TokenType.PACKAGE);
    }

    private ParseTree parseImportDeclaration() {
        SourcePosition start = getTreeStartLocation();
        eat(TokenType.IMPORT);

        while (!peek(TokenType.SEMI_COLON)) {
            String packageName = "";
            if (peek(TokenType.IDENTIFIER)) {
                // Token token = peekToken();
                // packageName += token.location
                eat(TokenType.IDENTIFIER);
            } else {
                eat(TokenType.PERIOD);
            }
        }
        return new ImportDeclarationTree(ParseTreeType.IMPORT_DECLARATION, getTreeLocation(start));
    }

    private boolean peekImportDeclaration() {
        return peek(TokenType.IMPORT);
    }

    private boolean peek(TokenType expectedType) {
        return peek(0, expectedType);
    }

    private boolean peek(int index, TokenType expectedType) {
        return peekType(index) == expectedType;
    }

    private TokenType peekType(int index) {
        return peekToken(index).tokenType;
    }

    private Token peekToken(int index) {
        return scanner.peekToken(index);
    }

    class CommentRecorder implements Scanner.CommentRecorder {

        ArrayList<Comment> comments = new ArrayList<>();
        private SourcePosition lastCommentEndPosition;

        @Override
        public void recordComment(Type type, String value, SourceRange range) {
            if (lastCommentEndPosition == null || range.end.offset > this.lastCommentEndPosition.offset) {
                value = value.trim();
                comments.add(new Comment(type, value, range));
                this.lastCommentEndPosition = range.end;
            }
        }

        public ArrayList<Comment> getComments() {
            return comments;
        }

    }

}
