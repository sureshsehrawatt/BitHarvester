package com.boldbit.bitharvester.Harvester.compiler.trees;

import java.util.ArrayList;

import com.boldbit.bitharvester.Harvester.compiler.token.IdentifierToken;
import com.boldbit.bitharvester.Harvester.compiler.token.TreeLocation;
import com.boldbit.bitharvester.Harvester.compiler.token.Token;

public class ClassDeclarationTree extends ParseTree {

    public final ArrayList<Token> modifiers;
    public final IdentifierToken className;
    public final IdentifierToken superClass;
    public final ArrayList<ParseTree> elements;

    public ClassDeclarationTree(
        ArrayList<Token> modifiers, IdentifierToken className, IdentifierToken superClass, ArrayList<ParseTree> elements, TreeLocation treeLocation) {

        super(ParseTreeType.CLASS_DECLARATION, treeLocation);
        this.modifiers = modifiers;
        this.className = className;
        this.superClass = superClass;
        this.elements = elements;
    }

}
