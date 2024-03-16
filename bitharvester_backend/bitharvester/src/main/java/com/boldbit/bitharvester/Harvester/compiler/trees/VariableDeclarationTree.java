package com.boldbit.bitharvester.Harvester.compiler.trees;

import java.util.ArrayList;

import com.boldbit.bitharvester.Harvester.compiler.token.IdentifierToken;
import com.boldbit.bitharvester.Harvester.compiler.token.TreeLocation;
import com.boldbit.bitharvester.Harvester.compiler.token.Token;

public class VariableDeclarationTree extends ParseTree{
    public final ArrayList<Token> modifiersList;
    public final Token type;
    public final IdentifierToken name;
    public final Token initializer;

    // FIXME - implement parseTree for initializer
    // public final ParseTreeType initializer;

    public VariableDeclarationTree(ArrayList<Token> modifiersList, Token type, IdentifierToken name, Token initializer, TreeLocation treeLocation) {
        super(ParseTreeType.FIELD_DECLARATION, treeLocation);
        this.modifiersList = modifiersList;
        this.type = type;
        this.name = name;
        this.initializer = initializer;
    }
    
}
