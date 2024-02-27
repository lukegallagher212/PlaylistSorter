package dailymixes;

// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Luke Gallagher (lukegallagher212)
// -------------------------------------------------------------------------
/**
 * tests all the methods in Song class
 * 
 * @author lukegallagher
 * @version Oct 31, 2023
 */
public class SongTest
    extends student.TestCase
{
    private Song s;
    private Song s1;
    private Song s2;
    private Song s3;
    private int num;
    private GenreSet set;

    // ----------------------------------------------------------
    /**
     * sets up variables
     */
    public void setUp()
    {
        s = new Song("Hey Jude", 30, 60, 5, "p1");
        s1 = new Song("Hey Jude", 30, 60, 5, "p1");
        s2 = new Song("Bye Jude", 70, 40, 95, "");
        s3 = null;
        num = 5;
        set = new GenreSet(30, 60, 5);
    }


    // ----------------------------------------------------------
    /**
     * tests getplaylistname
     */
    public void testGetPlaylistName()
    {
        assertEquals("p1", s.getPlaylistName());
    }


    // ----------------------------------------------------------
    /**
     * tests get name
     */
    public void testGetName()
    {
        assertEquals("Hey Jude", s.getName());
    }


    // ----------------------------------------------------------
    /**
     * test get genre set
     */
    public void testGetGenreSet()
    {
        assertEquals(set, s.getGenreSet());
    }


    // ----------------------------------------------------------
    /**
     * tests converting song to a string
     */
    public void testToString()
    {
        assertEquals(
            "Hey Jude Pop:30 Rock:60 Country:5 Suggested: p1",
            s.toString());
        assertEquals(
            "No-Playlist Bye Jude Pop:70 Rock:40 Country:95",
            s2.toString());
    }


    // ----------------------------------------------------------
    /**
     * tests weaether various songs/objects are equals
     */
    public void testEquals()
    {
        assertTrue(s.equals(s));
        assertTrue(s.equals(s1));
        assertFalse(s.equals(s2));
        assertFalse(s.equals(s3));
        assertFalse(s.equals(num));
    }
}
