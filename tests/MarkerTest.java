import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MarkerTest {

    Marker m;
    @Before
    public void setUp(){
    }

    @Test
    public void testToString() throws Exception {
        m = Marker.BLACK;
        assertEquals("Not the correct string","B",m.toString());
        m = Marker.WHITE;
        assertEquals("Not the correct string","W",m.toString());
        m = Marker.EMPTY;
        assertEquals("Not the correct string","E",m.toString());
    }

    @Test
    public void testGetOppostie() throws Exception {
        m = Marker.BLACK;
        assertEquals("Not opposite",Marker.WHITE, m.getOppostie());
        m = Marker.WHITE;
        assertEquals("Not opposite",Marker.BLACK, m.getOppostie());
        m = Marker.EMPTY;
        assertEquals("Not correct reply from getOposite() on empty marker",Marker.EMPTY, m.getOppostie());
    }
}