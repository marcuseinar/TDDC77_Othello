import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class BoardTest {

    Board board;
    Marker b = Marker.BLACK;
    Marker w = Marker.WHITE;
    Marker e = Marker.EMPTY;
    @Before
    public void setUp() throws Exception {
        board = new Board();
    }

    @Test
    public void testGetBoard() throws Exception {
        Marker[][] expectedBoard = new Marker[][]{
                {e, e, e, e, e, e, e, e},
                {e, e, e, e, e, e, e, e},
                {e, e, e, e, e, e, e, e},
                {e, e, e, b, w, e, e, e},
                {e, e, e, w, b, e, e, e},
                {e, e, e, e, e, e, e, e},
                {e, e, e, e, e, e, e, e},
                {e, e, e, e, e, e, e, e},
        };
        assertArrayEquals("Board was not initiated properly",expectedBoard,this.board.getBoard());

    }

    @Test
    public void testCheckMove() throws Exception {
        assertTrue("Black move was not valid where it should be", board.checkMove(4,2,Marker.BLACK));
        assertFalse("Black move was valid where it shouldn't be", board.checkMove(3,2,Marker.BLACK));
        assertTrue("White move was not valid where it should be", board.checkMove(3,2,Marker.WHITE));
        assertFalse("White move was valid where it shouldn't be", board.checkMove(2,2,Marker.WHITE));
        assertFalse("Empy marker should not be valid",board.checkMove( 4,2,Marker.EMPTY));
        assertFalse("negative coordinates should not be valid",board.checkMove( -1,0,Marker.BLACK));
        assertFalse("negative coordinates should not be valid",board.checkMove(0,-1,Marker.BLACK));
        assertFalse("negative coordinates should not be valid",board.checkMove(-1,-1,Marker.BLACK));

        //TODO write more tests
        assertFalse("Coordinates outside boardsize should not be valid",board.checkMove(board.getBoardSize()+1,board.getBoardSize(),Marker.BLACK));
        assertFalse("negative coordinates should not be valid",board.checkMove(-1,0,Marker.BLACK));
    }

    @Test
    public void testMakeMove() throws Exception {
        Marker[][] expectedBoard = new Marker[][]{
                {e, e, e, e, e, e, e, e},
                {e, e, e, e, e, e, e, e},
                {e, e, e, e, e, e, e, e},
                {e, e, e, b, w, e, e, e},
                {e, e, b, b, b, e, e, e},
                {e, e, e, e, e, e, e, e},
                {e, e, e, e, e, e, e, e},
                {e, e, e, e, e, e, e, e},
        };
        assertFalse("Black move was valid where it shouldn't be", board.makeMove(3,2,Marker.BLACK));
        assertTrue("Black move was not valid where it should be", board.makeMove(4,2,Marker.BLACK));
        assertArrayEquals("Black move was not made", expectedBoard, board.getBoard());

        expectedBoard = new Marker[][]{
                {e, e, e, e, e, e, e, e},
                {e, e, e, e, e, e, e, e},
                {e, e, e, e, e, e, e, e},
                {e, e, e, b, w, e, e, e},
                {e, e, b, w, b, e, e, e},
                {e, e, w, e, e, e, e, e},
                {e, e, e, e, e, e, e, e},
                {e, e, e, e, e, e, e, e},
        };
        assertFalse("White move was valid where it shouldn't be", board.makeMove(2,2,Marker.WHITE));
        assertTrue("White move was not valid where it should be", board.makeMove(5,2,Marker.WHITE));
        assertArrayEquals("White move was not made", expectedBoard, board.getBoard());

    }
}