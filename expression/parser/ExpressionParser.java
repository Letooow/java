package expression.parser;


import expression.*;
import expression.exceptions.*;
public final class ExpressionParser implements TripleParser {

    public BinaryExpressions parse(final String source) {
        System.err.println(source);
        return parse(new StringSource(source));
    }

    public static BinaryExpressions parse(final CharSource source) {
        return new ExpressionsParser(source).parseExpression();
    }

    public static class ExpressionsParser extends BaseParser {



        public ExpressionsParser(final CharSource source) {
            super(source);
        }

        public BinaryExpressions parseExpression() {
            final BinaryExpressions result = parseElement();
            if (eof()) {
                return result;
            }
            throw error("End of expression expected");
        }

        private BinaryExpressions parseElement() {
            return parseOR();
        }

        private BinaryExpressions parseOR() {
            skipWhitespace();
            BinaryExpressions left = parseXOR();
            while (true) {
                skipWhitespace();
                if (test('|')) {
                    left = createExp(left, take(), parseXOR());
                } else return left;
            }
        }

        private BinaryExpressions parseXOR() {
            skipWhitespace();
            BinaryExpressions left = parseAND();
            while (true) {
                skipWhitespace();
                if (test('^')) {
                    left = createExp(left, take(), parseAND());
                } else return left;
            }
        }

        private BinaryExpressions parseAND() {
            skipWhitespace();
            BinaryExpressions left = parseAdd();
            while (true) {
                skipWhitespace();
                if (test('&')) {
                    left = createExp(left, take(), parseAdd());
                } else return left;
            }
        }

        private BinaryExpressions parseAdd() {
            skipWhitespace();
            BinaryExpressions left = parseMultiply();
            while (true) {
                skipWhitespace();
                if (test('+') || test('-')) {
                    left = createExp(left, take(), parseMultiply());
                } else return left;
            }
        }

        private BinaryExpressions parseMultiply() {
            skipWhitespace();
            BinaryExpressions left = parseNegate();
            skipWhitespace();
            while (true) {
                skipWhitespace();
                if (test('*') || test('/')) {
                    left = createExp(left, take(), parseNegate());
                } else return left;
            }
        }

        private BinaryExpressions parseNegate() {
            skipWhitespace();
            if (take('-')) {
                if (between('0', '9')) {
                    StringBuilder sb = new StringBuilder("-");
                    takeInteger(sb);
                    return new Const(Integer.parseInt(sb.toString()));
                }
                return new Negate(parseBracket());
            }
            return parseBracket();
        }

        private BinaryExpressions parseBracket() {
            skipWhitespace();
            BinaryExpressions left;
            if (take('(')) {
                left = parseElement();
                take(')');
                return left;
            } else if (between('0', '9')) {
                return parseConst();
            } else if (between('A', 'Z') || between('a', 'z')) {
                return parseVariable();
            } else if (test('-')) {
                return parseNegate();
            }
            throw error("Illegal expression");
        }

        private BinaryExpressions parseConst() {
            final StringBuilder sb = new StringBuilder();
            takeInteger(sb);
            try {
                return new Const(Integer.parseInt(sb.toString()));
            } catch (final NumberFormatException e) {
                throw error("Invalid number " + sb);
            }
        }
        private void takeInteger(final StringBuilder sb) {
            skipWhitespace();
            if (take('-')) {
                sb.append('-');
            }
            if (take('0')) {
                sb.append('0');
            } else if (between('1', '9')) {
                takeDigits(sb);
            } else {
                throw error("Invalid number");
            }
        }
        private BinaryExpressions parseVariable() {
            StringBuilder sb = new StringBuilder();
            while (between('A', 'Z') || between('a', 'z')) {
                sb.append(take());
            }
            return new Variable(sb.toString());

        }
        private void takeDigits(final StringBuilder sb) {
            while (between('0', '9')) {
                sb.append(take());
            }
        }

        private void skipWhitespace() {
            while (Character.isWhitespace(returnChar())) {
                take();
                // skip
            }
        }
        private BinaryExpressions createExp(BinaryExpressions left, char znak,  BinaryExpressions right) {
            return switch (znak) {
                case '+' -> new CheckedAdd(left, right);
                case '-' -> new CheckedSubtract(left, right);
                case '*' -> new CheckedMultiply(left, right);
                case '/' -> new CheckedDivide(left, right);
                case '&' -> new AND(left, right);
                case '|' -> new OR(left, right);
                case '^' -> new XOR(left, right);
                default -> throw error("It's not an expression");
            };
        }
    }
}