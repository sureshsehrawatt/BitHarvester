package com.boldbit.bitharvester.Harvester.compiler.trees;

import com.boldbit.bitharvester.Harvester.compiler.token.IdentifierToken;
import com.boldbit.bitharvester.Harvester.compiler.token.SourceRange;
import com.boldbit.bitharvester.Harvester.compiler.token.Token;

public class ParameterDeclarationTree extends ParseTree{
    
    public final Token type;
    public final IdentifierToken name;
    public final Token value;

    public ParameterDeclarationTree(Token type, IdentifierToken name, Token value, SourceRange sourceRange) {
        super(ParseTreeType.METHOD_PARAMETER_DECLARATION, sourceRange);
        this.type = type;
        this.name = name;
        this.value = value;
    }
    
}
