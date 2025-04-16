package expression;

public class Divide extends AbstractExp implements BinaryExpressions {
    @Override
    protected int getRes(int left, int right) {
        return left / right;
    }

    public Divide(BinaryExpressions left, BinaryExpressions right) {
        super(left, right);
    }

    @Override
    protected char getExp() {
        return '/';
    }
}
