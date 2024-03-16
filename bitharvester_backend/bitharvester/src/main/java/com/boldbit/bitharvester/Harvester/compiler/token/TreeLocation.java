package com.boldbit.bitharvester.Harvester.compiler.token;

//TODO - change name TreeLocation to TreeLocation
public class TreeLocation {
    public final SourcePosition start;
    public final SourcePosition end;

    public TreeLocation(SourcePosition start, SourcePosition end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return String.format("<%s - %s>", start, end);
    }
}
