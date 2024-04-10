package com.boldbit.bitharvester.Harvester.compiler.trees;

import com.boldbit.bitharvester.Harvester.compiler.token.Token;
import com.boldbit.bitharvester.Harvester.compiler.token.TreeLocation;

public class ExpressionDoubleTree extends ParseTree {
    public final ParseTree expression;
    public final Token binaryOperator;
    public final ParseTree expression2;

    public ExpressionDoubleTree(ParseTree expression, Token binaryOperator, ParseTree expression2,
            TreeLocation treeLocation) {
        super(ParseTreeType.EXPRESSION, treeLocation);
        this.expression = expression;
        this.binaryOperator = binaryOperator;
        this.expression2 = expression2;
    }
}
