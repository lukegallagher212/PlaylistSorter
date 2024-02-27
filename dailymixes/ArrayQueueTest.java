package dailymixes;

import queue.EmptyQueueException;

// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Luke Gallagher (lukegallagher212)
// -------------------------------------------------------------------------
/**
 * tests all the methods in ArrayQueue
 * 
 * @author lukegallagher
 * @version Oct 31, 2023
 */
public class ArrayQueueTest
    extends student.TestCase
{
    private ArrayQueue<String> aq;
    private ArrayQueue<String> dq;

    // ----------------------------------------------------------
    /**
     * sets up all variables used
     */
    public void setUp()
    {
        aq = new ArrayQueue<String>(2);
        dq = new ArrayQueue<String>();
    }


    // ----------------------------------------------------------
    /**
     * tests Enqueue and size
     */
    public void testEnqeueueAndDequeue()
    {
        assertEquals(0, aq.getSize());
        aq.enqueue("A");
        assertEquals(1, aq.getSize());
        aq.enqueue("B");
        assertEquals("A", aq.dequeue());
        assertEquals(1, aq.getSize());
        assertEquals(4, aq.getLengthOfUnderlyingArray());
    }


    // ----------------------------------------------------------
    /**
     * tests get front an any exceptions that may be thrown
     */
    public void testGetFront()
    {
        Exception exception = null;
        try
        {
            aq.getFront();
        }
        catch (EmptyQueueException e)
        {
            exception = e;
        }
        assertTrue(exception instanceof EmptyQueueException);
        assertTrue(aq.isEmpty());
        aq.enqueue("A");
        assertEquals("A", aq.getFront());
        assertFalse(aq.isEmpty());
        aq.enqueue("B");
        aq.enqueue("C");
        assertEquals(4, aq.getLengthOfUnderlyingArray());
        dq.enqueue("A");
        assertEquals("A", dq.getFront());
        assertEquals(20, dq.getLengthOfUnderlyingArray());

    }


    // ----------------------------------------------------------
    /**
     * tests clearing a queue with multiple entries
     */
    public void testClear()
    {
        aq.enqueue("A");
        aq.enqueue("B");
        assertEquals(2, aq.getSize());
        aq.clear();
        assertEquals(0, aq.getSize());

    }


    // ----------------------------------------------------------
    /**
     * tests turning a queue into an array of elements
     */
    public void testToArray()
    {
        Object[] arr = new Object[3];
        arr[0] = "A";
        arr[1] = "B";
        arr[2] = "C";
        aq.enqueue("A");
        aq.enqueue("B");
        aq.enqueue("C");
        System.out.println(aq);
        // assertEquals(newArr, aq.toArray());\
        assertEquals(3, aq.getSize());
    }


    // ----------------------------------------------------------
    /**
     * tests converting queues into strings
     */
    public void testToString()
    {
        assertEquals("[]", aq.toString());
        aq.enqueue("A");
        aq.enqueue("B");
        aq.enqueue("C");
        aq.enqueue("D");
        System.out.println(aq);
        assertEquals("[A, B, C, D]", aq.toString());
    }

}
