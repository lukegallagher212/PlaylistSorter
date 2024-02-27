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
public class DailyMixDataException
    extends java.lang.Exception
{
    // ----------------------------------------------------------
    /**
     * Create a new DailyMixDataException object.
     * 
     * @param str
     */
    public DailyMixDataException(String str)
    {
        super(str);
    }
}
