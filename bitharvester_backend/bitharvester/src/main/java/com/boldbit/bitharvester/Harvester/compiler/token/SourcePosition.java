package com.boldbit.bitharvester.Harvester.compiler.token;

public class SourcePosition {
    public final SourceFile source;

    public int getOffset() {
        return offset;
    }

    public final int offset;
    public final int line;
    public final int column;

    public SourcePosition(SourceFile source, int offset, int line, int column) {
        this.source = source;
        this.offset = offset;
        this.line = line;
        this.column = column;
    }

    @Override
    public String toString() {
        return String.format("(%d, %d)", line + 1, column + 1);
    }

}
