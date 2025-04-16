package game;

import java.io.PrintStream;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class HumanPlayer implements Player {
    private final PrintStream out;
    private final Scanner in;

    public HumanPlayer(final PrintStream out, final Scanner in) {
        this.out = out;
        this.in = in;
    }

    public HumanPlayer() {
        this(System.out, new Scanner(System.in));
    }

    @Override
    public Move move(final Position position, final Cell cell) {
        while (true) {
            out.println("Position");
            out.println(position);
            out.println(cell + "'s move");
            out.println("Enter row and column");
            int m1, m2;
            try {
                while (true) {
                    try {
                        if (!in.hasNext()) {
                            return null;
                        }
                        String line = in.nextLine();
                        Scanner scan = new Scanner(line);
                        m1 = scan.nextInt();
                        m2 = scan.nextInt();
                        if (scan.hasNext()) {
                            scan.close();
                            throw new InputMismatchException();
                        }
                        final Move move = new Move(m1, m2, cell);
                        if (position.isValid(move)) {
                            return move;
                        }
                        out.println("Move " + move + " is invalid");
                    } catch (InputMismatchException e) {
                        System.out.println("Please type just two cords");
                        in.nextLine();
                    }
                }
            } catch (InputMismatchException e) {
                System.out.println("Input is invalid, please try again");
                in.nextLine();
            }
        }
    }
}
