package com.boldbit.bitharvester.Harvester.compiler.trees;

import java.util.ArrayList;

import com.boldbit.bitharvester.Harvester.compiler.token.TreeLocation;

public class FormalParameterListTree extends ParseTree{

    public final ArrayList<ParseTree> parameters;

    public FormalParameterListTree(ArrayList<ParseTree> parameters, TreeLocation treeLocation) {
        super(ParseTreeType.FORMAL_PARAMETER_LIST, treeLocation);
        this.parameters = parameters;
    }

}
