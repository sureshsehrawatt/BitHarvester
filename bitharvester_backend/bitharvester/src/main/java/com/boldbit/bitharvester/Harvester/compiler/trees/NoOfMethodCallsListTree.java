package com.boldbit.bitharvester.Harvester.compiler.trees;

import java.util.ArrayList;

public class NoOfMethodCallsListTree extends ParseTree {
    public final ArrayList<ParseTree> noOfMethodCallsList;

    public NoOfMethodCallsListTree(ArrayList<ParseTree> noOfMethodCallsList) {
        super(ParseTreeType.METHOD_DECLARATION_LIST, null);
        this.noOfMethodCallsList = noOfMethodCallsList;
    }
}
