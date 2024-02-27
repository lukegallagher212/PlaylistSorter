package dailymixes;

import java.util.Arrays;
import list.AList;

// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Luke Gallagher (lukegallagher212)

// -------------------------------------------------------------------------
/**
 * used to calculate where various songs in the queue should be added, and into
 * what playlists
 * 
 * @author lukegallagher
 * @version Nov 6, 2023
 */
public class PlaylistCalculator
{
    private Playlist[] playlists;
    /**
     * has to be at least 3 playlists
     */
    public static final int NUM_PLAYLISTS = 3;
    /**
     * at the least 0 percentage on a song
     */
    public static final int MIN_PERCENTAGE = 0;
    /**
     * at most 100 percentage on a song
     */
    public static final int MAX_PERCENTAGE = 100;
    private AList<Song> rejectedTracks;
    private ArrayQueue<Song> songQueue;

    // ----------------------------------------------------------
    /**
     * Create a new PlaylistCalculator object.
     * 
     * @param queue
     *            is the array queue of songs being placed into playlists
     * @param playL
     *            is the array of playlists that songs can be added into
     */
    public PlaylistCalculator(ArrayQueue<Song> queue, Playlist[] playL)
    {
        if (queue == null)
        {
            throw new IllegalArgumentException();
        }
        songQueue = queue;
        playlists = playL;
        rejectedTracks = new AList<Song>();
    }


    // ----------------------------------------------------------
    /**
     * adds the song given to the best playlist
     * 
     * @param nextSong
     *            next song in the array queue
     * @return the playlist the song should be added to
     */
    public Playlist getPlaylistForSong(Song nextSong)
    {
        if (nextSong == null)
        {
            return null;
        }
        for (int i = 0; i < playlists.length; i++)
        {
            if (playlists[i].getName().equals(nextSong.getPlaylistName()))
            {
                if (!playlists[i].isFull()
                    && playlists[i].isQualified(nextSong))
                {
                    return playlists[i];
                }
                return null;
            }
        }

        return getPlaylistWithMostRoom(nextSong);
    }


    private Playlist getPlaylistWithMostRoom(Song anEntry)
    {
        Playlist[] playlistsCopy = playlists.clone();
        Arrays.sort(playlistsCopy);

        for (int i = playlistsCopy.length - 1; i >= 0; i--)
        {
            if (playlistsCopy[i].isQualified(anEntry))
            {
                return playlistsCopy[i];
            }
        }
        return null;
    }


    // ----------------------------------------------------------
    /**
     * looks for if there is a playlist to add the next song in the queue, and
     * does so if their is.
     * 
     * @return weather the song was sucessfully added to a playlist
     */
    public boolean addSongToPlaylist()
    {
        if (songQueue.isEmpty())
        {
            return false;
        }
        if (getPlaylistForSong(songQueue.getFront()) != null)
        {
            getPlaylistForSong(songQueue.getFront())
                .addSong(songQueue.getFront());
            songQueue.dequeue();
            return true;
        }
        return false;
    }


    // ----------------------------------------------------------
    /**
     * puts the next queue entry into a list of rejected songs
     */
    public void reject()
    {
        rejectedTracks.add(songQueue.getFront());
        songQueue.dequeue();
    }


    // ----------------------------------------------------------
    /**
     * looks for the playlist that matches the string of the name of the
     * playlist.
     * 
     * @param playlistName
     *            the string of the playlist being looked for
     * @return the index of the playlist in playlists array
     */
    public int getPlaylistIndex(String playlistName)
    {
        for (int i = 0; i < playlists.length; i++)
        {
            if (playlists[i].getName().equals(playlistName))
            {
                return i;
            }
        }
        return -1;
    }


    // ----------------------------------------------------------
    /**
     * getter for songQueue
     * 
     * @return the array queue of songs
     */
    public ArrayQueue<Song> getQueue()
    {
        return songQueue;
    }


    // ----------------------------------------------------------
    /**
     * getter for playlists
     * 
     * @return the array of playlists
     */
    public Playlist[] getPlaylists()
    {
        return playlists;
    }
}
