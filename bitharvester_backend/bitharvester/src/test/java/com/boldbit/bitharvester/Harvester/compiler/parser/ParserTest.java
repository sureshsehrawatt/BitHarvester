package com.boldbit.bitharvester.Harvester.compiler.parser;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import com.boldbit.bitharvester.Harvester.compiler.token.ClassIdentifierToken;
import com.boldbit.bitharvester.Harvester.compiler.token.SourceFile;
import com.boldbit.bitharvester.Harvester.compiler.token.TokenType;
import com.boldbit.bitharvester.Harvester.compiler.trees.ParseTree;
import com.boldbit.bitharvester.Harvester.compiler.trees.ParseTreeType;

public class ParserTest {

    private Parser parser;
    private ParseTree result;

    @BeforeEach
    public void setUp() {
        parser = new Parser(new SourceFile("", ""));
    }

    @Timeout(10)
    @ParameterizedTest(name = "Test: \"{0}\"")
    @ValueSource(strings = {
    // "class MyClass {}",
    })
    public void parseProgramTest(String test) {

    }

    @Timeout(10)
    @ParameterizedTest(name = "Test: \"{0}\"")
    @ValueSource(strings = {
            "package com.boldbit.bitharvester.Harvester.compiler.rough;"
    })
    public void parsePackageDeclarationTest(String test) {
        parser = new Parser(new SourceFile("", test));
        result = parser.parsePackageDeclaration();
        assertNotNull(result, "Parse tree should not be null");
        assertEquals(ParseTreeType.PACKAGE_DECLARARTION, result.parseTreeType, "");
    }

    @Timeout(10)
    @ParameterizedTest(name = "Test: \"{0}\"")
    @ValueSource(strings = {
            "import java.util.*;",
            "import java.util.ArrayList;",
            "import static java.lang.Math.*;",
    })
    public void parseImportDeclarationTest(String test) {
        parser = new Parser(new SourceFile("", test));
        result = parser.parseImportDeclaration();
        assertNotNull(result, "Parse tree should not be null");
        assertEquals(ParseTreeType.IMPORT_DECLARATION, result.parseTreeType, "");
    }

    @Timeout(10)
    @ParameterizedTest(name = "Test: \"{0}\"")
    @ValueSource(strings = {
            "class MyClass {}",
            "class MyClass extends SecondClass {}",
    // "class MyClass"
    })
    public void typeDeclarationTest(String test) {
        parser = new Parser(new SourceFile("", test));
        result = parser.typeDeclaration();
        assertNotNull(result, "Parse tree should not be null");
        assertEquals(ParseTreeType.CLASS_DECLARATION, result.parseTreeType, "");
    }

    @Timeout(10)
    @ParameterizedTest(name = "Test: \"{0}\"")
    @ValueSource(strings = {
            "int a;",
            "int a = 10;",
            "int a = b;",
            "public String str = \"Hello World!\";",
            "public static final String name = \"TEST\";",
    })
    public void parseVariableDeclarationsTest(String test) {
        ClassIdentifierToken classIdentifierToken = new ClassIdentifierToken("Test", null);
        ArrayList<ParseTree> variableList = new ArrayList<>();
        parser = new Parser(new SourceFile("", test));
        result = parser.classOrInterfaceBodyDeclaration(classIdentifierToken, variableList, false);
        // TODO: VARIABLE_DECLARATION -> FIELD_DECLARATION
        assertEquals(ParseTreeType.FIELD_DECLARATION, variableList.get(0).parseTreeType, "");
    }

    @Timeout(10)
    @ParameterizedTest(name = "Test: \"{0}\"")
    @ValueSource(strings = {
            "public static void main(String[] args) { }",
            "void sayHello();",
            "int sum(int a, int b)",
    })
    public void parseMethodDeclarationTest(String test) {
        ClassIdentifierToken classIdentifierToken = new ClassIdentifierToken("Test", null);
        parser = new Parser(new SourceFile("", test));
        result = parser.classOrInterfaceBodyDeclaration(classIdentifierToken, null, false);
        assertNotNull(result, "Parse tree should not be null");
        assertEquals(ParseTreeType.METHOD_DECLARATION, result.parseTreeType, "");
    }

