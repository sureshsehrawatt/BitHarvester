package com.boldbit.bitharvester.Harvester.compiler.parser;

import java.util.ArrayList;
import java.util.Collections;

import com.boldbit.bitharvester.Harvester.compiler.lexer.Scanner;
import com.boldbit.bitharvester.Harvester.compiler.token.Comment;
import com.boldbit.bitharvester.Harvester.compiler.token.Comment.Type;
import com.boldbit.bitharvester.Harvester.compiler.token.IdentifierToken;
// import com.boldbit.bitharvester.Harvester.compiler.token.Keywords;
import com.boldbit.bitharvester.Harvester.compiler.token.SourceFile;
import com.boldbit.bitharvester.Harvester.compiler.token.SourcePosition;
import com.boldbit.bitharvester.Harvester.compiler.token.SourceRange;
import com.boldbit.bitharvester.Harvester.compiler.token.Token;
import com.boldbit.bitharvester.Harvester.compiler.token.TokenType;
import com.boldbit.bitharvester.Harvester.compiler.trees.BlockTree;
import com.boldbit.bitharvester.Harvester.compiler.trees.ClassDeclarationTree;
import com.boldbit.bitharvester.Harvester.compiler.trees.ExpressionStatementTree;
import com.boldbit.bitharvester.Harvester.compiler.trees.ExpressionTree;
import com.boldbit.bitharvester.Harvester.compiler.trees.IfStatementTree;
// import com.boldbit.bitharvester.Harvester.compiler.trees.FieldDeclarationTree;
import com.boldbit.bitharvester.Harvester.compiler.trees.ImportDeclarationTree;
import com.boldbit.bitharvester.Harvester.compiler.trees.MethodDeclarationTree;
import com.boldbit.bitharvester.Harvester.compiler.trees.MethodSignatureTree;
import com.boldbit.bitharvester.Harvester.compiler.trees.PackageDeclarationTree;
import com.boldbit.bitharvester.Harvester.compiler.trees.ParameterDeclarationTree;
import com.boldbit.bitharvester.Harvester.compiler.trees.ParseTree;
import com.boldbit.bitharvester.Harvester.compiler.trees.ParseTreeType;
import com.boldbit.bitharvester.Harvester.compiler.trees.ProgramTree;
import com.boldbit.bitharvester.Harvester.compiler.trees.WhileStatementTree;

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
        // ArrayList<ParseTree> sourceElements = SourceElements();
        ArrayList<ParseTree> sourceElements = new ArrayList<>();
        ArrayList<ParseTree> importList = new ArrayList<>();

        if (peekPackageDeclaration()) {
            sourceElements.add(parsePackageDeclaration());
        }

        while (peekImportDeclaration()) {
            importList.add(parseImportDeclaration());
        }
        sourceElements.addAll(importList);

        while (!peek(TokenType.END_OF_FILE)) {
            sourceElements.add(typeDeclaration());
        }

        eat(TokenType.END_OF_FILE);
        // FIXME - implement commentRecorder
        // return new ProgramTree(sourceElements, commentRecorder.getComments(),
        // getTreeLocation(start));
        return new ProgramTree(sourceElements, null, getTreeLocation(start));

    }

    private ParseTree typeDeclaration() {
        SourcePosition start = getTreeStartLocation();
        ArrayList<Token> modifiersList = new ArrayList<>();

        if (isModifier()) {
            modifiersList = consumeModifiers();
        }
        return classOrInterfaceOrEnumDeclaration(modifiersList, start);
    }

    private ParseTree classOrInterfaceOrEnumDeclaration(ArrayList<Token> modifiersList, SourcePosition start) {

        if (peek(TokenType.CLASS)) {
            return classDeclaration(modifiersList, start);
        } else if (peek(TokenType.INTERFACE)) {
            // return interfaceDeclaration(modifiersList);
        } else if (peek(TokenType.ENUM)) {
            // return interfaceDeclaration(modifiersList);
        }
        return null;
    }

    private ParseTree classDeclaration(ArrayList<Token> modifiersList, SourcePosition start) {
        IdentifierToken className = null;
        IdentifierToken superClass = null;

        if (peek(TokenType.CLASS)) {
            eat(TokenType.CLASS);
        }

        if (peek(TokenType.IDENTIFIER)) {
            className = (IdentifierToken) peekToken();
            eat(TokenType.IDENTIFIER);
        } else {
            System.out.println("Unexpected token in class declaration");
        }

        // TODO - implement typeParametersOpt and IMPLEMENTS
        // List<JCTypeParameter> typarams = typeParametersOpt();

        // List<JCExpression> implementing = List.nil();
        // if (token.kind == IMPLEMENTS) {
        // nextToken();
        // implementing = typeList();
        // }

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

        // ArrayList<ParseTree> elements = parseClassElements();
        ArrayList<ParseTree> classBody = classOrInterfaceBody(className, false);
        return new ClassDeclarationTree(modifiersList, className, superClass, classBody, getTreeLocation(start));

    }

    private ArrayList<Token> consumeModifiers() {
        ArrayList<Token> modifiersList = new ArrayList<>();
        while (isModifier()) {
            modifiersList.add(peekToken());
            eat(peekToken().tokenType);
        }
        return modifiersList;
    }

    private ArrayList<ParseTree> classOrInterfaceBody(IdentifierToken className, boolean isInterface) {
        ArrayList<ParseTree> classElements = new ArrayList<>();
        while (!peek(TokenType.CLOSE_CURLY)) {
            Token token = peekToken();
            if (token.tokenType == TokenType.SEMI_COLON) {
                eat(TokenType.SEMI_COLON);
                continue;
            }
            classElements.add(classOrInterfaceBodyDeclaration(className, isInterface));
        }
        eat(TokenType.CLOSE_CURLY);
        return classElements;
    }

    private ParseTree classOrInterfaceBodyDeclaration(IdentifierToken className, boolean isInterface) {
        SourcePosition start = getTreeStartLocation();
        ArrayList<Token> modifiersList = new ArrayList<>();
        while (isModifier()) {
            modifiersList.add(peekToken());
            eat(peekToken().tokenType);
        }
        if (peek(TokenType.CLASS) || peek(TokenType.INTERFACE) || peek(TokenType.ENUM)) {
            return classOrInterfaceOrEnumDeclaration(modifiersList, start);
        }

        if (peek(TokenType.OPEN_CURLY)) {
            return parseBlock(start);
        }

        // TODO - implement typeParameters
        // List<JCTypeParameter> typeParameters = typeParametersOpt();

        // constructor
        if (peek(TokenType.IDENTIFIER)) {
            // TODO -
            return parseConstructorDeclaration();
            // return methodDeclaratorRest(pos, mods, type, name, typarams, isInterface,
            // isVoid, dc);
        }

        Token type = null;
        if (TokenType.isType(peekToken().tokenType)) {
            type = peekToken();
            eat(peekToken().tokenType);
        }

        IdentifierToken name = null;
        if (peek(TokenType.IDENTIFIER)) {
            name = (IdentifierToken) peekToken();
            eat(peekToken().tokenType);
        } 

        if (peek(TokenType.OPEN_PAREN)) {
            return parseMethodDeclaration(modifiersList, type, name, isInterface, start);
        }

        return null;
    }

    private ParseTree parseMethodDeclaration(ArrayList<Token> modifiersList, Token returnType, IdentifierToken name,
            boolean isInterface, SourcePosition start) {
        ArrayList<ParseTree> formalParameters = formalParameters();

        BlockTree body = null;
        if (peek(TokenType.OPEN_CURLY)) {
            body = parseBlock(start);
        } else {
            System.out.println("Open curly not found");
            // eat(TokenType.SEMI_COLON);
        }

        return new MethodDeclarationTree(modifiersList, false,
                new MethodSignatureTree(returnType, name, formalParameters, null), body, getTreeLocation(start));
    }

    private BlockTree parseBlock(SourcePosition start) {
        eat(TokenType.OPEN_CURLY);
        ArrayList<ParseTree> statements = blockStatements();
        eat(TokenType.CLOSE_CURLY);
        return new BlockTree(statements, getTreeLocation(start));
    }

    private ArrayList<ParseTree> blockStatements() {
        ArrayList<ParseTree> statements = new ArrayList<>();
        while (true) {
            ParseTree statement = blockStatement();
            if (statement == null) {
                return statements;
            } else {
                statements.add(statement);
            }
        }
    }

    private ParseTree blockStatement() {
        Token token = peekToken();
        switch (token.tokenType) {
            case CLOSE_CURLY:
            case CASE:
            case DEFAULT:
            case END_OF_FILE:
                return null;
            case OPEN_CURLY:
            case IF:
            case FOR:
            case WHILE:
            case DO:
            case TRY:
            case SWITCH:
            case SYNCHRONIZED:
            case RETURN:
            case THROW:
            case BREAK:
            case CONTINUE:
            case SEMI_COLON:
            case ELSE:
            case FINALLY:
            case CATCH:
                return parseStatement();
            default:
                break;
        }
        return null;
    }

    private ParseTree parseStatement() {
        SourcePosition start = getTreeStartLocation();
        switch (peekToken().tokenType) {
            case OPEN_CURLY:
                return parseBlock(start);
            case IF:
                return parseIfStatement(start);//
            case FOR:
                return parseForStatement(start);
            case WHILE:
                return parseWhileStatement(start);
            case DO:
                return parseDoWhileStatement(start);
            case TRY:
                return parseTryStatement(start);
            case SWITCH:
                return parseSwitchStatement(start);
            case SYNCHRONIZED:
                return parseSynchronizedBlock(start);
            case RETURN:
                return parseReturnStatement(start);
            case THROW:
                return parseThrowStatement(start);
            case BREAK:
                return parseBreakStatement(start);
            case CONTINUE:
                return parseContinueStatement(start);
            case SEMI_COLON:
                return parseEmptyStatement(start);
            case ELSE:
                // return recoverFromError(token.position, ErrorRecoveryAction.BLOCK_STMT,
                // "else.without.if");
            case FINALLY:
                // return recoverFromError(token.position, ErrorRecoveryAction.BLOCK_STMT,
                // "finally.without.try");
            case CATCH:
                // return recoverFromError(token.position, ErrorRecoveryAction.CATCH_CLAUSE,
                // "catch.without.try");
            case ASSERT:
                return parseAssertStatement(start);
            case ENUM:
                // Handle enum declarations
                return parseEnumDeclaration(start);
            default:
                return parseExpressionStatement(start);
        }
    }

    // private Statement recoverFromError(int startPosition, ErrorRecoveryAction
    // action, String errorMessage) {
    // int errorPosition = getErrorPosition();
    // Statement statement = action.recover(this);
    // setErrorPosition(errorPosition);
    // return syntaxError(startPosition, Collections.singletonList(statement),
    // errorMessage);
    // }

    private ParseTree parseAssertStatement(SourcePosition start) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'parseAssertStatement'");
    }

    private ParseTree parseEnumDeclaration(SourcePosition start) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'parseEnumDeclaration'");
    }

    private ParseTree parseEmptyStatement(SourcePosition start) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'parseEmptyStatement'");
    }

    private ParseTree parseContinueStatement(SourcePosition start) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'parseContinueStatement'");
    }

    private ParseTree parseBreakStatement(SourcePosition start) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'parseBreakStatement'");
    }

    private ParseTree parseThrowStatement(SourcePosition start) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'parseThrowStatement'");
    }

    private ParseTree parseReturnStatement(SourcePosition start) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'parseReturnStatement'");
    }

    private ParseTree parseSynchronizedBlock(SourcePosition start) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'parseSynchronizedBlock'");
    }

    private ParseTree parseSwitchStatement(SourcePosition start) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'parseSwitchStatement'");
    }

    private ParseTree parseTryStatement(SourcePosition start) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'parseTryStatement'");
    }

    private ParseTree parseDoWhileStatement(SourcePosition start) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'parseDoWhileStatement'");
    }

    private ParseTree parseWhileStatement(SourcePosition start) {
        eat(TokenType.WHILE);
        eat(TokenType.OPEN_PAREN);
        ParseTree condition = parseExpressionStatement(start);
        eat(TokenType.CLOSE_PAREN);
        ArrayList<ParseTree> body = blockStatements();
        return new WhileStatementTree(condition, body, getTreeLocation(start));
    }

    private ParseTree parseForStatement(SourcePosition start) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'parseForStatement'");
    }

    private ParseTree parseIfStatement(SourcePosition start) {
        eat(TokenType.IF);
        eat(TokenType.OPEN_PAREN);
        ParseTree condition = parseExpressionStatement(start);
        eat(TokenType.CLOSE_PAREN);
        ArrayList<ParseTree> ifClause = blockStatements();
        ArrayList<ParseTree> elseClause = null;
        if (peek(TokenType.ELSE)) {
            elseClause = blockStatements();
        }
        return new IfStatementTree(condition, ifClause, elseClause, getTreeLocation(start));
    }

    private ParseTree parseExpressionStatement(SourcePosition start) {
        return parsePrimaryExpression(start);
    }

    private ParseTree parsePrimaryExpression(SourcePosition start) {
        ArrayList<ExpressionTree> expressions = new ArrayList<>();
        ArrayList<Token> expresion = new ArrayList<>();

        while (!peek(TokenType.CLOSE_PAREN)) {
            if (peek(TokenType.IDENTIFIER)) {
                IdentifierToken oprn1 = (IdentifierToken) peekToken();
                eat(TokenType.IDENTIFIER);
                if (expressions.size() < 1 && peek(TokenType.CLOSE_PAREN)) {
                    expressions.add(new ExpressionTree(oprn1, getTreeLocation(start)));
                    break;
                } else if (isCompareOperator()) {
                    Token op = peekToken();
                    eat(peekToken().tokenType);
                    if (peek(TokenType.IDENTIFIER)) {
                        IdentifierToken oprn2 = (IdentifierToken) peekToken();
                        eat(peekToken().tokenType);
                        if (peek(TokenType.CLOSE_PAREN)) {
                            expressions.add(new ExpressionTree(oprn1, oprn2, op, getTreeLocation(start)));
                            break;
                        }
                        expressions.add(new ExpressionTree(oprn1, oprn2, op, getTreeLocation(start)));
                    } else if (peek(TokenType.OPEN_PAREN)) {
                        ParseTree t = parseParenthesizedExpression();
                        ExpressionTree et = (ExpressionTree) t;
                        expressions.add(et);
                    }
                }
                expresion.add(peekToken());
                // } else if (peek(TokenType.LITERAL)) {

            } else if (peek(TokenType.OPEN_PAREN)) {
                ParseTree t = parseParenthesizedExpression();
                ExpressionTree et = (ExpressionTree) t;
                expressions.add(et);
            } else {
                // Handle other types of expressions
            }
        }
        return new ExpressionStatementTree(expressions, getTreeLocation(start));

    }

    private boolean isCompareOperator() {
        return TokenType.isCompareOperator(peekToken().tokenType);
    }

    private ParseTree parseParenthesizedExpression() {
        SourcePosition start = getTreeStartLocation();
        eat(TokenType.OPEN_PAREN);
        ParseTree expression = parseExpressionStatement(start);
        eat(TokenType.CLOSE_PAREN);
        return expression;
    }

    private ArrayList<ParseTree> formalParameters() {
        eat(TokenType.OPEN_PAREN);
        ArrayList<ParseTree> parameters = new ArrayList<>();
        while (!peek(TokenType.CLOSE_PAREN)) {
            SourcePosition start = getTreeStartLocation();
            if (!TokenType.isType(peekToken().tokenType)) {
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
        eat(TokenType.CLOSE_PAREN);
        return parameters;
    }

    private ParseTree parseConstructorDeclaration() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'parseConstructorDeclaration'");
    }

    // private ParseTree parseFieldDeclaration(ArrayList<Token> modifiersList, Token
    // type, IdentifierToken name, SourcePosition start) {
    // Token value = null;
    // if (!peek(TokenType.SEMI_COLON)) {
    // nextToken();
    // value = peekToken();
    // // TODO - fix this
    // eat(TokenType.IDENTIFIER);
    // }
    // eat(TokenType.SEMI_COLON);
    // return new FieldDeclarationTree(modifiersList, false, type, name, value,
    // getTreeLocation(start));
    // }

    // private boolean peekAssignmentOperator() {
    // return TokenType.peekAssignmentOperator(peekToken().tokenType);
    // }

    // private IdentifierToken eatIdOrKeywordAsId() {
    // Token token = nextToken();
    // if (token.tokenType == TokenType.IDENTIFIER) {
    // return (IdentifierToken) token;
    // } else if (Keywords.isKeyword(token.tokenType)) {
    // return new IdentifierToken(Keywords.get(token.tokenType).toString(),
    // token.location);
    // } else {
    // System.out.println("Excepetion in eatIdOrKeywordAsId");
    // }
    // return null;
    // }

    // private boolean peekIdOrKeyword() {
    // return peekIdOrKeyword(0);
    // }

    // private boolean peekIdOrKeyword(int index) {
    // TokenType type = peekType(index);
    // return TokenType.IDENTIFIER == type || Keywords.isKeyword(type);
    // }

    private boolean isModifier() {
        return TokenType.isModifier(peekToken().tokenType);
    }

    private ParseTree parsePackageDeclaration() {
        SourcePosition start = getTreeStartLocation();
        eat(TokenType.PACKAGE);
        ArrayList<Token> qualifiedName = new ArrayList<>();

        while (!peek(TokenType.SEMI_COLON)) {
            if (peek(TokenType.IDENTIFIER)) {
                qualifiedName.add(peekToken());
                eat(TokenType.IDENTIFIER);
            } else if (peek(TokenType.PERIOD)) {
                eat(TokenType.PERIOD);
            } else {
                System.out.println("Unexpected token in package declaration");
            }
        }
        eat(TokenType.SEMI_COLON);
        return new PackageDeclarationTree(ParseTreeType.PACKAGE_DECLARARTION, qualifiedName, getTreeLocation(start));
    }

    private SourcePosition getTreeStartLocation() {
        return peekToken().location.start;
    }

    private Token peekToken() {
        return peekToken(0);
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

    private boolean peekPackageDeclaration() {
        return peek(TokenType.PACKAGE);
    }

    private ParseTree parseImportDeclaration() {
        SourcePosition start = getTreeStartLocation();
        eat(TokenType.IMPORT);
        ArrayList<Token> qualifiedName = new ArrayList<>();
        while (!peek(TokenType.SEMI_COLON)) {
            if (peek(TokenType.IDENTIFIER)) {
                qualifiedName.add(peekToken());
                eat(TokenType.IDENTIFIER);
            } else if (peek(TokenType.STAR)) {
                qualifiedName.add(peekToken());
                eat(TokenType.STAR);
            } else {
                eat(TokenType.PERIOD);
            }
        }
        eat(TokenType.SEMI_COLON);
        return new ImportDeclarationTree(ParseTreeType.IMPORT_DECLARATION, qualifiedName, getTreeLocation(start));
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
