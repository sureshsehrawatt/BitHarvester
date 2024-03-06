package com.boldbit.bitharvester.Harvester.compiler.token;

public class SourceRange {
    public final SourcePosition start;
    public final SourcePosition end;

    public SourceRange(SourcePosition start, SourcePosition end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return String.format("<%s - %s>", start, end);
    }
}
