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
 * @version Nov 7, 2023
 */
public class PlaylistCalculatorTest
    extends student.TestCase
{
    private PlaylistCalculator calc;
    private ArrayQueue<Song> queue;
    private Playlist p2;
    private Song s1;
    private Song s2;
    private Song s3;

    // ----------------------------------------------------------
    /**
     * sets upo variables in test cases
     */
    public void setUp()
    {
        queue = new ArrayQueue<Song>();
        Playlist[] playlists = new Playlist[3];
        Playlist p1 = new Playlist("playlist1", 0, 5, 10, 85, 90, 80, 3);
        p2 = new Playlist("playlist2", 10, 0, 0, 95, 90, 90, 5);
        Playlist p3 = new Playlist("playlist3", 5, 10, 10, 60, 80, 75, 4);
        s1 = new Song("Song", 50, 50, 50, "playlist2");
        s2 = new Song("Song2", 45, 45, 45, "");
        s3 = new Song("Song3", 5, 10, 10, "playlist2");
        playlists[0] = p1;
        playlists[1] = p2;
        playlists[2] = p3;
        queue.enqueue(s1);
        queue.enqueue(s3);
        queue.enqueue(s2);

        calc = new PlaylistCalculator(queue, playlists);
    }


    // ----------------------------------------------------------
    /**
     * tests adding songs to playlists
     */
    public void testGetPlaylistForSong()
    {
        assertEquals(p2, calc.getPlaylistForSong(s1));
        assertEquals(p2, calc.getPlaylistForSong(s2));
        assertEquals(null, calc.getPlaylistForSong(s3));
    }


    // ----------------------------------------------------------
    /**
     * tests adding songs to playlists
     */
    public void testAddSongToPlaylist()
    {
        assertTrue(calc.addSongToPlaylist());
        assertFalse(calc.addSongToPlaylist());
        assertFalse(calc.addSongToPlaylist());
        assertFalse(calc.addSongToPlaylist());
    }


    // ----------------------------------------------------------
    /**
     * tests rejecting a song
     */
    public void testReject()
    {
        calc.reject();
        assertEquals(s3, queue.getFront());
        calc.getQueue();
        calc.getPlaylists();
    }


    // ----------------------------------------------------------
    /**
     * tests getting the index of certain playlists
     */
    public void testGetPlaylistIndex()
    {
        assertEquals(1, calc.getPlaylistIndex("playlist2"));
        assertEquals(-1, calc.getPlaylistIndex("none"));
    }
}
