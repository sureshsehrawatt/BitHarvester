package com.boldbit.bitharvester.Harvester.compiler.trees;

import java.util.ArrayList;

import com.boldbit.bitharvester.Harvester.compiler.token.IdentifierToken;
import com.boldbit.bitharvester.Harvester.compiler.token.TreeLocation;
import com.boldbit.bitharvester.Harvester.compiler.token.Token;

public class MethodSignatureTree extends ParseTree{

    public final Token returnType;
    public final IdentifierToken methodName;
    public final ArrayList<ParseTree> parameterList;

    public MethodSignatureTree(Token returnType, IdentifierToken methodName, ArrayList<ParseTree> parameterList, TreeLocation treeLocation) {
        super(ParseTreeType.METHOD_SIGNATURE, treeLocation);
        this.returnType = returnType;
        this.methodName = methodName;
        this.parameterList = parameterList;
    }
    
}
