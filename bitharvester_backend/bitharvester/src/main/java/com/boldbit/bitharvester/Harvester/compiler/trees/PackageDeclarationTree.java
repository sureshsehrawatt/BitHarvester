package com.boldbit.bitharvester.Harvester.compiler.trees;

import java.util.ArrayList;

import com.boldbit.bitharvester.Harvester.compiler.token.SourceRange;
import com.boldbit.bitharvester.Harvester.compiler.token.Token;

public class PackageDeclarationTree extends ParseTree {

    public final ArrayList<Token> qualifiedName;

    public PackageDeclarationTree(ParseTreeType parseTreeType, ArrayList<Token> qualifiedName,
            SourceRange sourceRange) {
        super(ParseTreeType.PACKAGE_DECLARARTION, sourceRange);
        this.qualifiedName = qualifiedName;
    }

}
