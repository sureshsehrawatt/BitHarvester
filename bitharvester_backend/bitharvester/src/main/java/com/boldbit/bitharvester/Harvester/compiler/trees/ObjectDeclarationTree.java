package com.boldbit.bitharvester.Harvester.compiler.trees;

import java.util.ArrayList;

import com.boldbit.bitharvester.Harvester.compiler.token.IdentifierToken;
import com.boldbit.bitharvester.Harvester.compiler.token.Token;
import com.boldbit.bitharvester.Harvester.compiler.token.TreeLocation;

public class ObjectDeclarationTree extends ParseTree {
    public final ArrayList<Token> modifiersList;
    public final Token type;
    public final IdentifierToken name;
    public final ParseTree initializer;

    public ObjectDeclarationTree(ArrayList<Token> modifiersList, Token type, IdentifierToken name,
            ParseTree initializer, TreeLocation treeLocation) {

        super(ParseTreeType.OBJECT_DECLARATION, treeLocation);
        this.modifiersList = modifiersList;
        this.type = type;
        this.name = name;
        this.initializer = initializer;
    }
}
