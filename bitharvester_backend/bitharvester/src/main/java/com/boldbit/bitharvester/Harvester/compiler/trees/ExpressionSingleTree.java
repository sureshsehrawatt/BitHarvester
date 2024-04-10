package com.boldbit.bitharvester.Harvester.compiler.trees;

import com.boldbit.bitharvester.Harvester.compiler.token.TreeLocation;

public class ExpressionSingleTree extends ParseTree {
    public final ParseTree expression;

    public ExpressionSingleTree(ParseTree expression, TreeLocation treeLocation) {
        super(ParseTreeType.EXPRESSION, treeLocation);
        this.expression = expression;
    }
}
