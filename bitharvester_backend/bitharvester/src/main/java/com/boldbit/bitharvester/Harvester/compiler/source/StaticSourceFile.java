package com.boldbit.bitharvester.Harvester.compiler.source;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class StaticSourceFile {
    public String fileName;
    public String sourceString;

    public StaticSourceFile(Path path) throws IOException{
        this.fileName = path.getFileName().toString();
        this.sourceString = Files.readString(path);
    }

}
