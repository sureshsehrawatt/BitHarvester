package com.boldbit.bitharvester.Harvester.compiler.trees;

import com.boldbit.bitharvester.Harvester.compiler.token.SourceRange;

public class DoWhileStatementTree extends ParseTree {

    public final ParseTree condition;
    public final ParseTree body;

    public DoWhileStatementTree(ParseTree body, ParseTree condition, SourceRange sourceRange) {
        super(ParseTreeType.DO_WHILE_STATEMENT, sourceRange);
        this.body = body;
        this.condition = condition;
    }
    
}
