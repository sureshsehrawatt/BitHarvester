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
    public SourceRange sourceRange;

    public Comment(Type type, String value, SourceRange sourceRange){
        this.type = type;
        this.value = value;
        this.sourceRange = sourceRange;
    }
}
