package com.boldbit.bitharvester.Harvester.compiler.trees;

import com.boldbit.bitharvester.Harvester.compiler.token.TreeLocation;

public class ForStatementTree extends ParseTree {

    public final ParseTree forControl;
    public final ParseTree body;

    public ForStatementTree(ParseTree forControl, ParseTree body, TreeLocation treeLocation) {
        super(ParseTreeType.FOR_STATEMENT, treeLocation);
        this.forControl = forControl;
        this.body = body;
    }
}
