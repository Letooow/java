package expression.exceptions;

import expression.*;

public class CheckedNegate extends Negate {


    public CheckedNegate(BinaryExpressions exp) {
        super(exp);
    }

    @Override
    protected int getRes(int exp) {

        if (-1 * exp < 0 && exp < 0) {
            throw new ExpressionsExceptions("overflow");
        }

        return super.getRes(exp);
    }
}
