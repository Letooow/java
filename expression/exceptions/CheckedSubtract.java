package expression.exceptions;

import expression.*;

public class CheckedSubtract extends Subtract {
    public CheckedSubtract(BinaryExpressions left, BinaryExpressions right) {
        super(left, right);
    }

    @Override
    protected int getRes(int left, int right) {
        if (right >= left && (left - right) > 0) {
            throw new ExpressionsExceptions("overflow");
        } else if (left >= 0 && right < 0 && left - right < 0) {
            throw new ExpressionsExceptions("overflow");
        }

        return super.getRes(left, right);

    }
}
