package uni.aql.model;

import java.util.Arrays;

public class TicTacToeBoard extends AbstractBoard {

    public TicTacToeBoard() {
        this.cells = new Cell[3][3];
        this.height = 3;
        this.width = 3;
        this.maxTurns = 3 * 3;
        this.nbWinCells = 3;

        Player player1 = new Player(TicTacToePiece.X);
        Player player2 = new Player(TicTacToePiece.O);
        this.players = Arrays.asList(player1, player2);

        restart();
    }
}