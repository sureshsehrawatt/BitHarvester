package com.boldbit.bitharvester.Harvester.compiler.trees;

import com.boldbit.bitharvester.Harvester.compiler.token.TreeLocation;

public class IfStatementTree extends ParseTree {

    public final ParseTree condition;
    public final ParseTree ifClause;
    public final ParseTree elseClause;

    public IfStatementTree(ParseTree condition, ParseTree ifClause, ParseTree elseClause, TreeLocation treeLocation) {
        super(ParseTreeType.IF_STATEMENT, treeLocation);
        this.condition = condition;
        this.ifClause = ifClause;
        this.elseClause = elseClause;
    }

}