    @Timeout(10)
    @ParameterizedTest(name = "Test: \"{0}\"")
    @ValueSource(strings = {
            "{;}",
            "{ System.out.println(\"Hello World!\")};",
            "{ float dummy() { int a = b; } }",
    })
    public void parseBlockTest(String test) {
        parser = new Parser(new SourceFile("", test));
        result = parser.parseBlock(null);
        assertNotNull(result, "Parse tree should not be null");
        assertEquals(ParseTreeType.BLOCK, result.parseTreeType, "");
    }

    @Timeout(10)
    @ParameterizedTest(name = "Test: \"{0}\"")
    @ValueSource(strings = {
            "int a;",
            "int a = 10;",
            "int a = b;",
            "(String str = \"Hello World!\";",
            "(String name = \"TEST\";",
    })
    public void parseBlockVariablesTest(String test) {
        ArrayList<ParseTree> variableList = new ArrayList<>();
        parser = new Parser(new SourceFile("", test));

        if (parser.peekToken().tokenType == TokenType.OPEN_PAREN) {
            parser.eat(parser.peekToken().tokenType);
        }

        result = parser.parseBlockVariables(variableList);
        assertEquals(ParseTreeType.FIELD_DECLARATION, variableList.get(0).parseTreeType, "");
    }

    @Timeout(10)
    @ParameterizedTest(name = "Test: \"{0}\"")
    @ValueSource(strings = {
            "try{ int a = b; } catch(Exception e){ int a=c; } finally{ int a=d; }",
    })
    public void parseTryStatementTest(String test) {
        parser = new Parser(new SourceFile("", test));
        result = parser.parseTryStatement(null);
        assertNotNull(result, "Parse tree should not be null");
        assertEquals(ParseTreeType.TRY_STATEMENT, result.parseTreeType, "");
    }

    @Timeout(10)
    @ParameterizedTest(name = "Test: \"{0}\"")
    @ValueSource(strings = {
            "int[] arr = new int[10];",
            "int[] arr = new int[str.length()];",
            "int[] arr = {1, 2, 3}",
            "int arr[] = {1, 2, 3}",
    })
    public void parseArrayDeclarationTest(String test) {
        parser = new Parser(new SourceFile("", test));
        result = parser.classOrInterfaceBodyDeclaration(null, null, false);
        assertNotNull(result, "Parse tree should not be null");
        assertEquals(ParseTreeType.ARRAY_LITERAL_EXPRESSION, result.parseTreeType, "");
    }

    @Timeout(10)
    @ParameterizedTest(name = "Test: \"{0}\"")
    @ValueSource(strings = {
            "do{ }while(check);",
            "do{ }while(a>b);",
            "do{ }while(str.length());",
    })
    public void parseDoWhileStatementTest(String test) {
        parser = new Parser(new SourceFile("", test));
        result = parser.parseDoWhileStatement(null);
        assertNotNull(result, "Parse tree should not be null");
        assertEquals(ParseTreeType.DO_WHILE_STATEMENT, result.parseTreeType, "");
    }

    @Timeout(10)
    @ParameterizedTest(name = "Test: \"{0}\"")
    @ValueSource(strings = {
            "while(check){ }",
            "while(a>b){ }",
            "while(str.length()){ }",
    })
    public void parseWhileStatementTest(String test) {
        parser = new Parser(new SourceFile("", test));
        result = parser.parseWhileStatement(null);
        assertNotNull(result, "Parse tree should not be null");
        assertEquals(ParseTreeType.WHILE_STATEMENT, result.parseTreeType, "");
    }

    @Timeout(10)
    @ParameterizedTest(name = "Test: \"{0}\"")
    @ValueSource(strings = {
            "for(int i=0; i < j; i++){  }", // Basic for loop with initialization, condition, and update
            "for(int i = 0; i < 10; i++){ }", // Basic for loop with a fixed number of iterations
            "for(int i = 0, j = 0; i < 10; i++, j++){ }", // For loop with multiple initializations and updates
            "for(int i = 0; i < 10; ){  }", // For loop with empty update statement
            "for(int i = 0; ; i++){  }", // For loop with empty condition
            "for(int i = 0; i < 10; ){  }", // For loop with break statement inside
            "for(int i = 0; i < 10; i++){  }",
            "for(;;){ }", // Infinite loop
            "for(String str : strings){}", // Enhanced for loop (for-each loop) over an iterable
            "for(int i : array){}", // Enhanced for loop (for-each loop) over an array
    })
    public void parseForStatementTest(String test) {
        parser = new Parser(new SourceFile("", test));
        result = parser.parseForStatement(null);
        assertNotNull(result, "Parse tree should not be null");
        assertEquals(ParseTreeType.FOR_STATEMENT, result.parseTreeType, "");
    }

