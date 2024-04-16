package com.boldbit.bitharvester.Harvester.compiler.trees;

import com.boldbit.bitharvester.Harvester.compiler.token.TreeLocation;

public class EnhancedForControlTree extends ParseTree{
    public final VariableDeclarationTree initialization;

    public EnhancedForControlTree(VariableDeclarationTree initialization, TreeLocation treeLocation) {
        super(ParseTreeType.ENHANCED_FOR_CONTROL, treeLocation);
        this.initialization = initialization;
    }
}
