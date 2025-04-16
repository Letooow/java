package game;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public interface Board {

    Board reset();
    Position getPosition();
    Cell getCell();
    Result makeMove(Move move);
}
