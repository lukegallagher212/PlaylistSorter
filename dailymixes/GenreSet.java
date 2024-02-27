package dailymixes;

// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Luke Gallagher (lukegallagher212)
// -------------------------------------------------------------------------
/**
 * A class that gets the ranges of the genre composition of songs
 * 
 * @author lukegallagher
 * @version Oct 23, 2023
 */
public class GenreSet
    implements Comparable<GenreSet>
{
    private int rock;
    private int pop;
    private int country;

    // ----------------------------------------------------------
    /**
     * Create a new GenreSet object.
     * 
     * @param r
     *            represents the rock percentage
     * @param p
     *            represents the pop percentage
     * @param c
     *            represents the country percentage
     */
    public GenreSet(int p, int r, int c)
    {
        pop = p;
        rock = r;
        country = c;
    }


    // ----------------------------------------------------------
    /**
     * getter for rock
     * 
     * @return rock composition
     */
    public int getRock()
    {
        return rock;
    }


    // ----------------------------------------------------------
    /**
     * getter for pop
     * 
     * @return pop composition
     */
    public int getPop()
    {
        return pop;
    }


    // ----------------------------------------------------------
    /**
     * getter for country
     * 
     * @return country composition
     */
    public int getCountry()
    {
        return country;
    }


    private boolean isLessThanOrEqualTo(GenreSet other)
    {
        return (this.pop <= other.pop || this.rock <= other.rock
            || this.country <= other.country);
    }


    // ----------------------------------------------------------
    /**
     * returns weather the composition is between the ranges of 2 given sets
     * 
     * @param minGenreSet
     *            is the minimum percentages of music type
     * @param maxGenreSet
     *            is the maximum percentages of music type
     * @return weather the GenreSet is within the range needed of each type of
     *             music
     */
    public boolean isWithinRange(GenreSet minGenreSet, GenreSet maxGenreSet)
    {
        return (!this.isLessThanOrEqualTo(minGenreSet)
            && this.isLessThanOrEqualTo(maxGenreSet));
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
            GenreSet other = (GenreSet)obj;
            return (this.country == other.country && this.pop == other.pop
                && this.rock == other.rock);
        }
        return false;

    }


    // ----------------------------------------------------------
    /**
     * gives an int that represnts percentage composition when comparing the
     * Genre Sets
     * 
     * @param other
     *            is the genre set being compared
     * @return postive, zero, or negative based on the sum of the comparison
     */
    public int compareTo(GenreSet other)
    {
        int composition = this.country + this.rock + this.pop;
        int otherComposition = other.country + other.rock + other.pop;
        if (composition < otherComposition)
        {
            return -1;
        }
        if (composition > otherComposition)
        {
            return 1;
        }
        return 0;
    }


    @Override
    public String toString()
    {
        return ("Pop:" + this.pop + " Rock:" + this.rock + " Country:"
            + this.country);
    }
}
