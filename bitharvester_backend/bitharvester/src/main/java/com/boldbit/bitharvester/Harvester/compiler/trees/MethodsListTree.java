package com.boldbit.bitharvester.Harvester.compiler.trees;

import java.util.ArrayList;

public class MethodsListTree extends ParseTree {

    public final ArrayList<ParseTree> methodsList;

    public MethodsListTree(ArrayList<ParseTree> methodsList) {
        super(ParseTreeType.METHOD_DECLARATION_LIST, null);
        this.methodsList = methodsList;
    }
}
