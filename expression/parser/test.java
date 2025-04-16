package expression.parser;

import expression.BinaryExpressions;
import expression.TripleExpression;

public class test {
    public static void main(String[] args) {
        BinaryExpressions res = ExpressionParser.parse(new StringSource("1 + 2147483647"));
        System.out.println(res.evaluate(1));
    }
}
