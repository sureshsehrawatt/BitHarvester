package com.boldbit.bitharvester.Harvester.compiler.trees;

import java.util.ArrayList;

import com.boldbit.bitharvester.Harvester.compiler.token.IdentifierToken;
import com.boldbit.bitharvester.Harvester.compiler.token.TreeLocation;

public class ConstructorDeclarationTree extends ParseTree {

    public final IdentifierToken name;
    public final ArrayList<ParseTree> formalParameters;
    public final ParseTree body;

    public ConstructorDeclarationTree(IdentifierToken name, ArrayList<ParseTree> formalParameters, ParseTree body,TreeLocation treeLocation) {
        super(ParseTreeType.CONSTRUCTOR_STATEMENT, treeLocation);
        this.name = name;
        this.formalParameters = formalParameters;
        this.body = body;
    }

}
