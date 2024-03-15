package com.boldbit.bitharvester.Harvester.compiler.trees;

import java.util.ArrayList;

import com.boldbit.bitharvester.Harvester.compiler.token.SourceRange;
import com.boldbit.bitharvester.Harvester.compiler.token.Token;

public class MethodDeclarationTree extends ParseTree {

    public final ArrayList<Token> modifiersList;
    public final boolean isStatic;
    public final MethodSignatureTree methodSignatureTree;
    public final ParseTree functionBody;

    public MethodDeclarationTree(ArrayList<Token> modifiersList, boolean isStatic, MethodSignatureTree methodSignatureTree, ParseTree functionBody, SourceRange sourceRange) {
        super(ParseTreeType.METHOD_DECLARATION, sourceRange);
        this.modifiersList = modifiersList;
        this.isStatic = isStatic;
        this.methodSignatureTree = methodSignatureTree;
        this.functionBody = functionBody;
    }

}
