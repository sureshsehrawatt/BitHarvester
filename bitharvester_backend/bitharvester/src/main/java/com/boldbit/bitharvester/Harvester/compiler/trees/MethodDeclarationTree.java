package com.boldbit.bitharvester.Harvester.compiler.trees;

import java.util.ArrayList;

import com.boldbit.bitharvester.Harvester.compiler.token.IdentifierToken;
import com.boldbit.bitharvester.Harvester.compiler.token.SourceRange;
import com.boldbit.bitharvester.Harvester.compiler.token.Token;

public class MethodDeclarationTree extends ParseTree {

    public final ArrayList<Token> modifiersList;
    public final boolean isStatic;
    public final Token returnType;
    public final IdentifierToken name;
    public final ArrayList<ParseTree> parameterList;
    // FIXME - implement FormalParameterListTree
    // public final FormalParameterListTree formalParameterList;
    public final ParseTree functionBody;

    public MethodDeclarationTree(ArrayList<Token> modifiersList, boolean isStatic, Token returnType,
            IdentifierToken name, ArrayList<ParseTree> parameterList, ParseTree functionBody, SourceRange sourceRange) {
        super(ParseTreeType.METHOD_DECLARATION, sourceRange);
        this.modifiersList = modifiersList;
        this.isStatic = isStatic;
        this.returnType = returnType;
        this.name = name;
        this.parameterList = parameterList;
        this.functionBody = functionBody;
    }

}
