package expression.exceptions;

import expression.*;

public class CheckedDivide extends Divide {

    public CheckedDivide(BinaryExpressions left, BinaryExpressions right) {
        super(left, right);
    }

    @Override
    protected int getRes(int left, int right) {


        if (right == 0) {
            throw new ExpressionsExceptions("division by zero");
        }

        int res = left / right;

        if ((left == Integer.MIN_VALUE && right == -1)) {
            throw new ExpressionsExceptions("overflow");
        }
        return super.getRes(left, right);
    }

}