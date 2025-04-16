package expression;

public class Subtract extends AbstractExp implements BinaryExpressions {


    @Override
    protected int getRes(int left, int right) {
        return left - right;
    }

    public Subtract(BinaryExpressions left, BinaryExpressions right) {
        super(left, right);
    }

    @Override
    protected char getExp() {
        return '-';
    }
}
