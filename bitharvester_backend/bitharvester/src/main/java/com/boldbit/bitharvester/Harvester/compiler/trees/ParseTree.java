package com.boldbit.bitharvester.Harvester.compiler.trees;

import com.boldbit.bitharvester.Harvester.compiler.token.TreeLocation;

public class ParseTree {
    public ParseTreeType parseTreeType;
    public TreeLocation treeLocation;

    public ParseTree(ParseTreeType parseTreeType, TreeLocation treeLocation){
        this.parseTreeType = parseTreeType;
        this.treeLocation = treeLocation;
    }
}
