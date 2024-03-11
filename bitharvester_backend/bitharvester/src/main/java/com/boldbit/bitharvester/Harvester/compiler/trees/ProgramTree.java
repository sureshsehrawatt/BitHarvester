package com.boldbit.bitharvester.Harvester.compiler.trees;

import com.boldbit.bitharvester.Harvester.compiler.token.SourceRange;

import java.util.ArrayList;

import com.boldbit.bitharvester.Harvester.compiler.token.Comment;

public class ProgramTree extends ParseTree{

    // TODO - use ImmutableList insted of ArrayList
    // public final ImmutableList<ParseTree> sourceElements;
    // public final ImmutableList<Comment> sourceComments;
    
    public final ArrayList<ParseTree> sourceElements;
    public final ArrayList<Comment> sourceComments;

    public ProgramTree(ArrayList<ParseTree> sourceElements, ArrayList<Comment> sourceComments, SourceRange sourceRange) {
        super(ParseTreeType.PROGRAM, sourceRange);
        this.sourceElements = sourceElements;
        this.sourceComments = sourceComments;
    }
    
}
