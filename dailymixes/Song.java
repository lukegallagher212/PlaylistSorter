package dailymixes;

// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Luke Gallagher (lukegallagher212)

// -------------------------------------------------------------------------
/**
 * A song object consists of a name, percentages of pop, rock, and country
 * composition, and a genere set it should belong to.
 * 
 * @author lukegalalgher
 * @version Oct 30, 2023
 */
public class Song
{
    private String name;
    private String suggestedPlaylist;
    private GenreSet genreSet;

    // ----------------------------------------------------------
    /**
     * Create a new Song object.
     * 
     * @param name
     *            is the name of the song
     * @param pop
     *            composition of pop
     * @param rock
     *            composition of rock
     * @param country
     *            composition of country
     * @param suggested
     *            the suggested playlist that this song should go into
     */
    public Song(String name, int pop, int rock, int country, String suggested)
    {
        this.name = name;
        this.suggestedPlaylist = suggested;
        genreSet = new GenreSet(pop, rock, country);

    }


    // ----------------------------------------------------------
    /**
     * returns the playlist name
     * 
     * @return name this.suggestedPlaylist
     */
    public String getPlaylistName()
    {
        return (this.suggestedPlaylist);
    }


    // ----------------------------------------------------------
    /**
     * returns the song name
     * 
     * @return this.name
     */
    public String getName()
    {
        return (this.name);
    }


    // ----------------------------------------------------------
    /**
     * returns the GenreSet of the song
     * 
     * @return genreSet
     */
    public GenreSet getGenreSet()
    {
        return genreSet;
    }


    @Override
    public String toString()
    {
        StringBuilder str = new StringBuilder();
        if (getPlaylistName().length() == 0)
        {
            str.append("No-Playlist ");

        }
        str.append(getName());
        str.append(" Pop:");
        str.append(getGenreSet().getPop());
        str.append(" Rock:");
        str.append(getGenreSet().getRock());
        str.append(" Country:");
        str.append(getGenreSet().getCountry());

        if (getPlaylistName().length() != 0)
        {
            str.append(" Suggested: ");
            str.append(getPlaylistName());
        }
        return str.toString();
    }


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
            Song other = (Song)obj;
            return (this.getName().equals(other.getName())
                && this.getGenreSet().equals(other.getGenreSet())
                && this.getPlaylistName().equals(other.getPlaylistName()));
        }
        return false;
    }
}
