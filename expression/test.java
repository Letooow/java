package expression;

import expression.exceptions.*;

public class test {
    public static void main(String[] args) {
        BinaryExpressions exp = new CheckedAdd(new Const(1), new Const(Integer.MAX_VALUE));
        System.out.println(exp.evaluate(0));
    }
}
