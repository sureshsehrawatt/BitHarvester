package com.boldbit.bitharvester.Harvester.compiler.trees;

import java.util.ArrayList;

import com.boldbit.bitharvester.Harvester.compiler.token.TreeLocation;

public class ArgumentsTree extends ParseTree {
    public final ArrayList<ParseTree> arguments;

    public ArgumentsTree(ArrayList<ParseTree> arguments, TreeLocation treeLocation) {
        super(ParseTreeType.ARGUMENT_LIST, treeLocation);
        this.arguments = arguments;
    }
}
