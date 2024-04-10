package com.boldbit.bitharvester.Harvester.compiler.trees;

import com.boldbit.bitharvester.Harvester.compiler.token.Token;
import com.boldbit.bitharvester.Harvester.compiler.token.TreeLocation;

public class MethodCallTree extends ParseTree {
    public final Token methodIdentifier;
    public final ParseTree arguments;

    public MethodCallTree(Token methodIdentifier, ParseTree arguments, TreeLocation treeLocation) {
        super(ParseTreeType.METHOD_CALL, treeLocation);
        this.methodIdentifier = methodIdentifier;
        this.arguments = arguments;
    }
}
