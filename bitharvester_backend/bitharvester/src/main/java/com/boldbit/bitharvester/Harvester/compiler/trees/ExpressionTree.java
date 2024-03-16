package com.boldbit.bitharvester.Harvester.compiler.trees;

import com.boldbit.bitharvester.Harvester.compiler.token.TreeLocation;
import com.boldbit.bitharvester.Harvester.compiler.token.Token;

public class ExpressionTree extends ParseTree {

    public final Token operand1;
    public final Token operand2;
    public final Token operator;
    public final ExpressionTree expressionTree;

    public ExpressionTree(Token operand1, TreeLocation treeLocation) {
        super(ParseTreeType.CONDITIONAL_EXPRESSION, treeLocation);
        this.operand1 = operand1;
        this.operand2 = null;
        this.operator = null;
        this.expressionTree = null;
    }
    public ExpressionTree(Token operand1, Token operator, TreeLocation treeLocation) {
        super(ParseTreeType.CONDITIONAL_EXPRESSION, treeLocation);
        this.operand1 = operand1;
        this.operand2 = null;
        this.operator = operator;
        this.expressionTree = null;
    }
    public ExpressionTree(Token operand1, Token operand2, Token operator, TreeLocation treeLocation) {
        super(ParseTreeType.CONDITIONAL_EXPRESSION, treeLocation);
        this.operand1 = operand1;
        this.operand2 = operand2;
        this.operator = operator;
        this.expressionTree = null;
    }
    public ExpressionTree(Token operand1, ExpressionTree expressionTree, Token operator, TreeLocation treeLocation) {
        super(ParseTreeType.CONDITIONAL_EXPRESSION, treeLocation);
        this.operand1 = operand1;
        this.operand2 = null;
        this.expressionTree = expressionTree;
        this.operator = operator;
    }
    
}
