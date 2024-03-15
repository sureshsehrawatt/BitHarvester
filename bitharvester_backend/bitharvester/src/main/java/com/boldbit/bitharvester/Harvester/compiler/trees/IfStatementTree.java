package com.boldbit.bitharvester.Harvester.compiler.trees;

import java.util.ArrayList;

import com.boldbit.bitharvester.Harvester.compiler.token.SourceRange;

public class IfStatementTree extends ParseTree {

    public final ParseTree condition;
    public final ArrayList<ParseTree> ifClause;
    public final ArrayList<ParseTree> elseClause;

    public IfStatementTree(ParseTree condition, ArrayList<ParseTree> ifClause, ArrayList<ParseTree> elseClause, SourceRange sourceRange) {
        super(ParseTreeType.IF_STATEMENT, sourceRange);
        this.condition = condition;
        this.ifClause = ifClause;
        this.elseClause = elseClause;
    }

}
