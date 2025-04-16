package expression;

public class Multiply extends AbstractExp implements BinaryExpressions {
    @Override
    protected int getRes(int left, int right) {
        return left * right;
    }

    public Multiply(BinaryExpressions left, BinaryExpressions right) {
        super(left, right);
    }
    @Override
    protected char getExp() {
        return '*';
    }
}
