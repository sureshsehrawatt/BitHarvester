package com.boldbit.bitharvester.Harvester.compiler.trees;

import java.util.ArrayList;

import com.boldbit.bitharvester.Harvester.compiler.token.TreeLocation;

public class ForControlTree extends ParseTree {

    public final ArrayList<ParseTree> initialization;
    public final ParseTree condition;
    public final ArrayList<ParseTree> update;

    public ForControlTree(ArrayList<ParseTree> initialization, ParseTree condition, ArrayList<ParseTree> update,
            TreeLocation treeLocation) {
        super(ParseTreeType.FOR_CONTROL, treeLocation);
        this.initialization = initialization;
        this.condition = condition;
        this.update = update;
    }
}
