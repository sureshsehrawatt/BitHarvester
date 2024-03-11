package com.boldbit.bitharvester.Harvester.compiler.parser;

import com.boldbit.bitharvester.Harvester.compiler.token.SourceFile;
import com.boldbit.bitharvester.Harvester.compiler.token.SourcePosition;
import com.boldbit.bitharvester.Harvester.compiler.token.SourceRange;

public class LineNumberScanner {
    SourceFile sourceFile;
    String contents;
    int sourceLength;
    private int lastLine = -1;
    private int lastLineStart = -1;
    private int nextLineStart = 0;

    public LineNumberScanner(SourceFile sourceFile) {
        this.sourceFile = sourceFile;
        this.contents = sourceFile.contents;
        this.sourceLength = contents.length();
    }

    public SourcePosition getSourcePosition(int offset) {
        while (offset >= nextLineStart) {
            advanceLine();
        }
        return new SourcePosition(sourceFile, offset, lastLine, offset - lastLineStart);
    }

    private void advanceLine() {
        lastLine++;

        lastLineStart = nextLineStart;
        for (int index = lastLineStart; index < sourceLength; index++) {
            char ch = contents.charAt(index);
            if (isLineTerminator(ch)) {
                if (ch == '\r' && index + 1 < sourceLength && contents.charAt(index + 1) == '\n') {
                    index++;
                }
                nextLineStart = index + 1;
                return;
            }
        }
        nextLineStart = Integer.MAX_VALUE;
    }

    private boolean isLineTerminator(char ch) {
        switch (ch) {
            case '\n': // Line Feed
            case '\r': // Carriage Return
            case '\u2028': // Line Separator
            case '\u2029': // Paragraph Separator
                return true;
            default:
                return false;
        }
    }

    public SourceRange getSourceRange(int startOffset, int endOffset) {
        return new SourceRange(getSourcePosition(startOffset), getSourcePosition(endOffset));
    }

    public void rewindTo(SourcePosition position) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'rewindTo'");
    }
}
