package com.boldbit.bitharvester.Harvester.compiler.trees;

import java.util.ArrayList;

import com.boldbit.bitharvester.Harvester.compiler.token.TreeLocation;
import com.boldbit.bitharvester.Harvester.compiler.token.Token;

public class ImportDeclarationTree extends ParseTree {

    public final ArrayList<Token> qualifiedName;

    public ImportDeclarationTree(ParseTreeType parseTreeType, ArrayList<Token> qualifiedName, TreeLocation treeLocation) {
        super(ParseTreeType.IMPORT_DECLARATION, treeLocation);
        this.qualifiedName = qualifiedName;
    }

}
