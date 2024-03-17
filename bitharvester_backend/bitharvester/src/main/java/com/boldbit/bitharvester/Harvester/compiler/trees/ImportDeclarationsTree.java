package com.boldbit.bitharvester.Harvester.compiler.trees;

import java.util.ArrayList;

import com.boldbit.bitharvester.Harvester.compiler.token.TreeLocation;

public class ImportDeclarationsTree extends ParseTree{

    public final ArrayList<ParseTree> importDeclarationsTrees;

    public ImportDeclarationsTree(ArrayList<ParseTree> importDeclarationsTrees, TreeLocation treeLocation) {
        super(ParseTreeType.IMPORT_DECLARATION, treeLocation);
        this.importDeclarationsTrees = importDeclarationsTrees;
    }
    
}
