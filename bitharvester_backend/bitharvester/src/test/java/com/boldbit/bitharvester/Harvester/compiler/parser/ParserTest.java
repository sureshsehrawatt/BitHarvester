package com.boldbit.bitharvester.Harvester.compiler.parser;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import com.boldbit.bitharvester.Harvester.compiler.token.SourceFile;
import com.boldbit.bitharvester.Harvester.compiler.trees.ClassDeclarationTree;
import com.boldbit.bitharvester.Harvester.compiler.trees.ForStatementTree;
import com.boldbit.bitharvester.Harvester.compiler.trees.ParseTree;

public class ParserTest {

    private Parser parser;
    private ParseTree result;

    @BeforeEach
    public void setUp() {
        parser = new Parser(new SourceFile("", ""));
    }

    // @Timeout(1)
    @ParameterizedTest(name = "Test: \"{0}\"")
    @ValueSource(strings = {
            "class MyClass {}",
            "class MyClass extends SecondClass {}",
    // "class MyClass"
    })
    public void testTypeDeclaration(String input) {
        parser = new Parser(new SourceFile("", input));
        // assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
        result = parser.typeDeclaration();
        assertNotNull(result, "Parse tree should not be null");
        assertTrue(result instanceof ClassDeclarationTree, "Result should be an instance of ClassDeclarationTree");
        // });
    }

    @Timeout(10)
    @ParameterizedTest(name = "Test: \"{0}\"")
    @ValueSource(strings = {
            "for(int i=0; i < j; i++){  }", // Basic for loop with initialization, condition, and update
            "for(;;){ }", // Infinite loop
            "for(int i = 0; i < 10; i++){ }", // Basic for loop with a fixed number of iterations
            "for(int i = 0, j = 0; i < 10; i++, j++){ }", // For loop with multiple initializations and updates
            // "for(String str : strings){}", // Enhanced for loop (for-each loop) over an
            // iterable
            // "for(int i : array){}", // Enhanced for loop (for-each loop) over an array
            "for(int i = 0; i < 10; ){  }", // For loop with empty update statement
            "for(int i = 0; ; i++){  }", // For loop with empty condition
            "for(int i = 0; i < 10; ){  }", // For loop with break statement inside
            "for(int i = 0; i < 10; i++){  }"
    })
    public void parseForStatementTest(String input) {
        parser = new Parser(new SourceFile("", input));
        // assertTimeoutPreemptively(Duration.ofSeconds(10), () -> {
        result = parser.parseForStatement(null);
        assertNotNull(result, "Parse tree should not be null");
        assertTrue(result instanceof ForStatementTree, "Result should be an instance of ClassDeclarationTree");
        // });
    }

    @Timeout(10)
    @ParameterizedTest(name = "Test: \"{0}\"")
    @ValueSource(strings = {
            ";",
            "a = b + c;",
            "a = b + (c * d);",
            "a = 11 + 22;",
            "obj();",
            "obj.sayHello(123);",
            ";",
    // "employee.id.name.firstname;",
    // "employee.id.name();",
    // "object.method(parameter1, parameter2, ..., parameterN);",
    // "object1.object2.object3.field;",
    // "a + b * c - d;",
    // "string1 + string2;",
    // "object.method().field;",
    // "object.method1(object.method2());",
    // "object.method(arg1, arg2, arg3);",
    // "object.method(\"stringArgument\");",
    // "object.method(123);"
    })
    public void parseExpressionTest(String input) {
        Parser parserEx = new Parser(new SourceFile("", input));
        ParseTree resultEx = parserEx.parseExpression(null);
        System.out.println("Test result:- " + resultEx);
    }
}
