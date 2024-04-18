package com.boldbit.bitharvester.Harvester.compiler.trees;

import com.boldbit.bitharvester.Harvester.compiler.token.TreeLocation;

public class ClassElements extends ParseTree {

    public final VariablesListTree variablesListTree;
    public final CallsListTree callsListTree;
    public final MethodsListTree methodsListTree;

    public ClassElements(VariablesListTree variablesListTree, CallsListTree callsListTree,
            MethodsListTree methodsListTree, TreeLocation treeLocation) {
        super(ParseTreeType.CLASS_ELEMENTS, treeLocation);
        this.variablesListTree = variablesListTree;
        this.callsListTree = callsListTree;
        this.methodsListTree = methodsListTree;
    }
}
