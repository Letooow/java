package expression;

public class Const implements BinaryExpressions {
    private final int number;
    public Const(int number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return Integer.toString(number);
    }

    @Override
    public int evaluate(int x) {
        return number;
    }

    @Override
    public Expression getLeft() {
        return this;
    }

    @Override
    public Expression getRight() {
        return null;
    }

    @Override
    public int hashCode() {
        return 197 * getClass().hashCode() + 23 * number;
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof Const) {
            return number == ((Const) object).number;
        } else return false;
    }
    @Override
    public int evaluate(int x, int y, int z) {
        return number;
    }
}