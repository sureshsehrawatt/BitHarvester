package com.boldbit.bitharvester.Harvester.compiler.token;

public class Comment {
    public static enum Type {
        // // comment
        LINE,

        // /* comment */
        BLOCK,

        // /** comment */
        DOC,
        
        // TODO
        // /** comment */
        TODO,
        
        // /** comment */
        FIXME,
    }

    public Type type;
    public String value;
    public TreeLocation treeLocation;

    public Comment(Type type, String value, TreeLocation treeLocation){
        this.type = type;
        this.value = value;
        this.treeLocation = treeLocation;
    }
}
