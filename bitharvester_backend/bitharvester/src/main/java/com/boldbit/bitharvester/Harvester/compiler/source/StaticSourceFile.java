package com.boldbit.bitharvester.Harvester.compiler.source;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class StaticSourceFile {
    public String fileName;
    public String sourceString;
    public String fileSize;

    public StaticSourceFile(Path path) throws IOException {
        this.fileName = path.getFileName().toString();
        this.sourceString = Files.readString(path);
        this.fileSize = getSize(path);
    }

    public StaticSourceFile(String fileName, String sourceString, long fileSize) {
        this.fileName = fileName;
        this.sourceString = sourceString;
        this.fileSize = getSize(fileSize);
    }

    String getSize(long fileSizeBytes) {
        String size = "";
        if (fileSizeBytes >= 1024 * 1024) {
            // If file size is greater than or equal to 1 MB
            size = String.format("%.2f MB", (double) fileSizeBytes / (1024 * 1024));
        } else if (fileSizeBytes >= 1024) {
            // If file size is greater than or equal to 1 KB
            size = String.format("%.2f KB", (double) fileSizeBytes / 1024);
        } else {
            size = fileSizeBytes + " bytes";
        }
        return size;
    }

    String getSize(Path path) {
        String size = "";
        try {
            long fileSizeBytes = Files.size(path);

            if (fileSizeBytes >= 1024 * 1024) {
                // If file size is greater than or equal to 1 MB
                size = String.format("%.2f MB", (double) fileSizeBytes / (1024 * 1024));
            } else if (fileSizeBytes >= 1024) {
                // If file size is greater than or equal to 1 KB
                size = String.format("%.2f KB", (double) fileSizeBytes / 1024);
            } else {
                size = fileSizeBytes + " bytes";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return size;
    }
}
