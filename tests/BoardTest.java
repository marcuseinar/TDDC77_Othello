import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

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

        assertFalse("Empty marker should not be valid",board.checkMove( 4,2,Marker.EMPTY));

        assertFalse("negative coordinates should not be valid",board.checkMove( -1,0,Marker.BLACK));
        assertFalse("negative coordinates should not be valid",board.checkMove(0,-1,Marker.BLACK));
        assertFalse("negative coordinates should not be valid",board.checkMove(-1,-1,Marker.BLACK));

        assertFalse("Coordinates outside boardsize should not be valid",board.checkMove(board.getBoardSize(),0,Marker.BLACK));
        assertFalse("Coordinates outside boardsize should not be valid",board.checkMove(0,board.getBoardSize(),Marker.BLACK));
        assertFalse("Coordinates outside boardsize should not be valid",board.checkMove(board.getBoardSize(),board.getBoardSize(),Marker.BLACK));

        assertFalse("Cannot place on another marker",board.checkMove(4,4,Marker.BLACK));
        assertFalse("Cannot place on another marker",board.checkMove(3,3,Marker.WHITE));
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

        assertFalse("Empty marker should not be valid",board.makeMove( 4,2,Marker.EMPTY));

        assertFalse("negative coordinates should not be valid",board.makeMove( -1,0,Marker.BLACK));
        assertFalse("negative coordinates should not be valid",board.makeMove(0,-1,Marker.BLACK));
        assertFalse("negative coordinates should not be valid",board.makeMove(-1,-1,Marker.BLACK));
        assertFalse("Coordinates outside boardsize should not be valid",board.makeMove(board.getBoardSize(),0,Marker.BLACK));
        assertFalse("Coordinates outside boardsize should not be valid",board.makeMove(0,board.getBoardSize(),Marker.BLACK));
        assertFalse("Coordinates outside boardsize should not be valid",board.makeMove(board.getBoardSize(),board.getBoardSize(),Marker.BLACK));

        assertFalse("Cannot place on another marker",board.makeMove(4,4,Marker.BLACK));
        assertFalse("Cannot place on another marker",board.makeMove(3,3,Marker.WHITE));
    }

    @Test
    public void testGetPlayerCounter() throws Exception {
        assertEquals("Counted wrong ammount of BLACK pieces", 2, board.getPlayerCounter(Marker.BLACK));
        assertEquals("Counted wrong ammount of WHITE pieces", 2, board.getPlayerCounter(Marker.WHITE));
        board.makeMove(4,2,Marker.BLACK);
        assertEquals("Counted wrong ammount of BLACK pieces", 4,board.getPlayerCounter(Marker.BLACK));
        assertEquals("Counted wrong ammount of WHITE pieces", 1,board.getPlayerCounter(Marker.WHITE));
        board.makeMove(5,2,Marker.WHITE);
        assertEquals("Counted wrong ammount of BLACK pieces", 3,board.getPlayerCounter(Marker.WHITE));
        assertEquals("Counted wrong ammount of WHITE pieces", 3,board.getPlayerCounter(Marker.WHITE));
        assertEquals("EMPTY amrker should return -1", -1,board.getPlayerCounter(Marker.EMPTY));
    }

    @Test
    public void testGetValidMoves() throws Exception {
        ArrayList<Coordinate> expected = new ArrayList<Coordinate>();
        expected.add(new Coordinate(4,2));
        expected.add(new Coordinate(2,4));
        expected.add(new Coordinate(5,3));
        expected.add(new Coordinate(3,5));
        assertTrue("Did not return correct valid moves list",expected.equals(this.board.getValidMoves(Marker.BLACK)));

        board.makeMove(4,2,Marker.BLACK);

        expected = new ArrayList<Coordinate>();
        expected.add(new Coordinate(2,3));
        expected.add(new Coordinate(2,5));
        expected.add(new Coordinate(4,5));
        assertTrue("Did not return correct valid moves list",expected.equals(this.board.getValidMoves(Marker.WHITE)));
    }
}