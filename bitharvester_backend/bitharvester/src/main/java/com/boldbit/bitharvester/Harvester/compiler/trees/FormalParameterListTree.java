package com.boldbit.bitharvester.Harvester.compiler.trees;

import java.util.ArrayList;

import com.boldbit.bitharvester.Harvester.compiler.token.SourceRange;

public class FormalParameterListTree extends ParseTree{

    public final ArrayList<ParseTree> parameters;

    public FormalParameterListTree(ArrayList<ParseTree> parameters, SourceRange sourceRange) {
        super(ParseTreeType.FORMAL_PARAMETER_LIST, sourceRange);
        this.parameters = parameters;
    }

}
