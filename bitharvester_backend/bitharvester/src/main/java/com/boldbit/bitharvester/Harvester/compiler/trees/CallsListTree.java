package com.boldbit.bitharvester.Harvester.compiler.trees;

import java.util.ArrayList;

public class CallsListTree extends ParseTree {
    public final ArrayList<ParseTree> methodCallsList;

    public CallsListTree(ArrayList<ParseTree> methodCallsList) {
        super(ParseTreeType.METHOD_DECLARATION_LIST, null);
        this.methodCallsList = methodCallsList;
    }
}
