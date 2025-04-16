package expression;


abstract class AbstractExp implements BinaryExpressions {

    private final BinaryExpressions left;
    private final BinaryExpressions right;

    protected abstract int getRes(int left, int right);

    public AbstractExp(BinaryExpressions left, BinaryExpressions right) {
        this.left = left;
        this.right = right;
    }
    @Override
    public int evaluate(int x) {
        return getRes(left.evaluate(x), right.evaluate(x));
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return getRes(left.evaluate(x, y, z), right.evaluate(x, y, z));
    }

    @Override
    public BinaryExpressions getLeft() {
        return left;
    }
    public BinaryExpressions getRight() {
        return right;
    }
    @Override
    public boolean equals(Object object) {
        if (object instanceof BinaryExpressions && object.getClass().equals(this.getClass())) {
            return left.equals(((BinaryExpressions) object).getLeft())
                    && right.equals(((BinaryExpressions) object).getRight());
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return 259 * left.hashCode() + 239 * right.hashCode() + 283 * this.getClass().hashCode();
    }
    @Override
    public String toString() {
        return "(" + left + " " + getExp() + " " + right + ")";
    }

    protected abstract char getExp();

}
