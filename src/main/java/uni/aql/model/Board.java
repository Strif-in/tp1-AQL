package uni.aql.model;

import static uni.aql.model.Player.O;
import static uni.aql.model.Player.X;

public class Board {

    private Cell[][] cells = new Cell[3][3];

    private int turn = 0;
    private Player winner;
    private GameState state;
    private Player currentTurn;

    public Board() {
        restart();
    }

    /**
     *  Restart or start a new game, will clear the board and win status
     */
    public void restart() {
        clearCells();
        winner = null;
        currentTurn = Player.X;
        turn = 0;
        setInProgressMode();
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
        if(isValid(row, col)) {

            // Tie condition Added
            if (turn >= 8) {
                setInFinishedMode();
                return;
            }

            turn += 1;

            cells[row][col].setValue(currentTurn);

            if(isWinningMoveByPlayer(currentTurn, row, col)) {
                setInFinishedMode();
                winner = currentTurn;

            } else {
                // flip the current turn and continue
                flipCurrentTurn();
            }
        }
    }

    public Cell[][] getCells() {
        return cells;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }

    public int getTurn() {
        return turn;
    }

    public Player getWinner() {
        return winner;
    }

    public Player getCurrentTurn() {
        return currentTurn;
    }

    public void setCurrentTurn(Player currentTurn) {
        this.currentTurn = currentTurn;
    }

    public GameState getState() {
        return state;
    }

    public void setState(GameState state) {
        this.state = state;
    }

    private void setInProgressMode() {
        setState(GameState.IN_PROGRESS);
    }
    private void setInFinishedMode() {
        setState(GameState.FINISHED);
    }

    public Boolean isInProgressMode() {
        return getState().equals(GameState.IN_PROGRESS);
    }
    public Boolean isInFinishedMode() {
        return getState().equals(GameState.FINISHED);
    }

    private void clearCells() {
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                cells[i][j] = new Cell();
            }
        }
    }

    private boolean isValid(int row, int col ) {
        return state != GameState.FINISHED && isOutOfBounds(row) && isOutOfBounds(col) && !isCellValueAlreadySet(row, col);
    }

    private boolean isOutOfBounds(int idx) {
        return idx >= 0 && idx <= 2;
    }

    private boolean isCellValueAlreadySet(int row, int col) {
        return cells[row][col].getValue() != null;
    }


    /**
     * Algorithm adapted from <a href="http://www.ntu.edu.sg/home/ehchua/programming/java/JavaGame_TicTacToe.html"/>
     * @param player Player that might win
     * @param currentRow int Row played by Player
     * @param currentCol int Col played by player
     * @return true if <code>player</code> who just played the move at the <code>currentRow</code>, <code>currentCol</code>
     *              has a tic tac toe.
     */
    private boolean isWinningMoveByPlayer(Player player, int currentRow, int currentCol) {
        return (checkColWinByPlayer(player, currentCol) || checkRowWinByPlayer(player, currentRow) || checkDiagonalByPlayer(player) || checkAntiDiagonalByPlayer(player) );
    }

    private boolean checkRowWinByPlayer(Player player, int currentRow){
        for (int i = 0; i < 3; i++){
            if (cells[currentRow][i].getValue() != player){
                return false;
            }
        }
        return true;
    }

    private boolean checkColWinByPlayer(Player player, int currentCol){
        for (int i = 0; i < 3; i++){
            if (cells[i][currentCol].getValue() != player){
                return false;
            }
        }
        return true;
    }

    private boolean checkDiagonalByPlayer(Player player){
        return cells[0][0].getValue() == player && cells[1][1].getValue() == player && cells[2][2].getValue() == player;
    }

    private boolean checkAntiDiagonalByPlayer(Player player){
        return  cells[0][2].getValue() == player && cells[1][1].getValue() == player && cells[2][0].getValue() == player;
    }

    private void flipCurrentTurn() {
        currentTurn = currentTurn == X ? O : X;
    }

}
