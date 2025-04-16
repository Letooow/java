package game;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class Main {
    public static void main(String[] args) {
        // continue game after ctrl+d
        Scanner scanner = new Scanner(System.in);

        System.out.print("Please, type m, n, k: ");
        int m, n, k;
        while (true) {
            try {
                if (!scanner.hasNext()) {
                    System.out.println("End");
                    break;
                }
                String str = scanner.nextLine();
                if (str.isBlank()) continue;
                Scanner line = new Scanner(str);

                m = line.nextInt();
                n = line.nextInt();
                k = line.nextInt();
                if (line.hasNext()) throw new NoSuchElementException();
                int result;

                if (m <= 0 || n <= 0 || k <= 0 ||
                        (k > n / 2 && k > m) ||
                        ((k > n / 2 && m == 1) || ((k > m / 2 && n == 1)))) {

                    System.out.println("Wrong input, try again: ");
                    continue;
                }
                int chose;
                line.close();

                System.out.println("If it's game write 0, if it's tournament write 1: ");
                while (true) {
                    // exception
                    try {
                        if (!scanner.hasNext()) break;
                        str = scanner.nextLine();
                        if (str.isBlank()) continue;
                        line = new Scanner(str);
                        chose = line.nextInt();
                        if (line.hasNext()) {
                            line.close();
                            throw new NoSuchElementException();
                        }
                        if (chose == 0) {
                            // NOTE Tournament on two players?
                            final Olympic game = new Olympic(false, new Player[]{new HumanPlayer(), new HumanPlayer()});
                            game.play(new MnkBoard(m, n, k), true);
                            break;
                        } else if (chose == 1) {
                            System.out.print("Please, type number of players: ");
                            while (true) {
                                try {
                                    int countOfPlayers;
                                    if (!scanner.hasNext()) break;
                                    while (true) {
                                        str = scanner.nextLine();
                                        if (str.isBlank()) continue;
                                        line = new Scanner(str);
                                        countOfPlayers = line.nextInt();
                                        if (line.hasNext()) {
                                            line.close();
                                            throw new NoSuchElementException();
                                        }
                                        if (countOfPlayers > 0) break;
                                        else {
                                            System.out.print("Wrong input, try again: ");
                                            throw new InputMismatchException();
                                        }
                                    }
                                    Player[] players = new Player[countOfPlayers];
                                    Arrays.fill(players, new RandomPlayer(m, n));
                                    players[players.length - 1] = new HumanPlayer();

                                    final Olympic olympic = new Olympic(false, players);
                                    result = olympic.play(new MnkBoard(m, n, k), true);
                                    System.out.println("Player index " + result + " win!");
                                    break;
                                } catch (InputMismatchException e) {
                                    System.out.println("Wrong input, try again: ");
                                } catch (NoSuchElementException e) {
                                    System.out.println("Type just one element");
                                }
                            }
                        break;
                        } else System.out.println("Type just 1 or 0");
                    } catch (InputMismatchException e) {
                        System.out.println("Wrong input ");
                    } catch (NoSuchElementException e) {
                        System.out.println("Type just one number");
                    }
                }
                scanner.close();
                break;
            } catch (InputMismatchException e) {
                System.out.print("Wrong input, try again: ");
            } catch (NoSuchElementException e) {
                System.out.println("Type just three elements");
            }
        }
    }
}
