package uni.aql.model;

import java.util.Arrays;

import static java.lang.Math.min;

public class TicTacToeBoard extends AbstractBoard {

    public TicTacToeBoard(int height, int width) {
        this.cells = new Cell[height][width];
        this.height = height;
        this.width = width;
        this.maxTurns = height * width;
        this.nbWinCells = min(height, width);

        Player player1 = new Player(TicTacToePiece.X);
        Player player2 = new Player(TicTacToePiece.O);
        this.players = Arrays.asList(player1, player2);

        restart();
    }

    @Override
    boolean checkRowWinByPlayer(Player player, int currentRow) {
        int cptWin = 0;
        for (int i = 0; i < width; i++){
            cptWin = countMarkSequence(player, currentRow, i, cptWin);
        }
        return isCountWin(cptWin);
    }

    @Override
    boolean checkColWinByPlayer(Player player, int currentCol) {
        int cptWin = 0;
        for (int i = 0; i < height; i++){
            cptWin = countMarkSequence(player, i, currentCol, cptWin);
        }
        return isCountWin(cptWin);
    }

    @Override
    boolean checkDiagonalByPlayer(Player player, int currentRow, int currentCol) {
        int cptWin = 0;
        int offset = Math.min(currentRow, currentCol);

        for (int i = currentRow - offset, j = currentCol - offset; i < height && j < width; i++, j++) {
            cptWin = countMarkSequence(player, i, j, cptWin);
        }

        return isCountWin(cptWin);
    }

    @Override
    boolean checkAntiDiagonalByPlayer(Player player, int currentRow, int currentCol) {
        int cptWin = 0;
        int offset = Math.min(currentRow, width - 1 - currentCol);

        for (int i = currentRow - offset, j = currentCol + offset; i < height && j >= 0; i++, j--) {
            cptWin = countMarkSequence(player, i, j, cptWin);
        }

        return isCountWin(cptWin);
    }
}