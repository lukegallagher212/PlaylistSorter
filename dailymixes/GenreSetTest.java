package dailymixes;

// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Luke Gallagher (lukegallagher212)
// -------------------------------------------------------------------------
/**
 * tests all methods of genre set
 * 
 * @author lukegallagher
 * @version Oct 31, 2023
 */
public class GenreSetTest
    extends student.TestCase
{
    private GenreSet min;
    private GenreSet mid;
    private GenreSet max;
    private GenreSet mid2;
    private GenreSet nSet;
    private int num;

    // ----------------------------------------------------------
    /**
     * sets up variables
     */
    public void setUp()
    {
        min = new GenreSet(10, 10, 10);
        mid = new GenreSet(50, 50, 50);
        max = new GenreSet(90, 90, 90);
        mid2 = new GenreSet(50, 50, 50);
        nSet = null;
        num = 5;

    }


    // ----------------------------------------------------------
    /**
     * tests getting the pop value
     */
    public void testGetPop()
    {
        assertEquals(10, min.getPop());
    }


    // ----------------------------------------------------------
    /**
     * tests getting the rock value
     */
    public void testGetRock()
    {
        assertEquals(50, mid.getRock());
    }


    // ----------------------------------------------------------
    /**
     * tests getting the country value
     */
    public void testGetCountry()
    {
        assertEquals(90, max.getCountry());
    }


    // ----------------------------------------------------------
    /**
     * tests the inWithinRange method as well as the private helper method,
     * isLessThanOrEqualTo
     */
    public void testIsWithinRange()
    {
        assertTrue(mid.isWithinRange(min, max));
        assertFalse(min.isWithinRange(min, mid));
    }


    // ----------------------------------------------------------
    /**
     * tests equals method
     */
    public void testEquals()
    {
        assertTrue(mid.equals(mid));
        assertTrue(mid.equals(mid2));
        assertFalse(mid.equals(min));
        assertFalse(mid.equals(nSet));
        assertFalse(mid.equals(num));

    }


    // ----------------------------------------------------------
    /**
     * testing all possible cases for compareTo
     */
    public void testCompareTo()
    {
        assertEquals(-1, mid.compareTo(max));
        assertEquals(0, mid.compareTo(mid2));
        assertEquals(1, mid.compareTo(min));
    }


    // ----------------------------------------------------------
    /**
     * tests converting a genre set to a string
     */
    public void testToString()
    {
        assertEquals("Pop:50 Rock:50 Country:50", mid.toString());
    }
}
