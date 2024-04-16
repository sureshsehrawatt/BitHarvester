package com.boldbit.bitharvester.Harvester.compiler.trees;

import com.boldbit.bitharvester.Harvester.compiler.token.Token;
import com.boldbit.bitharvester.Harvester.compiler.token.TreeLocation;

public class UpdateExpressionTree extends ParseTree {

    public enum OperatorPosition {
        PREFIX,
        POSTFIX
    }

    public final Token operator;
    public final OperatorPosition operatorPosition;
    public final ParseTree operand;

    public UpdateExpressionTree(ParseTree operand, Token operator, OperatorPosition operatorPosition,
            TreeLocation treeLocation) {
        super(ParseTreeType.UPDATE_EXPRESSION, treeLocation);

        this.operand = operand;
        this.operator = operator;
        this.operatorPosition = operatorPosition;
    }

    public static UpdateExpressionTree prefix(ParseTree operand, Token operator, TreeLocation treeLocation) {
        return new UpdateExpressionTree(operand, operator, OperatorPosition.PREFIX, treeLocation);
    }

    public static UpdateExpressionTree postfix(ParseTree operand, Token operator, TreeLocation treeLocation) {
        return new UpdateExpressionTree(operand, operator, OperatorPosition.POSTFIX, treeLocation);
    }
}
