package com.boldbit.bitharvester.Harvester.compiler.trees;

import java.util.ArrayList;

import com.boldbit.bitharvester.Harvester.compiler.token.TreeLocation;
import com.boldbit.bitharvester.Harvester.compiler.token.Token;

public class MethodDeclarationTree extends ParseTree {

    public final ArrayList<Token> modifiersList;
    public final boolean isStatic;
    public final MethodSignatureTree methodSignatureTree;
    public final ParseTree body;

    public MethodDeclarationTree(ArrayList<Token> modifiersList, boolean isStatic, MethodSignatureTree methodSignatureTree, ParseTree body, TreeLocation treeLocation) {
        super(ParseTreeType.METHOD_DECLARATION, treeLocation);
        this.modifiersList = modifiersList;
        this.isStatic = isStatic;
        this.methodSignatureTree = methodSignatureTree;
        this.body = body;
    }

}
