package expression.exceptions;

import expression.*;

public class CheckedMultiply extends Multiply {
    public CheckedMultiply(BinaryExpressions left, BinaryExpressions right) {
        super(left, right);
    }

    @Override
    protected int getRes(int left, int right) {

        int res = left * right;
        int abL = Math.abs(left);
        int abR = Math.abs(right);
        int max = Math.max(abL, abR);
        int min = Math.min(abL, abR);

        if (left < 0 && right < 0 && res <= 0 || (left > 0 && right > 0 && res <= 0)) {
            throw new ExpressionsExceptions("overflow");
        } else if (left > 1 && right > 1 || left < -1 && right < -1) {
            int maxValid = (int) Math.ceil((double) Integer.MAX_VALUE / max);
            if (Math.abs(min) > Math.abs(maxValid)) {
                throw new ExpressionsExceptions("overflow");
            }
        } else if (left < -1 && right > 1 || left > 1 && right < -1) {
            int maxValid = (int) Math.ceil((double) Integer.MIN_VALUE / max);
            if (min == Integer.MIN_VALUE) {
                min = Integer.MAX_VALUE;
            }
            if (Math.abs(min) > Math.abs(maxValid)) {
                throw new ExpressionsExceptions("overflow");
            }
        }

        return super.getRes(left, right);
    }
}
