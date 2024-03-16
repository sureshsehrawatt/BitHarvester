package com.boldbit.bitharvester.Harvester.compiler.trees;

import java.util.ArrayList;

import com.boldbit.bitharvester.Harvester.compiler.token.TreeLocation;

public class BlockTree extends ParseTree{
    
    public final ArrayList<ParseTree> statements;
    
    public BlockTree(ArrayList<ParseTree> statements, TreeLocation treeLocation) {
        super(ParseTreeType.BLOCK, treeLocation);
        this.statements = statements;
    }
}
