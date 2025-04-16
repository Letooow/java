package expression;

import java.util.InputMismatchException;

public class Variable implements BinaryExpressions {
    private final String var;

    public Variable(String var) {
        this.var = var;
    }

    @Override
    public String toString() {
        return var;
    }

    @Override
    public int evaluate(int x) {
        return x;
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
        return 197 * getClass().hashCode() + 23 * var.hashCode();
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof Variable) {
            return ((Variable) object).var.equals(var);
        } else {
            return false;
        }
    }
    @Override
    public int evaluate(int x, int y, int z) {
        return switch (var) {
            case "x" -> x;
            case "y" -> y;
            case "z" -> z;
            default -> throw new InputMismatchException();
        };
    }
}

