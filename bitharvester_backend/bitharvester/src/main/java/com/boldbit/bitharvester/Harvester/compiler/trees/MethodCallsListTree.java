package com.boldbit.bitharvester.Harvester.compiler.trees;

import java.util.ArrayList;

import com.boldbit.bitharvester.Harvester.compiler.token.TreeLocation;

public class MethodCallsListTree extends ParseTree {

    public final ParseTree expression;
    public final ArrayList<ParseTree> methodCallsInExpression;

    public MethodCallsListTree(ParseTree expression, ArrayList<ParseTree> methodCallsInExpression,
            TreeLocation treeLocation) {
        super(ParseTreeType.METHOD_CALLS_LIST, treeLocation);
        this.expression = expression;
        this.methodCallsInExpression = methodCallsInExpression;
    }
}
