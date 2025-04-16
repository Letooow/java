package expression;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int x = scanner.nextInt();
        int y = scanner.nextInt();
        int z = scanner.nextInt();
        scanner.close();
        int res =
                new Add(
                    new Subtract(
                            new Multiply(
                                    new Variable("x"),
                                    new Variable("X")),
                            new Multiply(
                                    new Const(2),
                                    new Variable("X"))),
                    new Const(1)
                ).evaluate(x);
        new Add(new Const(2), new Const(4)).evaluate(x);
        int hash = new Add(new Const(2), new Const(3)).hashCode();
        int hash2 = new Add(new Const(2), new Const(3)).hashCode();
        int hash3 = new Add(new Const(3), new Const(2)).hashCode();
        System.out.println(res);
        System.out.println(hash + " | " + hash2 + " | " + hash3);
    }
}
