package com.boldbit.bitharvester.Harvester.compiler.trees;

import java.util.ArrayList;

import com.boldbit.bitharvester.Harvester.compiler.token.TreeLocation;
import com.boldbit.bitharvester.Harvester.compiler.token.Token;

public class ImportDeclarationTree extends ParseTree {

    public enum ImportType {
        SINGLE_IMPORT,
        ON_DEMAND_IMPORT,
        STATIC_IMPORT
    }

    public final ArrayList<Token> qualifiedName;
    public final ImportType importType;

    public ImportDeclarationTree(ImportType importType, ArrayList<Token> qualifiedName, TreeLocation treeLocation) {
        super(ParseTreeType.IMPORT_DECLARATION, treeLocation);
        this.importType = importType;
        this.qualifiedName = qualifiedName;
    }

    public static ImportDeclarationTree singleImportDeclarationTree(ArrayList<Token> qualifiedName,
            TreeLocation treeLocation) {
        return new ImportDeclarationTree(ImportType.SINGLE_IMPORT, qualifiedName, treeLocation);
    }

    public static ImportDeclarationTree onDemandImportDeclarationTree(ArrayList<Token> qualifiedName,
            TreeLocation treeLocation) {
        return new ImportDeclarationTree(ImportType.ON_DEMAND_IMPORT, qualifiedName, treeLocation);
    }

    public static ImportDeclarationTree staticImportDeclarationTree(ArrayList<Token> qualifiedName,
            TreeLocation treeLocation) {
        return new ImportDeclarationTree(ImportType.STATIC_IMPORT, qualifiedName, treeLocation);
    }
}
