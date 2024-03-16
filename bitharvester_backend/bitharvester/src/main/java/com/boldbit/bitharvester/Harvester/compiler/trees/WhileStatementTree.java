package com.boldbit.bitharvester.Harvester.compiler.trees;

import com.boldbit.bitharvester.Harvester.compiler.token.TreeLocation;

public class WhileStatementTree extends ParseTree {

    public final ParseTree condition;
    public final ParseTree body;

    public WhileStatementTree(ParseTree condition, ParseTree body, TreeLocation treeLocation) {
        super(ParseTreeType.WHILE_STATEMENT, treeLocation);
        this.condition = condition;
        this.body = body;
    }
    
}
