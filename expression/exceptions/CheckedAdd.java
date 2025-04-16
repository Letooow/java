package expression.exceptions;

import expression.*;

public class CheckedAdd extends Add {
    public CheckedAdd(BinaryExpressions left, BinaryExpressions right) {
        super(left, right);
    }

    @Override
    protected int getRes(int left, int right) {
        if (left > 0 && right > 0 && (left + right) < 0) {
            throw new ExpressionsExceptions("overflow");
        } else if (left < 0 && right < 0 && (left + right) >= 0) {
            throw new ExpressionsExceptions("overflow");
        }
        return super.getRes(left, right);
    }
}
