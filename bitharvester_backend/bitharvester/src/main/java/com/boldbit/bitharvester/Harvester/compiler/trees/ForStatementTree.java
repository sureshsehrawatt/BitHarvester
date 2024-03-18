package com.boldbit.bitharvester.Harvester.compiler.trees;

import java.util.ArrayList;

import com.boldbit.bitharvester.Harvester.compiler.token.TreeLocation;

public class ForStatementTree extends ParseTree{
    
    public final ArrayList<ParseTree> initialization; 
    public final ParseTree condition; 
    public final ParseTree update;
    public final ParseTree body;

    public ForStatementTree(ArrayList<ParseTree> initialization, ParseTree condition, ParseTree update, ParseTree body,
    TreeLocation treeLocation) {
        super(ParseTreeType.FOR_STATEMENT, treeLocation);
        this.initialization = initialization;
        this.condition = condition;
        this.update = update;
        this.body = body;

    }
    
}
