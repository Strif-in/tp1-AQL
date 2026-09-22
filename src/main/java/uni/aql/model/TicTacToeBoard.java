package uni.aql.model;

public class TicTacToeBoard extends AbstractBoard {

    public TicTacToeBoard(int height, int width) {
        super();
        this.cells = new Cell[height][width];
        this.height = height;
        this.width = width;
        this.maxTurns = height * width;
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
    boolean checkRowWinByPlayer(Player player, int currentRow){
        for (int i = 0; i < width; i++){
            if (cells[currentRow][i].getValue() != player){
                return false;
            }
        }
        return true;
    }

    @Override
    boolean checkColWinByPlayer(Player player, int currentCol){
        for (int i = 0; i < height; i++){
            if (cells[i][currentCol].getValue() != player){
                return false;
            }
        }
        return true;
    }

    @Override
    boolean checkDiagonalByPlayer(Player player){
        return cells[0][0].getValue() == player && cells[1][1].getValue() == player && cells[2][2].getValue() == player;
    }

    @Override
    boolean checkAntiDiagonalByPlayer(Player player){
        return  cells[0][2].getValue() == player && cells[1][1].getValue() == player && cells[2][0].getValue() == player;
    }
}