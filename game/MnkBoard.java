package game;

import java.util.Arrays;
import java.util.Map;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class MnkBoard implements Board, Position {
    private static final Map<Cell, Character> SYMBOLS = Map.of(
            Cell.X, 'X',
            Cell.O, 'O',
            Cell.E, '.'
    );

    private final Cell[][] cells;
    private Cell turn;

    private final int m, n, k;
    private int size;
    public MnkBoard(int m, int n, int k) {
        this.m = m;
        this.n = n;
        this.k = k;
        this.size = m * n;
        this.cells = new Cell[m][n];
        for (Cell[] row : cells) {
            Arrays.fill(row, Cell.E);
        }
        turn = Cell.X;
    }

    @Override
    public Board reset() {
        return new MnkBoard(m, n, k);
    }

    @Override
    public Position getPosition() {
        return this;
    }

    @Override
    public Cell getCell() {
        return turn;
    }

    @Override
    public Result makeMove(final Move move) {
        if (!isValid(move)) {
            return Result.LOSE;
        }

        cells[move.getRow()][move.getColumn()] = move.getValue();

        int row = move.getRow();
        int column = move.getColumn();
        int rowStart = row;
        int columnStart = column;
        int inRow = 0;
        int inColumn = 0;
        int count = 1;

        //{(),(),()}
        
        while (row >= 0) {
            if (cells[row][columnStart] == turn) {
                inRow++;
                if (inRow == k) return Result.WIN;
                row += count;
            } else {
                if (count == -1) break;
                inRow = 0;
                --row;
                count = -1;
                continue;
            }
            if (row >= m) {
                inRow = 0;
                --row;
                count = -1;
            }
        }
        count = 1;

        while (column >= 0) {
            if (cells[rowStart][column] == turn) {
                inColumn++;
                if (inColumn == k) return Result.WIN;
                column += count;
            } else {
                if (count == -1) break;
                inColumn = 0;
                --column;
                count = -1;
                continue;
            }
            if (column >= n) {
                inColumn = 0;
                --column;
                count = -1;
            }
        }

        count = 1;
        row = rowStart;
        column = columnStart;
        int inDiag1 = 0;

        while (column >= 0 && row >= 0) {
            if (cells[row][column] == turn) {
                inDiag1++;
                if (inDiag1 == k) return Result.WIN;
                row += count;
                column += count;
            } else {
                if (count == -1) break;
                inDiag1 = 0;
                row--;
                column--;
                count = -1;
                continue;
            }
            if (column >= n || row >= m) {
                inDiag1 = 0;
                row--;
                column--;
                count = -1;
            }
        }

        count = 1;
        row = rowStart;
        column = columnStart;
        int inDiag2 = 0;

        while (row >= 0 && column < n) {
            if (cells[row][column] == turn) {
                inDiag2++;
                if (inDiag2 == k) return Result.WIN;
                row += count;
                column -= count;
            } else {
                if (count == -1) break;
                inDiag2 = 0;
                row--;
                column++;
                count = -1;
                continue;
            }
            if (column < 0 || row >= m) {
                inDiag2 = 0;
                row--;
                column++;
                count = -1;
            }
        }

        size--;

        if (size <= 0) {
            return Result.DRAW;
        }

        turn = turn == Cell.X ? Cell.O : Cell.X;
        return Result.UNKNOWN;
    }



    @Override
    public boolean isValid(final Move move) {
        return 0 <= move.getRow() && move.getRow() < m
                && 0 <= move.getColumn() && move.getColumn() < n
                && cells[move.getRow()][move.getColumn()] == Cell.E
                && turn == getCell();
    }

    @Override
    public Cell getCell(final int r, final int c) {
        return cells[r][c];
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("  ");
        for (int i = 0; i < n; i++) sb.append(i).append(" ");
        for (int r = 0; r < m; r++) {
            sb.append(System.lineSeparator());
            sb.append(r).append(" ");
            for (int c = 0; c < n; c++) {
                sb.append(SYMBOLS.get(cells[r][c])).append(" ");
            }
        }
        return sb.toString();
    }
}
