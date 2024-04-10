package com.boldbit.bitharvester.Harvester.compiler.trees;

import com.boldbit.bitharvester.Harvester.compiler.token.Token;
import com.boldbit.bitharvester.Harvester.compiler.token.TreeLocation;

public class LiteralExpressionTree extends ParseTree {
    public final Token literalToken;

    public LiteralExpressionTree(Token literalToken, TreeLocation treeLocation) {
        super(ParseTreeType.LITERAL_EXPRESSION, treeLocation);
        this.literalToken = literalToken;
    }
}
