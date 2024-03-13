package com.boldbit.bitharvester.Harvester.compiler.trees;

import java.util.ArrayList;

import com.boldbit.bitharvester.Harvester.compiler.token.SourceRange;

public class BlockTree extends ParseTree{
    
    public final ArrayList<ParseTree> statements;
    
    public BlockTree(ArrayList<ParseTree> statements, SourceRange sourceRange) {
        super(ParseTreeType.BLOCK, sourceRange);
        this.statements = statements;
    }
}
