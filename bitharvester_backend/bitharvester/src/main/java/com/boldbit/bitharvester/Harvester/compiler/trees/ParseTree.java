package com.boldbit.bitharvester.Harvester.compiler.trees;

import com.boldbit.bitharvester.Harvester.compiler.token.SourceRange;

public class ParseTree {
    public ParseTreeType parseTreeType;
    public SourceRange sourceRange;

    public ParseTree(ParseTreeType parseTreeType, SourceRange sourceRange){
        this.parseTreeType = parseTreeType;
        this.sourceRange = sourceRange;
    }
}