    @Timeout(10)
    @ParameterizedTest(name = "Test: \"{0}\"")
    @ValueSource(strings = {
            "(int a)",
            "(int a, int b)",
            "(int a, String str)",
            "(int a, int b[])",
    })
    public void formalParametersTest(String test) {
        parser = new Parser(new SourceFile("", test));
        ArrayList<ParseTree> formalParameters = parser.formalParameters();
        assertEquals(ParseTreeType.METHOD_PARAMETER_DECLARATION, formalParameters.get(0).parseTreeType, "");
    }

    @Timeout(10)
    @ParameterizedTest(name = "Test: \"{0}\"")
    @ValueSource(strings = {
            "if(a>b){ a -= b; }",
            "if(a>b){ a -= b; } else { a += b; }",
            "if(a>b){ a -= b; } else if (a<b){ a += b; }",
            "if(a>b){ a -= b; } else if (a<b){ a += b; } else { a *= b; }",
    })
    public void parseIfStatementTest(String test) {
        parser = new Parser(new SourceFile("", test));
        result = parser.parseIfStatement(null);
        assertNotNull(result, "Parse tree should not be null");
        assertEquals(ParseTreeType.IF_STATEMENT, result.parseTreeType, "");
    }

    @Timeout(10)
    @ParameterizedTest(name = "Test: \"{0}\"")
    @ValueSource(strings = {
            "(Example(int id, String name){ }",
    })
    public void parseConstructorDeclarationTest(String test) {
        parser = new Parser(new SourceFile("", test));
        parser.eat(TokenType.OPEN_PAREN);
        result = parser.parseConstructorDeclaration();
        assertNotNull(result, "Parse tree should not be null");
        assertEquals(ParseTreeType.CONSTRUCTOR_STATEMENT, result.parseTreeType, "");
    }

    @Timeout(10)
    @ParameterizedTest(name = "Test: \"{0}\"")
    @ValueSource(strings = {
            "i++, ++j)"
    })
    public void parseExpressionListTest(String test) {
        parser = new Parser(new SourceFile("", test));
        ArrayList<ParseTree> resultList = parser.parseExpressionList(null);
        assertNotNull(resultList, "Parse tree should not be null");
        assertEquals(ParseTreeType.EXPRESSION, resultList.get(0).parseTreeType, "");
    }

    @Timeout(10)
    @ParameterizedTest(name = "Test: \"{0}\"")
    @ValueSource(strings = {
            // ";",
            "a = b + c;",
            "a = b + (c * d);",
            "a = 11 + 22;",
            "obj();",
            "obj.sayHello(123);",
            "employee.id.name.firstname;",
            "employee.id.name();",
            "object.method(parameter1, parameter2, ..., parameterN);",
            "object1.object2.object3.field;",
            "a + b * c - d;",
            "string1 + string2;",
            "object.method().field;",
            "object.method1(object.method2());",
            "object.method(arg1, arg2, arg3);",
            "object.method(\"stringArgument\");",
            "object.method(123);"
    })
    public void parseExpressionTest(String input) {
        Parser parserEx = new Parser(new SourceFile("", input));
        ParseTree resultEx = parserEx.parseExpression(null);
        assertEquals(ParseTreeType.EXPRESSION, resultEx.parseTreeType);
    }

    @Timeout(10)
    @ParameterizedTest(name = "Test: \"{0}\"")
    @ValueSource(strings = {
            "(1)",
            "(1, 2)",
            "(a)",
            "(a, b)",
            "(str.length())",
            "(str.length(), a.size())",
            "(obj.getValue(a))",
    })
    public void parseArgumentsTest(String test) {
        parser = new Parser(new SourceFile("", test));
        result = parser.parseArguments();
        assertNotNull(result, "Parse tree should not be null");
        assertEquals(ParseTreeType.ARGUMENT_LIST, result.parseTreeType, "");
    }

}
