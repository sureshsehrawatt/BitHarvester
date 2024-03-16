package com.boldbit.bitharvester.Harvester.compiler.trees;

import com.boldbit.bitharvester.Harvester.compiler.token.TreeLocation;

public class DoWhileStatementTree extends ParseTree {

    public final ParseTree condition;
    public final ParseTree body;

    public DoWhileStatementTree(ParseTree body, ParseTree condition, TreeLocation treeLocation) {
        super(ParseTreeType.DO_WHILE_STATEMENT, treeLocation);
        this.body = body;
        this.condition = condition;
    }
    
}
