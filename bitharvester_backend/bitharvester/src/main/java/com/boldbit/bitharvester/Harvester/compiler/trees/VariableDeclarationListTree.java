package com.boldbit.bitharvester.Harvester.compiler.trees;

import java.util.ArrayList;

import com.boldbit.bitharvester.Harvester.compiler.token.TreeLocation;

// FIXME : no use
public class VariableDeclarationListTree extends ParseTree {
    public final ArrayList<ParseTree> variableDeclarationsTrees;

    public VariableDeclarationListTree(ArrayList<ParseTree> variableDeclarationsTrees, TreeLocation treeLocation) {
        super(ParseTreeType.VARIABLE_DECLARATION_LIST, treeLocation);
        this.variableDeclarationsTrees = variableDeclarationsTrees;
    }
}
