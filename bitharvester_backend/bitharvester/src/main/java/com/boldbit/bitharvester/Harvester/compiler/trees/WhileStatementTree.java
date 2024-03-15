package com.boldbit.bitharvester.Harvester.compiler.trees;

import java.util.ArrayList;

import com.boldbit.bitharvester.Harvester.compiler.token.SourceRange;

public class WhileStatementTree extends ParseTree {

    public final ParseTree condition;
    public final ArrayList<ParseTree> body;

    public WhileStatementTree(ParseTree condition, ArrayList<ParseTree> body, SourceRange sourceRange) {
        super(ParseTreeType.WHILE_STATEMENT, sourceRange);
        this.condition = condition;
        this.body = body;
    }
    
}
