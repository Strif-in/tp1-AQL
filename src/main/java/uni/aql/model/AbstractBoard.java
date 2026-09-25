package uni.aql.model;

import static uni.aql.model.Player.O;
import static uni.aql.model.Player.X;

public abstract class AbstractBoard {
    protected Cell[][] cells;

    protected int maxTurns;
    protected int turn = 0;
    protected int height;
    protected int width;
    protected int nbWinCells;
    protected GameState state;
    protected Player winner;
    protected Player currentTurn;

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

    protected void setInProgressMode() {
        setState(GameState.IN_PROGRESS);
    }
    protected void setInFinishedMode() {
        setState(GameState.FINISHED);
    }

    public Boolean isInProgressMode() {
        return getState().equals(GameState.IN_PROGRESS);
    }
    public Boolean isInFinishedMode() {
        return getState().equals(GameState.FINISHED);
    }

    protected void play( int row, int col ) {
        if(isValid(row, col)) {

            if (turn >= maxTurns - 1) {
                setInFinishedMode();
                return;
            }

            turn += 1;

            cells[row][col].setValue(currentTurn);

            if(isWinningMoveByPlayer(currentTurn, row, col)) {
                setInFinishedMode();
                winner = currentTurn;

            } else {
                flipCurrentTurn();
            }
        }
    }

    protected boolean isValid(int row, int col ) {
        return state != GameState.FINISHED && isInBounds(row, height) && isInBounds(col, width) && !isCellValueAlreadySet(row, col);
    }
    
    protected boolean isInBounds(int idx, int limit) {
        return idx >= 0 && idx <= limit - 1;
    }

    protected boolean isCellValueAlreadySet(int row, int col) {
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
    protected boolean isWinningMoveByPlayer(Player player, int currentRow, int currentCol){
        return (checkColWinByPlayer(player, currentCol, nbWinCells)
                || checkRowWinByPlayer(player, currentRow, nbWinCells)
                || checkDiagonalByPlayer(player, nbWinCells)
                || checkAntiDiagonalByPlayer(player, nbWinCells) );
    }

    abstract boolean checkRowWinByPlayer(Player player, int currentRow, int nbWinCells);

    abstract boolean checkColWinByPlayer(Player player, int currentCol, int nbWinCells);

    abstract boolean checkDiagonalByPlayer(Player player, int nbWinCells);

    abstract boolean checkAntiDiagonalByPlayer(Player player, int nbWinCells);

    protected void flipCurrentTurn() {
        currentTurn = currentTurn == X ? O : X;
    }

    protected void clearCells() {
        for(int i = 0; i < height; i++) {
            for(int j = 0; j < width; j++) {
                cells[i][j] = new Cell();
            }
        }
    }
}
