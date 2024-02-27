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
 * @version Nov 4, 2023
 */
public class Playlist
    implements Comparable<Playlist>
{
    private GenreSet minGenreSet;
    private GenreSet maxGenreSet;
    private Song[] songs;
    private int capacity;
    private int numberOfSongs;
    private String name;

    // ----------------------------------------------------------
    /**
     * Create a new Playlist object.
     * 
     * @param playlistName
     *            name of playlist
     * @param minPop
     *            minimum compostion of pop
     * @param minRock
     *            minimum compostion of rock
     * @param minCountry
     *            minimum compostion of country
     * @param maxPop
     *            maximum composition of pop
     * @param maxRock
     *            maximum composition of rock
     * @param maxCountry
     *            maximum composition of country
     * @param playlistCap
     *            number of songs allowed in the playlist
     */
    public Playlist(
        String playlistName,
        int minPop,
        int minRock,
        int minCountry,
        int maxPop,
        int maxRock,
        int maxCountry,
        int playlistCap)
    {
        name = playlistName;
        minGenreSet = new GenreSet(minPop, minRock, minCountry);
        maxGenreSet = new GenreSet(maxPop, maxRock, maxCountry);
        songs = new Song[playlistCap];
        capacity = playlistCap;
        numberOfSongs = 0;
    }


    // ----------------------------------------------------------
    /**
     * getter for minGenreSet
     * 
     * @return the mim genre set of the compositions
     */
    public GenreSet getMinGenreSet()
    {
        return minGenreSet;
    }


    // ----------------------------------------------------------
    /**
     * setter for NewName
     * 
     * @param newName
     *            sets the playlist name to the given newName
     */
    public void setName(String newName)
    {
        this.name = newName;
    }


    // ----------------------------------------------------------
    /**
     * getter for remaining space in the songs array
     * 
     * @return how many spaces are avaiable in songs
     */
    public int getSpacesLeft()
    {
        return this.capacity - this.numberOfSongs;
    }


    // ----------------------------------------------------------
    /**
     * getter for maxGenreSet
     * 
     * @return the max genre set of the compositions
     */
    public GenreSet getMaxGenreSet()
    {
        return maxGenreSet;
    }


    // ----------------------------------------------------------
    /**
     * compares playlists to see what the difference is in playlists
     * 
     * @param other
     *            other playlist being compared
     * @return the int representing the difference in the playlists
     */
    public int compareTo(Playlist other)
    {
        if (this.getCapacity() != other.getCapacity())
        {
            return this.getCapacity() - other.getCapacity();
        }
        if (this.getSpacesLeft() != other.getSpacesLeft())
        {
            return this.getSpacesLeft() - other.getSpacesLeft();
        }
        if (this.getMinGenreSet().compareTo(other.getMinGenreSet()) != 0)
        {
            return this.getMinGenreSet().compareTo(other.getMinGenreSet());
        }
        if (this.getMaxGenreSet().compareTo(other.getMaxGenreSet()) != 0)
        {
            return this.getMaxGenreSet().compareTo(other.getMaxGenreSet());
        }

        return this.getName().compareTo(other.getName());
    }


    // ----------------------------------------------------------
    /**
     * getter for how many songs are in the playlist
     * 
     * @return numberOfSongs variable
     */
    public int getNumberOfSongs()
    {
        return this.numberOfSongs;
    }


    // ----------------------------------------------------------
    /**
     * adds a song to a playlist
     * 
     * @param anEntry
     *            is a new possible song being added
     * @return weather the it was added to the playlist or not
     */
    public boolean addSong(Song anEntry)
    {
        if (isQualified(anEntry)
            && this.getCapacity() > this.getNumberOfSongs())
        {
            songs[numberOfSongs] = anEntry;
            numberOfSongs++;
            return true;
        }
        return false;

    }


    // ----------------------------------------------------------
    /**
     * toStirng of playlist
     * 
     * @return the playlist name, capcity, and range of composition
     */
    @Override
    public String toString()
    {
        StringBuilder str = new StringBuilder();
        str.append("Playlist: ");
        str.append(this.name);
        str.append(", # of songs: ");
        str.append(numberOfSongs);
        str.append(" (cap: ");
        str.append(this.capacity);
        str.append("), Requires: Pop:");
        str.append(minGenreSet.getPop());
        str.append("%-");
        str.append(maxGenreSet.getPop());
        str.append("%, Rock:");
        str.append(minGenreSet.getRock());
        str.append("%-");
        str.append(maxGenreSet.getRock());
        str.append("%, Country:");
        str.append(minGenreSet.getCountry());
        str.append("%-");
        str.append(maxGenreSet.getCountry());
        str.append("%");
        return str.toString();
    }


    // ----------------------------------------------------------
    /**
     * returns in the playlist is full
     * 
     * @return comparing number of songs to the amount of songs the playlist can
     *             hold
     */
    public boolean isFull()
    {
        return (numberOfSongs == this.capacity);
    }


    // ----------------------------------------------------------
    /**
     * two playlist are equals if all 8 input fields are the same, and they have
     * the same songs in the same order.
     * 
     * @return weather the two playlist equals each other
     */
    @Override
    public boolean equals(Object obj)
    {
        if (obj == null)
        {
            return false;
        }
        if (this == obj)
        {
            return true;
        }
        if (this.getClass() == obj.getClass())
        {
            Playlist other = (Playlist)obj;
            return (this.getMinGenreSet().equals(other.getMinGenreSet())
                && this.getMaxGenreSet().equals(other.getMaxGenreSet())
                && this.getName().equals(other.getName()));
        }
        return false;
    }


    // ----------------------------------------------------------
    /**
     * getter for songs
     * 
     * @return returns the song array songs
     */
    public Song[] getSongs()
    {
        return this.songs;
    }


    // ----------------------------------------------------------
    /**
     * getter for capacity
     * 
     * @return capacity of the playlist
     */
    public int getCapacity()
    {
        return this.capacity;
    }


    // ----------------------------------------------------------
    /**
     * getter for name
     * 
     * @return the name of the playlist
     */
    public String getName()
    {
        return this.name;
    }


    // ----------------------------------------------------------
    /**
     * returns weather a new song can be in the playtlist
     * 
     * @param newSong
     *            is a new possible song with compositions
     * @return if the songs genre set is within the playlist genre set bounds
     */
    public boolean isQualified(Song newSong)
    {
        return newSong.getGenreSet()
            .isWithinRange(this.getMinGenreSet(), this.getMaxGenreSet());
    }
}
