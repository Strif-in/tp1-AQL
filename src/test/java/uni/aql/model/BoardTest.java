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

        // Test empty cells if method get cells exists possible
        assertNull(board.getWinner());
        assertEquals(Player.X, board.getCurrentTurn());
        assertTrue(board.isInProgressMode());
    }

    @Test
    void testMarkRow() {
        board.mark(3,0);

        assertEquals(Player.X, board.getCurrentTurn());

        board.mark(-1,0);

        assertEquals(Player.X, board.getCurrentTurn());

        board.mark(0,0);

        assertEquals(Player.O, board.getCurrentTurn());
        assertTrue(board.isInProgressMode());
        assertNull(board.getWinner());


        board.mark(0,0);
        assertEquals(Player.O, board.getCurrentTurn());

    }

    @Test
    void testMarkCol() {

        board.mark(0,3);

        assertEquals(Player.X, board.getCurrentTurn());

        board.mark(0,-1);

        assertEquals(Player.X, board.getCurrentTurn());

        board.mark(0,0);

        assertEquals(Player.O, board.getCurrentTurn());
        assertTrue(board.isInProgressMode());
        assertNull(board.getWinner());

        board.mark(0,0);
        assertEquals(Player.O, board.getCurrentTurn());
    }

    @Test
    void testWinRow() {
        board.mark(0,0); // Player X
        board.mark(1,2); // Player O
        board.mark(0,1); // Player X
        board.mark(1,0); // Player O
        board.mark(0,2); // Player X

        assertEquals(Player.X , board.getWinner());
        assertTrue(board.isInFinishedMode());

        board.mark(2,2); // Player O

        assertEquals(Player.X, board.getCurrentTurn());
        assertTrue(board.isInFinishedMode());
    }

    @Test
    void testWinCol() {
        board.mark(0,0); // Player X
        board.mark(0,1); // Player O
        board.mark(1,0); // Player X
        board.mark(1,1); // Player O
        board.mark(2,0); // Player X

        assertEquals(Player.X , board.getWinner());
        assertTrue(board.isInFinishedMode());

        board.mark(2,2); // Player O

        assertEquals(Player.X, board.getCurrentTurn());
        assertTrue(board.isInFinishedMode());
    }

    @Test
    void testWinDiagonal() {
        board.mark(0,0); // Player X
        board.mark(2,0); // Player O
        board.mark(1,1); // Player X
        board.mark(0,2); // Player O
        board.mark(2,2); // Player X

        assertEquals(Player.X , board.getWinner());
        assertTrue(board.isInFinishedMode());

        board.mark(2,2); // Player O

        assertEquals(Player.X, board.getCurrentTurn());
        assertTrue(board.isInFinishedMode());
    }

    @Test
    void testWinAntiDiagonal() {
        board.mark(0,2); // Player X
        board.mark(2,2); // Player O
        board.mark(1,1); // Player X
        board.mark(0,0); // Player O
        board.mark(2,0); // Player X

        assertEquals(Player.X , board.getWinner());
        assertTrue(board.isInFinishedMode());

        board.mark(2,2); // Player O

        assertEquals(Player.X, board.getCurrentTurn());
        assertTrue(board.isInFinishedMode());
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
        assertTrue(board.isInProgressMode()); // Error in code: no tie condition.


        board.mark(2,2); // Player O ?

        assertEquals(Player.O, board.getCurrentTurn());
        assertNull(board.getWinner());
        assertTrue(board.isInProgressMode());
    }
}