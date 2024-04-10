package com.boldbit.bitharvester.Harvester.compiler.trees;

import com.boldbit.bitharvester.Harvester.compiler.token.IdentifierToken;
import com.boldbit.bitharvester.Harvester.compiler.token.TreeLocation;

public class IdentifierExpressionTree extends ParseTree {
    public final IdentifierToken identifierToken;

    public IdentifierExpressionTree(IdentifierToken identifierToken, TreeLocation treeLocation) {
        super(ParseTreeType.IDENTIFIER_EXPRESSION, treeLocation);
        this.identifierToken = identifierToken;
    }
}
