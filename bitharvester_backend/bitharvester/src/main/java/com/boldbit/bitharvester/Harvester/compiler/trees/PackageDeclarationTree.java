package com.boldbit.bitharvester.Harvester.compiler.trees;

import java.util.ArrayList;

import com.boldbit.bitharvester.Harvester.compiler.token.TreeLocation;
import com.boldbit.bitharvester.Harvester.compiler.token.Token;

public class PackageDeclarationTree extends ParseTree {

    public final ArrayList<Token> qualifiedName;

    public PackageDeclarationTree(ParseTreeType parseTreeType, ArrayList<Token> qualifiedName,
            TreeLocation treeLocation) {
        super(ParseTreeType.PACKAGE_DECLARARTION, treeLocation);
        this.qualifiedName = qualifiedName;
    }

}
