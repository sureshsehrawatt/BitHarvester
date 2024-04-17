package com.boldbit.bitharvester.Harvester.compiler.trees;

import java.util.ArrayList;

import com.boldbit.bitharvester.Harvester.compiler.token.ClassIdentifierToken;
import com.boldbit.bitharvester.Harvester.compiler.token.TreeLocation;
import com.boldbit.bitharvester.Harvester.compiler.token.Token;

public class ClassDeclarationTree extends ParseTree {

    public final ArrayList<Token> modifiers;
    public final ClassIdentifierToken className;
    public final ClassIdentifierToken superClass;
    public final ClassElements classElements;

    public ClassDeclarationTree(
            ArrayList<Token> modifiers, ClassIdentifierToken className, ClassIdentifierToken superClass,
            ClassElements classElements, TreeLocation treeLocation) {

        super(ParseTreeType.CLASS_DECLARATION, treeLocation);
        this.modifiers = modifiers;
        this.className = className;
        this.superClass = superClass;
        this.classElements = classElements;
    }
}
