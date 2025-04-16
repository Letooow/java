package expression;

public class AND extends AbstractExp implements BinaryExpressions {
    public AND(BinaryExpressions left, BinaryExpressions right) {
        super(left, right);
    }

    @Override
    protected int getRes(int left, int right) {
        return left & right;
    }

    @Override
    protected char getExp() {
        return '&';
    }
}
