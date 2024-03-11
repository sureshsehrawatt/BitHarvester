package com.boldbit.bitharvester.Harvester.compiler.parser;

import java.util.ArrayList;
import java.util.List;

import com.boldbit.bitharvester.Harvester.compiler.lexer.Scanner;
import com.boldbit.bitharvester.Harvester.compiler.token.Comment.Type;
import com.boldbit.bitharvester.Harvester.compiler.token.LiteralToken;
import com.boldbit.bitharvester.Harvester.compiler.token.Comment;
import com.boldbit.bitharvester.Harvester.compiler.token.SourceFile;
import com.boldbit.bitharvester.Harvester.compiler.token.SourcePosition;
import com.boldbit.bitharvester.Harvester.compiler.token.SourceRange;
import com.boldbit.bitharvester.Harvester.compiler.token.Token;
import com.boldbit.bitharvester.Harvester.compiler.token.TokenType;
import com.boldbit.bitharvester.Harvester.compiler.trees.ImportDeclarationTree;
import com.boldbit.bitharvester.Harvester.compiler.trees.PackageDeclarationTree;
import com.boldbit.bitharvester.Harvester.compiler.trees.ParseTree;
import com.boldbit.bitharvester.Harvester.compiler.trees.ParseTreeType;
import com.boldbit.bitharvester.Harvester.compiler.trees.ProgramTree;

public class Parser {

    Scanner scanner;
    SourcePosition lastSourcePosition;

    public Parser(SourceFile sourceFile) {
        this.scanner = new Scanner(sourceFile, 0);
        lastSourcePosition = scanner.getPosition();
    }

    public ProgramTree parseProgram() {
        SourcePosition start = lastSourcePosition;
        ArrayList<ParseTree> sourceElements = SourceElements();

        eat(TokenType.END_OF_FILE);
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
        }

        if (peekImportDeclaration()) {
            return parseImportDeclaration();
        }

        if (peekClassDeclaration()) {
            return parseClassDeclaration();
        }

        return parseSourceElement();
    }

    private ParseTree parseClassDeclaration() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'parseClassDeclaration'");
    }

    private boolean peekClassDeclaration() {
        return peek(TokenType.CLASS);
    }

    private ParseTree parseSourceElement() {
        // TODO Auto-generated method stub
        System.out.println("Unimplemented method 'parseSourceElement'");
        throw new UnsupportedOperationException("Unimplemented method 'parseSourceElement'");
    }

    private ParseTree parsePackageDeclaration() {
        SourcePosition start = getTreeStartLocation();
        eat(TokenType.PACKAGE);

        while (!peek(TokenType.SEMI_COLON)) {
            String packageName = "";
            if (peek(TokenType.IDENTIFIER)) {
                // Token token = peekToken();
                // packageName += token.location
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

        List<Comment> comments;
        private SourcePosition lastCommentEndPosition;

        @Override
        public void recordComment(Type type, String value, SourceRange range) {
            if (lastCommentEndPosition == null || range.end.offset > this.lastCommentEndPosition.offset) {
                value = value.trim();
                comments.add(new Comment(type, value, range));
                this.lastCommentEndPosition = range.end;
            }
        }

        public List<Comment> getComments() {
            return comments;
        }

    }

}
