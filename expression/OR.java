package expression;

public class OR extends AbstractExp implements BinaryExpressions {
    public OR(BinaryExpressions left, BinaryExpressions right) {
        super(left, right);
    }

    @Override
    protected int getRes(int left, int right) {
        return left | right;
    }

    @Override
    protected char getExp() {
        return '|';
    }
}
