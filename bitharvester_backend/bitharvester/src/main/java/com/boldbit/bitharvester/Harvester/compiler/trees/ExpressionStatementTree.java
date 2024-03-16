package com.boldbit.bitharvester.Harvester.compiler.trees;

import java.util.ArrayList;

import com.boldbit.bitharvester.Harvester.compiler.token.TreeLocation;

public class ExpressionStatementTree extends ParseTree {

    public final ArrayList<ExpressionTree> expressions;

    public ExpressionStatementTree(ArrayList<ExpressionTree> expressions, TreeLocation treeLocation) {
        super(ParseTreeType.EXPRESSION_STATEMENT, treeLocation);
        this.expressions = expressions;
    }
    
}
