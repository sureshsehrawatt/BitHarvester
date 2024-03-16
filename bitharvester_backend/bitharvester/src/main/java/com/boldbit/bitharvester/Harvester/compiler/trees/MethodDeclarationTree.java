package com.boldbit.bitharvester.Harvester.compiler.trees;

import java.util.ArrayList;

import com.boldbit.bitharvester.Harvester.compiler.token.SourceRange;
import com.boldbit.bitharvester.Harvester.compiler.token.Token;

public class MethodDeclarationTree extends ParseTree {

    public final ArrayList<Token> modifiersList;
    public final boolean isStatic;
    public final MethodSignatureTree methodSignatureTree;
    public final ParseTree methodBody;

    public MethodDeclarationTree(ArrayList<Token> modifiersList, boolean isStatic, MethodSignatureTree methodSignatureTree, ParseTree methodBody, SourceRange sourceRange) {
        super(ParseTreeType.METHOD_DECLARATION, sourceRange);
        this.modifiersList = modifiersList;
        this.isStatic = isStatic;
        this.methodSignatureTree = methodSignatureTree;
        this.methodBody = methodBody;
    }

}
