package com.boldbit.bitharvester.Harvester.compiler.trees;

import java.util.ArrayList;

import com.boldbit.bitharvester.Harvester.compiler.token.SourceRange;

public class ExpressionStatementTree extends ParseTree {

    public final ArrayList<ExpressionTree> expressions;

    public ExpressionStatementTree(ArrayList<ExpressionTree> expressions, SourceRange sourceRange) {
        super(ParseTreeType.EXPRESSION_STATEMENT, sourceRange);
        this.expressions = expressions;
    }
    
}
