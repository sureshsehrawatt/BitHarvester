package com.boldbit.bitharvester.Harvester.compiler.trees;

import com.boldbit.bitharvester.Harvester.compiler.token.ClassIdentifierToken;
import com.boldbit.bitharvester.Harvester.compiler.token.TreeLocation;

public class ClassIdentifierExpressionTree extends ParseTree {
    public final ClassIdentifierToken classIdentifierToken;

    public ClassIdentifierExpressionTree(ClassIdentifierToken classIdentifierToken, TreeLocation treeLocation) {
        super(ParseTreeType.IDENTIFIER_EXPRESSION, treeLocation);
        this.classIdentifierToken = classIdentifierToken;
    }
}
