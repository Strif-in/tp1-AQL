package uni.aql.model;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    public TicTacToeBoard ticTacToeBoard;

    @BeforeEach()
    void setup(){
        this.ticTacToeBoard = new TicTacToeBoard(3,3);
    }

    @Test
    void testRestartBoard() {
        ticTacToeBoard.mark(2,1); // Player X
        ticTacToeBoard.mark(2,2); // Player O
        ticTacToeBoard.mark(1,1); // Player X
        ticTacToeBoard.mark(0,0); // Player O
        ticTacToeBoard.mark(1,2); // Player X

        ticTacToeBoard.restart();

        assertEquals(0, ticTacToeBoard.getTurn());

        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                assertNull(ticTacToeBoard.getCells()[i][j].getValue());
            }
        }

        assertNull(ticTacToeBoard.getWinner());
        assertEquals(Player.X, ticTacToeBoard.getCurrentTurn());
        assertTrue(ticTacToeBoard.isInProgressMode());
    }

    @Test
    void testMarkRow() {
        ticTacToeBoard.mark(3,0);

        assertEquals(Player.X, ticTacToeBoard.getCurrentTurn());
        assertTrue(ticTacToeBoard.isInProgressMode());
        assertEquals(0, ticTacToeBoard.getTurn());
        assertNull(ticTacToeBoard.getWinner());

        ticTacToeBoard.mark(-1,0);

        assertEquals(Player.X, ticTacToeBoard.getCurrentTurn());
        assertTrue(ticTacToeBoard.isInProgressMode());
        assertEquals(0, ticTacToeBoard.getTurn());
        assertNull(ticTacToeBoard.getWinner());

        ticTacToeBoard.mark(0,0);

        assertEquals(Player.X, ticTacToeBoard.getCells()[0][0].getValue());
        assertEquals(Player.O, ticTacToeBoard.getCurrentTurn());
        assertTrue(ticTacToeBoard.isInProgressMode());
        assertEquals(1, ticTacToeBoard.getTurn());
        assertNull(ticTacToeBoard.getWinner());


        ticTacToeBoard.mark(0,0);

        assertEquals(Player.X, ticTacToeBoard.getCells()[0][0].getValue());
        assertEquals(Player.O, ticTacToeBoard.getCurrentTurn());
        assertTrue(ticTacToeBoard.isInProgressMode());
        assertEquals(1, ticTacToeBoard.getTurn());
        assertNull(ticTacToeBoard.getWinner());
    }

    @Test
    void testMarkCol() {
        ticTacToeBoard.mark(0,3);

        assertEquals(Player.X, ticTacToeBoard.getCurrentTurn());
        assertTrue(ticTacToeBoard.isInProgressMode());
        assertEquals(0, ticTacToeBoard.getTurn());
        assertNull(ticTacToeBoard.getWinner());

        ticTacToeBoard.mark(0,-1);

        assertEquals(Player.X, ticTacToeBoard.getCurrentTurn());
        assertTrue(ticTacToeBoard.isInProgressMode());
        assertEquals(0, ticTacToeBoard.getTurn());
        assertNull(ticTacToeBoard.getWinner());

        ticTacToeBoard.mark(0,0);

        assertEquals(Player.X, ticTacToeBoard.getCells()[0][0].getValue());
        assertEquals(Player.O, ticTacToeBoard.getCurrentTurn());
        assertTrue(ticTacToeBoard.isInProgressMode());
        assertEquals(1, ticTacToeBoard.getTurn());
        assertNull(ticTacToeBoard.getWinner());

        ticTacToeBoard.mark(0,0);


        assertEquals(Player.X, ticTacToeBoard.getCells()[0][0].getValue());
        assertEquals(Player.O, ticTacToeBoard.getCurrentTurn());
        assertTrue(ticTacToeBoard.isInProgressMode());
        assertEquals(1, ticTacToeBoard.getTurn());
        assertNull(ticTacToeBoard.getWinner());
    }

    @Test
    void testWinRow() {
        ticTacToeBoard.mark(0,0); // Player X
        ticTacToeBoard.mark(1,2); // Player O
        ticTacToeBoard.mark(0,1); // Player X
        ticTacToeBoard.mark(1,0); // Player O
        ticTacToeBoard.mark(0,2); // Player X

        assertEquals(Player.X , ticTacToeBoard.getWinner());
        assertEquals(Player.X, ticTacToeBoard.getCurrentTurn());
        assertTrue(ticTacToeBoard.isInFinishedMode());
        assertEquals(5, ticTacToeBoard.getTurn());

        ticTacToeBoard.mark(2,2); // Player O

        assertEquals(Player.X, ticTacToeBoard.getCurrentTurn());
        assertTrue(ticTacToeBoard.isInFinishedMode());
        assertEquals(5, ticTacToeBoard.getTurn());
    }

    @Test
    void testWinCol() {
        ticTacToeBoard.mark(0,0); // Player X
        ticTacToeBoard.mark(0,1); // Player O
        ticTacToeBoard.mark(1,0); // Player X
        ticTacToeBoard.mark(1,1); // Player O
        ticTacToeBoard.mark(2,0); // Player X

        assertEquals(Player.X , ticTacToeBoard.getWinner());
        assertEquals(Player.X, ticTacToeBoard.getCurrentTurn());
        assertTrue(ticTacToeBoard.isInFinishedMode());
        assertEquals(5, ticTacToeBoard.getTurn());

        ticTacToeBoard.mark(2,2); // Player O

        assertEquals(Player.X, ticTacToeBoard.getCurrentTurn());
        assertTrue(ticTacToeBoard.isInFinishedMode());
        assertEquals(5, ticTacToeBoard.getTurn());
    }

    @Test
    void testWinDiagonal() {
        ticTacToeBoard.mark(0,0); // Player X
        ticTacToeBoard.mark(2,0); // Player O
        ticTacToeBoard.mark(1,1); // Player X
        ticTacToeBoard.mark(0,2); // Player O
        ticTacToeBoard.mark(2,2); // Player X

        assertEquals(Player.X , ticTacToeBoard.getWinner());
        assertEquals(Player.X, ticTacToeBoard.getCurrentTurn());
        assertTrue(ticTacToeBoard.isInFinishedMode());
        assertEquals(5, ticTacToeBoard.getTurn());

        ticTacToeBoard.mark(2,2); // Player O

        assertEquals(Player.X, ticTacToeBoard.getCurrentTurn());
        assertTrue(ticTacToeBoard.isInFinishedMode());
        assertEquals(5, ticTacToeBoard.getTurn());
    }

    @Test
    void testWinAntiDiagonal() {
        ticTacToeBoard.mark(0,2); // Player X
        ticTacToeBoard.mark(2,2); // Player O
        ticTacToeBoard.mark(1,1); // Player X
        ticTacToeBoard.mark(0,0); // Player O
        ticTacToeBoard.mark(2,0); // Player X

        assertEquals(Player.X , ticTacToeBoard.getWinner());
        assertEquals(Player.X, ticTacToeBoard.getCurrentTurn());
        assertTrue(ticTacToeBoard.isInFinishedMode());
        assertEquals(5, ticTacToeBoard.getTurn());

        ticTacToeBoard.mark(2,2); // Player O

        assertEquals(Player.X, ticTacToeBoard.getCurrentTurn());
        assertTrue(ticTacToeBoard.isInFinishedMode());
        assertEquals(5, ticTacToeBoard.getTurn());
    }


    @Test
    void testFinishedGameTie() {
        ticTacToeBoard.mark(0,0); // Player X
        ticTacToeBoard.mark(0,1); // Player O
        ticTacToeBoard.mark(0,2); // Player X
        ticTacToeBoard.mark(1,1); // Player O
        ticTacToeBoard.mark(1,0); // Player X
        ticTacToeBoard.mark(1,2); // Player O
        ticTacToeBoard.mark(2,1); // Player X
        ticTacToeBoard.mark(2,0); // Player O
        ticTacToeBoard.mark(2,2); // Player X

        assertNull(ticTacToeBoard.getWinner());
        assertTrue(ticTacToeBoard.isInFinishedMode());
        assertEquals(8, ticTacToeBoard.getTurn());

        ticTacToeBoard.mark(2,2); // Player O ?

        assertEquals(Player.X, ticTacToeBoard.getCurrentTurn());
        assertNull(ticTacToeBoard.getWinner());
        assertTrue(ticTacToeBoard.isInFinishedMode());
        assertEquals(8, ticTacToeBoard.getTurn());
    }


    @Test
    void testForcePlayerOPlay() {
        ticTacToeBoard.setCurrentTurn(Player.O);
        ticTacToeBoard.mark(1,2); // Player 0

        assertEquals(Player.X, ticTacToeBoard.getCurrentTurn());
        assertTrue(ticTacToeBoard.isInProgressMode());
    }

    @Test
    void testForcedValueTurn() {
        ticTacToeBoard.setTurn(10);

        assertEquals(Player.X, ticTacToeBoard.getCurrentTurn());
        assertNull(ticTacToeBoard.getWinner());
        assertEquals(10, ticTacToeBoard.getTurn());

        ticTacToeBoard.mark(1,2); // Player X

        assertEquals(Player.X, ticTacToeBoard.getCurrentTurn());
        assertNull(ticTacToeBoard.getWinner());
        assertTrue(ticTacToeBoard.isInFinishedMode());
        assertEquals(10, ticTacToeBoard.getTurn());
    }
}