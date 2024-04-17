package com.boldbit.bitharvester.Harvester.compiler.trees;

import java.util.ArrayList;

public class VariablesListTree extends ParseTree {
    public final ArrayList<ParseTree> variablesList;

    public VariablesListTree(ArrayList<ParseTree> variablesList) {
        super(ParseTreeType.VARIABLE_DECLARATION_LIST, null);
        this.variablesList = variablesList;
    }
}
