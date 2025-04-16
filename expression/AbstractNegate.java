package expression;

abstract class AbstractNegate implements BinaryExpressions {

    private final BinaryExpressions exp;

    public AbstractNegate(BinaryExpressions exp) {
        this.exp = exp;
    }

    protected abstract int getRes(int exp);

    @Override
    public int evaluate(int x, int y, int z) {
        return getRes(exp.evaluate(x, y, z));
    }

    @Override
    public int evaluate(int x) {
        return getRes(exp.evaluate(x));
    }

    @Override
    public Expression getLeft() {
        return exp;
    }

    @Override
    public Expression getRight() {
        return null;
    }


    @Override
    public String toString() {
        return "-" + "(" + exp + ")";
    }
}
