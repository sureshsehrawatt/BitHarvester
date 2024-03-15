package com.boldbit.bitharvester.Harvester.compiler.trees;

import java.util.ArrayList;

import com.boldbit.bitharvester.Harvester.compiler.token.SourceRange;
import com.boldbit.bitharvester.Harvester.compiler.token.Token;

public class ImportDeclarationTree extends ParseTree {

    public final ArrayList<Token> qualifiedName;

    public ImportDeclarationTree(ParseTreeType parseTreeType, ArrayList<Token> qualifiedName, SourceRange sourceRange) {
        super(ParseTreeType.IMPORT_DECLARATION, sourceRange);
        this.qualifiedName = qualifiedName;
    }

}
