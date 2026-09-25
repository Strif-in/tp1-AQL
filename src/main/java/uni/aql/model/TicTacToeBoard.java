package uni.aql.model;

import static java.lang.Math.min;

public class TicTacToeBoard extends AbstractBoard {

    public TicTacToeBoard(int height, int width) {
        this.cells = new Cell[height][width];
        this.height = height;
        this.width = width;
        this.maxTurns = height * width;
        this.nbWinCells = min(height, width);
        restart();
    }

    public TicTacToeBoard(int height, int width, int nbWinCells) {
        new TicTacToeBoard(height, width);
        this.nbWinCells = nbWinCells;
        restart();
    }

    /**
     * Mark the current row for the player who's current turn it is.
     * Will perform no-op if the arguments are out of range or if that position is already played.
     * Will also perform a no-op if the game is already over.
     *
     * @param row 0..2
     * @param col 0..2
     *
     */
    public void mark( int row, int col ) {
        play(row, col);
    }

    @Override
    boolean checkRowWinByPlayer(Player player, int currentRow, int nbWinCells) {
        int cptWin = 0;
        for (int i = 0; i < width; i++){
            cptWin = cells[currentRow][i].getValue() == player ? cptWin + 1 : 0;
        }
        return cptWin >= nbWinCells;
    }

    @Override
    boolean checkColWinByPlayer(Player player, int currentCol, int nbWinCells) {
        int cptWin = 0;
        for (int i = 0; i < height; i++){
            cptWin = cells[i][currentCol].getValue() == player ? cptWin + 1 : 0;
        }
        return cptWin >= nbWinCells;
    }

    @Override
    boolean checkDiagonalByPlayer(Player player, int nbWinCells) {
        return cells[0][0].getValue() == player && cells[1][1].getValue() == player && cells[2][2].getValue() == player;
    }

    @Override
    boolean checkAntiDiagonalByPlayer(Player player, int nbWinCells) {
        return  cells[0][2].getValue() == player && cells[1][1].getValue() == player && cells[2][0].getValue() == player;
    }
}