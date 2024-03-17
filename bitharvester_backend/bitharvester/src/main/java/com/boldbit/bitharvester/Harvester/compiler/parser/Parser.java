package com.boldbit.bitharvester.Harvester.compiler.parser;

import java.util.ArrayList;

import com.boldbit.bitharvester.Harvester.compiler.lexer.Scanner;
import com.boldbit.bitharvester.Harvester.compiler.token.Comment;
import com.boldbit.bitharvester.Harvester.compiler.token.Comment.Type;
import com.boldbit.bitharvester.Harvester.compiler.token.IdentifierToken;
import com.boldbit.bitharvester.Harvester.compiler.token.LiteralToken;
import com.boldbit.bitharvester.Harvester.compiler.token.SourceFile;
import com.boldbit.bitharvester.Harvester.compiler.token.SourcePosition;
import com.boldbit.bitharvester.Harvester.compiler.token.TreeLocation;
import com.boldbit.bitharvester.Harvester.compiler.token.Token;
import com.boldbit.bitharvester.Harvester.compiler.token.TokenType;
import com.boldbit.bitharvester.Harvester.compiler.trees.ArrayDeclarationTree;
import com.boldbit.bitharvester.Harvester.compiler.trees.BlockTree;
import com.boldbit.bitharvester.Harvester.compiler.trees.ClassDeclarationTree;
import com.boldbit.bitharvester.Harvester.compiler.trees.ConstructorDeclarationTree;
import com.boldbit.bitharvester.Harvester.compiler.trees.DoWhileStatementTree;
import com.boldbit.bitharvester.Harvester.compiler.trees.ExpressionStatementTree;
import com.boldbit.bitharvester.Harvester.compiler.trees.ExpressionTree;
import com.boldbit.bitharvester.Harvester.compiler.trees.ForStatementTree;
import com.boldbit.bitharvester.Harvester.compiler.trees.IfStatementTree;
import com.boldbit.bitharvester.Harvester.compiler.trees.ImportDeclarationTree;
import com.boldbit.bitharvester.Harvester.compiler.trees.MethodDeclarationTree;
import com.boldbit.bitharvester.Harvester.compiler.trees.MethodSignatureTree;
import com.boldbit.bitharvester.Harvester.compiler.trees.PackageDeclarationTree;
import com.boldbit.bitharvester.Harvester.compiler.trees.ParameterDeclarationTree;
import com.boldbit.bitharvester.Harvester.compiler.trees.ParseTree;
import com.boldbit.bitharvester.Harvester.compiler.trees.ProgramTree;
import com.boldbit.bitharvester.Harvester.compiler.trees.TryStatementTree;
import com.boldbit.bitharvester.Harvester.compiler.trees.VariableDeclarationTree;
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
        ArrayList<ParseTree> variablesList = new ArrayList<>();
        // TODO -
        // ArrayList<ParseTree> methodsList = new ArrayList<>();

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

        ArrayList<ParseTree> classBody = classOrInterfaceBody(className, variablesList, false);
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

    private ArrayList<ParseTree> classOrInterfaceBody(IdentifierToken className, ArrayList<ParseTree> variablesList,
            boolean isInterface) {
        ArrayList<ParseTree> classElements = new ArrayList<>();
        while (!peek(TokenType.CLOSE_CURLY)) {
            Token token = peekToken();
            if (token.tokenType == TokenType.SEMI_COLON) {
                eat(TokenType.SEMI_COLON);
                continue;
            }
            ParseTree pt = classOrInterfaceBodyDeclaration(className, variablesList, isInterface);
            if (pt == null && variablesList.size() > 0) {
                classElements.addAll(variablesList);
                variablesList.clear();
            } else {
                classElements.add(pt);
            }
        }
        eat(TokenType.CLOSE_CURLY);
        return classElements;
    }

    private ParseTree classOrInterfaceBodyDeclaration(IdentifierToken className, ArrayList<ParseTree> variablesList,
            boolean isInterface) {
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
            IdentifierToken t = (IdentifierToken) peekToken();
            if (t.value.equals(className.value)) {
                // TODO -
                return parseConstructorDeclaration();
            }
            // return methodDeclaratorRest(pos, mods, type, name, typarams, isInterface,
            // isVoid, dc);
        }

        Token type = null;
        if (TokenType.isType(peekToken().tokenType)) {
            type = peekToken();
            eat(peekToken().tokenType);
            if (peek(TokenType.OPEN_SQUARE)) {
                return parseArrayDeclaration(modifiersList, type, null, start);
            }
        }

        IdentifierToken name = null;
        if (peek(TokenType.IDENTIFIER)) {
            name = (IdentifierToken) peekToken();
            eat(peekToken().tokenType);
            if (peek(TokenType.OPEN_SQUARE)) {
                return parseArrayDeclaration(modifiersList, type, name, start);
            }
        }

        if (peek(TokenType.OPEN_PAREN)) {
            return parseMethodDeclaration(modifiersList, type, name, isInterface, start);
        }

        if (peek(TokenType.EQUAL) || peek(TokenType.SEMI_COLON)) {
            return parseVariableDeclarations(modifiersList, type, name, variablesList, start);
        }

        return null;
    }

    private ParseTree parseVariableDeclarations(ArrayList<Token> modifiersList, Token type, IdentifierToken name,
            ArrayList<ParseTree> variablesList, SourcePosition start) {
        variablesList.add(parseVariableDeclaration(modifiersList, type, name, start));
        while (peek(TokenType.COMMA)) {
            nextToken();
            variablesList.add(parseVariableDeclarationRest(modifiersList, type, start));
        }
        return null;
    }

    private ParseTree parseVariableDeclarationRest(ArrayList<Token> modifiersList, Token type, SourcePosition start) {
        IdentifierToken name = (IdentifierToken) peekToken();
        eat(TokenType.IDENTIFIER);
        return parseVariableDeclaration(modifiersList, type, name, start);
    }

    private ParseTree parseVariableDeclaration(ArrayList<Token> modifiersList, Token type, IdentifierToken name,
            SourcePosition start) {
        // TODO - type
        if (peek(TokenType.SEMI_COLON)) {
            eat(TokenType.SEMI_COLON);
            return new VariableDeclarationTree(modifiersList, type, name, null, getTreeLocation(start));
        }

        if (peek(TokenType.EQUAL)) {
            Token init = null;
            eat(TokenType.EQUAL);
            if (peek(TokenType.IDENTIFIER)) {
                init = peekToken();
                eat(TokenType.IDENTIFIER);
                return new VariableDeclarationTree(modifiersList, type, name, init, getTreeLocation(start));
            } else if (peek(TokenType.LITERAL)) {
                init = peekToken();
                eat(TokenType.LITERAL);
                return new VariableDeclarationTree(modifiersList, type, name, init, getTreeLocation(start));
            }
            return new VariableDeclarationTree(modifiersList, type, name, init, getTreeLocation(start));
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
        ArrayList<ParseTree> variablesList = new ArrayList<>();
        ArrayList<ParseTree> statements = blockStatements(variablesList);
        eat(TokenType.CLOSE_CURLY);
        return new BlockTree(statements, getTreeLocation(start));
    }

    private ArrayList<ParseTree> blockStatements(ArrayList<ParseTree> variablesList) {
        ArrayList<ParseTree> statements = new ArrayList<>();
        while (true) {
            if (peekToken().tokenType == TokenType.SEMI_COLON) {
                eat(TokenType.SEMI_COLON);
                continue;
            }
            ParseTree statement = blockStatement(variablesList);
            if (variablesList.size() > 0) {
                statements.addAll(variablesList);
                variablesList.clear();
            } else if (statement == null) {
                return statements;
            } else {
                statements.add(statement);
            }

        }
    }

    private ParseTree blockStatement(ArrayList<ParseTree> variablesList) {
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
                return parseBlockVariables(variablesList);
        }
    }

    private ParseTree parseBlockVariables(ArrayList<ParseTree> variablesList) {
        SourcePosition start = getTreeStartLocation();
        ArrayList<Token> modifiersList = new ArrayList<>();
        while (isModifier()) {
            modifiersList.add(peekToken());
            eat(peekToken().tokenType);
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
        return parseVariableDeclarations(modifiersList, type, name, variablesList, start);
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

    private ParseTree parseArrayDeclaration(ArrayList<Token> modifiersList, Token type, IdentifierToken name,
            SourcePosition start) {
        eat(TokenType.OPEN_SQUARE);
        ArrayList<ParseTree> elements = new ArrayList<>();
        if (name == null) {
            if (peek(TokenType.CLOSE_SQUARE)) {
                eat(TokenType.CLOSE_SQUARE);
            }
            if (peek(TokenType.IDENTIFIER)) {
                name = (IdentifierToken) peekToken();
                eat(peekToken().tokenType);
            }
            if (peek(TokenType.SEMI_COLON)) {
                eat(TokenType.SEMI_COLON);
                return new ArrayDeclarationTree(modifiersList, type, name, elements, getTreeLocation(start));
            }
        } else if (peek(TokenType.SEMI_COLON)) {
            eat(TokenType.SEMI_COLON);
            return new ArrayDeclarationTree(modifiersList, type, name, elements, getTreeLocation(start));
        }
        if (peek(TokenType.EQUAL)) {
            eat(TokenType.EQUAL);
            if (peek(TokenType.NEW)) {
                eat(TokenType.NEW);
                eat(type.tokenType);
                eat(TokenType.OPEN_SQUARE);
                if (peek(TokenType.LITERAL)) {
                    LiteralToken literalToken = (LiteralToken) peekToken();
                    int value = Integer.parseInt(literalToken.value);
                    while (value > 0) {
                        elements.add(new ParseTree(null, null));
                        value--;
                    }
                }
                eat(TokenType.CLOSE_SQUARE);
                eat(TokenType.SEMI_COLON);
                return new ArrayDeclarationTree(modifiersList, type, name, elements, getTreeLocation(start));
            } else if (peek(TokenType.EQUAL)) {

            }
        }
        eat(TokenType.CLOSE_SQUARE);
        return null;
    }

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
        eat(TokenType.TRY);
        ParseTree tryBody = parseBlock(start);
        ParseTree catchBody = null;
        ParseTree finallyBody = null;
        if (peek(TokenType.CATCH)) {
            SourcePosition catchStart = getTreeStartLocation();
            eat(TokenType.CATCH);
            // TODO -
            ParseTree ex = parseParenthesizedExpression();
            catchBody = parseBlock(catchStart);
        } else {
            System.out.println("Missing catch block");
        }

        if (peek(TokenType.CATCH)) {
            SourcePosition finallyStart = getTreeStartLocation();
            finallyBody = parseBlock(finallyStart);
        }
        return new TryStatementTree(tryBody, catchBody, finallyBody, getTreeLocation(start));
    }

    private ParseTree parseDoWhileStatement(SourcePosition start) {
        eat(TokenType.DO);
        ParseTree body = parseBlock(start);
        eat(TokenType.WHILE);
        eat(TokenType.OPEN_PAREN);
        ParseTree condition = parseExpressionStatement(start);
        eat(TokenType.CLOSE_PAREN);
        eat(TokenType.SEMI_COLON);
        return new DoWhileStatementTree(body, condition, getTreeLocation(start));
    }

    private ParseTree parseWhileStatement(SourcePosition start) {
        eat(TokenType.WHILE);
        eat(TokenType.OPEN_PAREN);
        ParseTree condition = parseExpressionStatement(start);
        eat(TokenType.CLOSE_PAREN);
        ParseTree body = parseBlock(start);
        return new WhileStatementTree(condition, body, getTreeLocation(start));
    }

    private ParseTree parseForStatement(SourcePosition start) {
        eat(TokenType.FOR);
        eat(TokenType.OPEN_PAREN);
        ParseTree initialization = parseFormalVariable();
        eat(TokenType.SEMI_COLON);
        ParseTree condition = parseExpressionStatement(start);
        eat(TokenType.SEMI_COLON);
        ParseTree update = parseExpressionStatement(start);
        eat(TokenType.CLOSE_PAREN);
        ParseTree body = parseBlock(start);
        return new ForStatementTree(initialization, condition, update, body, getTreeLocation(start));
    }

    private ParseTree parseIfStatement(SourcePosition start) {
        eat(TokenType.IF);
        eat(TokenType.OPEN_PAREN);
        ParseTree condition = parseExpressionStatement(start);
        eat(TokenType.CLOSE_PAREN);
        ParseTree ifClause = parseBlock(start);
        ParseTree elseClause = null;
        if (peek(TokenType.ELSE)) {
            SourcePosition endClauseStart = getTreeStartLocation();
            elseClause = parseBlock(endClauseStart);
        }
        return new IfStatementTree(condition, ifClause, elseClause, getTreeLocation(start));
    }

    private ParseTree parseExpressionStatement(SourcePosition start) {
        ArrayList<ExpressionTree> expressions = new ArrayList<>();
        ArrayList<Token> expresion = new ArrayList<>();

        while (!peek(TokenType.CLOSE_PAREN)) {
            if (peek(TokenType.IDENTIFIER)) {
                IdentifierToken oprn1 = (IdentifierToken) peekToken();
                eat(TokenType.IDENTIFIER);
                if (expressions.size() < 2 && peek(TokenType.CLOSE_PAREN)) {
                    expressions.add(new ExpressionTree(oprn1, getTreeLocation(start)));
                    break;
                } else if (peek(TokenType.PLUS_PLUS) || peek(TokenType.MINUS_MINUS)) {
                    Token op = peekToken();
                    eat(peekToken().tokenType);
                    if (peek(TokenType.CLOSE_PAREN)) {
                        expressions.add(new ExpressionTree(oprn1, op, getTreeLocation(start)));
                        break;
                    }
                    expressions.add(new ExpressionTree(oprn1, op, getTreeLocation(start)));
                } else if (isCompareOperator() || isOperator()) {
                    Token op = peekToken();
                    eat(peekToken().tokenType);
                    if (peek(TokenType.IDENTIFIER) || peek(TokenType.LITERAL)) {
                        Token oprn2 = peekToken();
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

            } else if (peek(TokenType.OPEN_PAREN)) {
                ParseTree t = parseParenthesizedExpression();
                ExpressionTree et = (ExpressionTree) t;
                expressions.add(et);
            } else if (TokenType.isType(peekToken().tokenType)) {
                // TODO -
                // ParseTree var = parseFormalVariable();
                // expressions.add((ExpressionTree) parseFormalVariable());
            } else if (peek(TokenType.SEMI_COLON)) {
                return new ExpressionStatementTree(expressions, getTreeLocation(start));
            }
        }
        return new ExpressionStatementTree(expressions, getTreeLocation(start));
    }

    private ParseTree parseFormalVariable() {
        SourcePosition start = getTreeStartLocation();
        ArrayList<Token> modifiersList = new ArrayList<>();
        while (isModifier()) {
            modifiersList.add(peekToken());
            eat(peekToken().tokenType);
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

        Token init = null;
        if (peek(TokenType.EQUAL)) {
            eat(TokenType.EQUAL);
            if (peek(TokenType.IDENTIFIER) || peek(TokenType.LITERAL)) {
                init = peekToken();
                eat(init.tokenType);
            }
        }

        if (name != null && type != null) {
            return new VariableDeclarationTree(modifiersList, type, name, init, getTreeLocation(start));
        }

        return null;
    }

    private boolean isCompareOperator() {
        return TokenType.isCompareOperator(peekToken().tokenType);
    }

    private boolean isOperator() {
        return TokenType.isOperator(peekToken().tokenType);
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
            IdentifierToken name = null;
            if (!TokenType.isType(peekToken().tokenType)) {
                break;
            }
            Token type = peekToken();
            nextToken();
            if (peek(TokenType.OPEN_SQUARE)) {
                eat(TokenType.OPEN_SQUARE);
                eat(TokenType.CLOSE_SQUARE);
                name = (IdentifierToken) eat(TokenType.IDENTIFIER);
                parameters.add(new ArrayDeclarationTree(null, type, name, null, getTreeLocation(start)));
            } else if (!peek(TokenType.IDENTIFIER)) {
                break;
            }

            if (peek(TokenType.IDENTIFIER)) {
                name = (IdentifierToken) eat(TokenType.IDENTIFIER);
                if (peek(TokenType.OPEN_SQUARE)) {
                    eat(TokenType.OPEN_SQUARE);
                    parameters.add(new ArrayDeclarationTree(null, type, name, null, getTreeLocation(start)));
                    eat(TokenType.CLOSE_SQUARE);
                } else {
                    // FIXME - implement ParameterDeclarationTree
                    parameters.add(new ParameterDeclarationTree(type, name, null, getTreeLocation(start)));
                }
            }

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
        SourcePosition start = getTreeStartLocation();
        IdentifierToken name = (IdentifierToken) peekToken();
        eat(TokenType.IDENTIFIER);
        ArrayList<ParseTree> formalParameters = formalParameters();
        ParseTree body = parseBlock(start);
        return new ConstructorDeclarationTree(name, formalParameters, body, getTreeLocation(start));
    }

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
        return new PackageDeclarationTree(qualifiedName, getTreeLocation(start));
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

    private TreeLocation getTreeLocation(SourcePosition start) {
        return new TreeLocation(start, getTreeEndLocation());
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
        return new ImportDeclarationTree(qualifiedName, getTreeLocation(start));
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
        public void recordComment(Type type, String value, TreeLocation range) {
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
