package expression;

public class XOR extends AbstractExp implements BinaryExpressions {
    public XOR(BinaryExpressions left, BinaryExpressions right) {
        super(left, right);
    }

    @Override
    protected int getRes(int left, int right) {
        return left ^ right;
    }

    @Override
    protected char getExp() {
        return '^';
    }
}
