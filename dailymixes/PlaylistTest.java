package dailymixes;

// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Luke Gallagher (lukegallagher212)
// -------------------------------------------------------------------------
/**
 * Write a one-sentence summary of your class here. Follow it with additional
 * details about its purpose, what abstraction it represents, and how to use it.
 * 
 * @author lukegallagher
 * @version Nov 6, 2023
 */
public class PlaylistTest
    extends student.TestCase
{
    private Playlist p1;
    private Playlist p2;
    private Playlist p3;
    private Playlist p4;
    private Playlist p5;
    private Playlist p6;
    private Playlist p7;
    private Song s1;
    private Song s2;

    // ----------------------------------------------------------
    /**
     * sets up variables used in test methods
     */
    public void setUp()
    {
        p1 = new Playlist("playlist1", 10, 15, 5, 80, 85, 50, 3);
        p2 = null;
        p3 = new Playlist("playlist1", 10, 15, 5, 80, 85, 50, 3);
        p4 = new Playlist("playlist2", 20, 5, 30, 90, 55, 95, 2);
        p5 = new Playlist("playlist1", 20, 30, 10, 80, 85, 50, 3);
        p6 = new Playlist("playlist1", 10, 15, 5, 90, 95, 60, 3);
        p7 = new Playlist("playlist3", 10, 15, 5, 80, 85, 50, 3);
        s1 = new Song("song", 25, 36, 10, "playlist1");
        s2 = new Song("song2", 5, 20, 45, "");
    }


    // ----------------------------------------------------------
    /**
     * tests setting name of a playlist
     */
    public void testSetName()
    {
        p1.setName("play1");
        assertEquals("play1", p1.getName());
    }


    // ----------------------------------------------------------
    /**
     * tests get spaces on a playlist
     */
    public void testGetSpacesLeftandGetNumberOfSongs()
    {
        assertEquals(3, p1.getSpacesLeft());
        assertEquals(0, p1.getNumberOfSongs());
        assertTrue(p1.addSong(s1));
        assertEquals(2, p1.getSpacesLeft());
        assertEquals(1, p1.getNumberOfSongs());
        assertEquals(3, p1.getCapacity());
    }


    // ----------------------------------------------------------
    /**
     * tests all cases when adding a song to a playlist
     */
    public void testAddSong()
    {
        assertTrue(p1.isQualified(s1));
        assertTrue(p1.addSong(s1));
        assertFalse(p1.isQualified(s2));
        assertFalse(p1.addSong(s2));
        p1.addSong(s1);
        assertFalse(p1.isFull());
        p1.addSong(s1);
        p1.getSongs();
        assertFalse(p1.addSong(s1));
        assertTrue(p1.isFull());
    }


    // ----------------------------------------------------------
    /**
     * tests coverting a playlist to a stirng
     */
    public void testToString()
    {
        p1.addSong(s1);
        assertEquals(
            "Playlist: playlist1, # of songs: 1 (cap: 3), Requires:"
                + " Pop:10%-80%, Rock:15%-85%, Country:5%-50%",
            p1.toString());
    }


    // ----------------------------------------------------------
    /**
     * tests using equals on all cases for playlist
     */
    public void testEquals()
    {
        assertFalse(p1.equals(p2));
        assertTrue(p1.equals(p1));
        assertFalse(p1.equals(s1));
        assertFalse(p1.equals(p4));
        p1.addSong(s1);
        p3.addSong(s1);
        assertTrue(p1.equals(p3));
    }


    // ----------------------------------------------------------
    /**
     * tests comparing various playlist to one another
     */
    public void testComapreTo()
    {
        assertEquals(1, p1.compareTo(p4));
        p3.addSong(s1);
        assertEquals(1, p1.compareTo(p3));
        assertEquals(-1, p1.compareTo(p5));
        assertEquals(-1, p1.compareTo(p6));
        assertEquals(-2, p1.compareTo(p7));
    }
}
