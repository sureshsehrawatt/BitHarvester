package com.boldbit.bitharvester.Harvester.compiler.token;

public final class SourceFile {
    public final String fileName;
    public final String contents;
    public final String fileSize;

    public SourceFile(String fileName, String contents, String fileSize) {
        this.fileName = fileName;
        this.contents = contents;
        this.fileSize = fileSize;
    }
}
