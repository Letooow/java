package expression;

public class Negate extends AbstractNegate implements BinaryExpressions {

    public Negate(BinaryExpressions exp) {
        super(exp);
    }

    @Override
    protected int getRes(int exp) {
        return -1 * exp;
    }
}
