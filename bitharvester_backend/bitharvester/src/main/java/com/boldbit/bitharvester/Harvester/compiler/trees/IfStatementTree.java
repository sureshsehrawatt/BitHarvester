package com.boldbit.bitharvester.Harvester.compiler.trees;

import com.boldbit.bitharvester.Harvester.compiler.token.TreeLocation;

public class IfStatementTree extends ParseTree {

    public final ParseTree condition;
    public final ParseTree ifBody;
    public final ParseTree elseBody;

    public IfStatementTree(ParseTree condition, ParseTree ifBody, ParseTree elseBody, TreeLocation treeLocation) {
        super(ParseTreeType.IF_STATEMENT, treeLocation);
        this.condition = condition;
        this.ifBody = ifBody;
        this.elseBody = elseBody;
    }

}
