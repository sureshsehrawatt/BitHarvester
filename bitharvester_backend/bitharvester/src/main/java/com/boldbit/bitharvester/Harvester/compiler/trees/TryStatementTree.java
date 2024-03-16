package com.boldbit.bitharvester.Harvester.compiler.trees;

import com.boldbit.bitharvester.Harvester.compiler.token.SourceRange;

public class TryStatementTree extends ParseTree {

    public final ParseTree tryBody;
    public final ParseTree catchBody; 
    public final ParseTree finallyBody;

    public TryStatementTree(ParseTree tryBody, ParseTree catchBody, ParseTree finallyBody, SourceRange treeLocation) {
        super(ParseTreeType.TRY_STATEMENT, treeLocation);
        this.tryBody = tryBody;
        this.catchBody = catchBody;
        this.finallyBody = finallyBody;
    }
    
}
