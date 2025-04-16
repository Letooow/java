package expression;

public interface BinaryExpressions extends Expression, TripleExpression {
    int evaluate(int x);
    Expression getLeft();
    Expression getRight();

}
