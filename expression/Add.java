package expression;

public class Add extends AbstractExp implements BinaryExpressions {

    @Override
    protected int getRes(int left, int right) {
        return left + right;
    }

    public Add(BinaryExpressions left, BinaryExpressions right) {
        super(left, right);
    }

    @Override
    protected char getExp() {
        return '+';
    }


}
