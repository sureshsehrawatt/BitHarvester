package com.boldbit.bitharvester.Harvester.compiler.trees;

import com.boldbit.bitharvester.Harvester.compiler.token.TreeLocation;

public class ForStatementTree extends ParseTree{
    
    public final ParseTree initialization; 
    public final ParseTree condition; 
    public final ParseTree update;
    public final ParseTree body;

    public ForStatementTree(ParseTree initialization, ParseTree condition, ParseTree update, ParseTree body,
    TreeLocation treeLocation) {
        super(ParseTreeType.FOR_STATEMENT, treeLocation);
        this.initialization = initialization;
        this.condition = condition;
        this.update = update;
        this.body = body;

    }
    
}
