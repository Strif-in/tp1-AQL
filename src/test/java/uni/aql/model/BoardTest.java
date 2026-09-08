package uni.aql.model;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    public Board board;

    @BeforeEach()
    void setup(){
        this.board = new Board();
    }

    @Test
    void testRestartBoard() {
        board.mark(2,1); // Player X
        board.mark(2,2); // Player O
        board.mark(1,1); // Player X
        board.mark(0,0); // Player O
        board.mark(1,2); // Player X

        board.restart();

        assertEquals(0, board.getTurn());

        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                assertNull(board.getCells()[i][j].getValue());
            }
        }

        assertNull(board.getWinner());
        assertEquals(Player.X, board.getCurrentTurn());
        assertTrue(board.isInProgressMode());
    }

    @Test
    void testMarkRow() {
        board.mark(3,0);

        assertEquals(Player.X, board.getCurrentTurn());
        assertTrue(board.isInProgressMode());
        assertEquals(0, board.getTurn());
        assertNull(board.getWinner());

        board.mark(-1,0);

        assertEquals(Player.X, board.getCurrentTurn());
        assertTrue(board.isInProgressMode());
        assertEquals(0, board.getTurn());
        assertNull(board.getWinner());

        board.mark(0,0);

        assertEquals(Player.X, board.getCells()[0][0].getValue());
        assertEquals(Player.O, board.getCurrentTurn());
        assertTrue(board.isInProgressMode());
        assertEquals(1, board.getTurn());
        assertNull(board.getWinner());


        board.mark(0,0);

        assertEquals(Player.X, board.getCells()[0][0].getValue());
        assertEquals(Player.O, board.getCurrentTurn());
        assertTrue(board.isInProgressMode());
        assertEquals(1, board.getTurn());
        assertNull(board.getWinner());
    }

    @Test
    void testMarkCol() {
        board.mark(0,3);

        assertEquals(Player.X, board.getCurrentTurn());
        assertTrue(board.isInProgressMode());
        assertEquals(0, board.getTurn());
        assertNull(board.getWinner());

        board.mark(0,-1);

        assertEquals(Player.X, board.getCurrentTurn());
        assertTrue(board.isInProgressMode());
        assertEquals(0, board.getTurn());
        assertNull(board.getWinner());

        board.mark(0,0);

        assertEquals(Player.X, board.getCells()[0][0].getValue());
        assertEquals(Player.O, board.getCurrentTurn());
        assertTrue(board.isInProgressMode());
        assertEquals(1, board.getTurn());
        assertNull(board.getWinner());

        board.mark(0,0);


        assertEquals(Player.X, board.getCells()[0][0].getValue());
        assertEquals(Player.O, board.getCurrentTurn());
        assertTrue(board.isInProgressMode());
        assertEquals(1, board.getTurn());
        assertNull(board.getWinner());
    }

    @Test
    void testWinRow() {
        board.mark(0,0); // Player X
        board.mark(1,2); // Player O
        board.mark(0,1); // Player X
        board.mark(1,0); // Player O
        board.mark(0,2); // Player X

        assertEquals(Player.X , board.getWinner());
        assertEquals(Player.X, board.getCurrentTurn());
        assertTrue(board.isInFinishedMode());
        assertEquals(5, board.getTurn());

        board.mark(2,2); // Player O

        assertEquals(Player.X, board.getCurrentTurn());
        assertTrue(board.isInFinishedMode());
        assertEquals(5, board.getTurn());
    }

    @Test
    void testWinCol() {
        board.mark(0,0); // Player X
        board.mark(0,1); // Player O
        board.mark(1,0); // Player X
        board.mark(1,1); // Player O
        board.mark(2,0); // Player X

        assertEquals(Player.X , board.getWinner());
        assertEquals(Player.X, board.getCurrentTurn());
        assertTrue(board.isInFinishedMode());
        assertEquals(5, board.getTurn());

        board.mark(2,2); // Player O

        assertEquals(Player.X, board.getCurrentTurn());
        assertTrue(board.isInFinishedMode());
        assertEquals(5, board.getTurn());
    }

    @Test
    void testWinDiagonal() {
        board.mark(0,0); // Player X
        board.mark(2,0); // Player O
        board.mark(1,1); // Player X
        board.mark(0,2); // Player O
        board.mark(2,2); // Player X

        assertEquals(Player.X , board.getWinner());
        assertEquals(Player.X, board.getCurrentTurn());
        assertTrue(board.isInFinishedMode());
        assertEquals(5, board.getTurn());

        board.mark(2,2); // Player O

        assertEquals(Player.X, board.getCurrentTurn());
        assertTrue(board.isInFinishedMode());
        assertEquals(5, board.getTurn());
    }

    @Test
    void testWinAntiDiagonal() {
        board.mark(0,2); // Player X
        board.mark(2,2); // Player O
        board.mark(1,1); // Player X
        board.mark(0,0); // Player O
        board.mark(2,0); // Player X

        assertEquals(Player.X , board.getWinner());
        assertEquals(Player.X, board.getCurrentTurn());
        assertTrue(board.isInFinishedMode());
        assertEquals(5, board.getTurn());

        board.mark(2,2); // Player O

        assertEquals(Player.X, board.getCurrentTurn());
        assertTrue(board.isInFinishedMode());
        assertEquals(5, board.getTurn());
    }


    @Test
    void testFinishedGameTie() {
        board.mark(0,0); // Player X
        board.mark(0,1); // Player O
        board.mark(0,2); // Player X
        board.mark(1,1); // Player O
        board.mark(1,0); // Player X
        board.mark(1,2); // Player O
        board.mark(2,1); // Player X
        board.mark(2,0); // Player O
        board.mark(2,2); // Player X

        assertNull(board.getWinner());
        assertTrue(board.isInFinishedMode());
        assertEquals(8, board.getTurn());

        board.mark(2,2); // Player O ?

        assertEquals(Player.X, board.getCurrentTurn());
        assertNull(board.getWinner());
        assertTrue(board.isInFinishedMode());
        assertEquals(8, board.getTurn());
    }


    @Test
    void testForcePlayerOPlay() {
        board.setCurrentTurn(Player.O);
        board.mark(1,2); // Player 0

        assertEquals(Player.X, board.getCurrentTurn());
        assertTrue(board.isInProgressMode());
    }

    @Test
    void testForcedValueTurn() {
        board.setTurn(10);

        assertEquals(Player.X, board.getCurrentTurn());
        assertNull(board.getWinner());
        assertEquals(10, board.getTurn());

        board.mark(1,2); // Player X

        assertEquals(Player.X, board.getCurrentTurn());
        assertNull(board.getWinner());
        assertTrue(board.isInFinishedMode());
        assertEquals(10, board.getTurn());
    }
}