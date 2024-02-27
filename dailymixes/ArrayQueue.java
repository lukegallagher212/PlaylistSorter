package dailymixes;

import queue.EmptyQueueException;
import queue.QueueInterface;

// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Luke Gallagher (lukegallagher212)

// -------------------------------------------------------------------------
/**
 * Creates an queue with a circular array implementation for incoming songs
 * 
 * @param <T>
 * @author lukegallagher
 * @version Oct 30, 2023
 */
public class ArrayQueue<T>
    implements QueueInterface<T>
{
    private T[] queue;
    private int dequeueIndex;
    private int size;
    private int enqueueIndex;

    /**
     * default capacity for the array queue
     */
    public static final int DEFAULT_CAPACITY = 20;

    // ----------------------------------------------------------
    /**
     * Create a new ArrayQueue object with a default capacity of 20
     * 
     * @param <T>
     *            the generic being stored in the queue
     */
    @SuppressWarnings("hiding")
    public <T> ArrayQueue()
    {
        this(DEFAULT_CAPACITY);
        dequeueIndex = 0;
        enqueueIndex = queue.length - 1;
        size = 0;
    }


    // ----------------------------------------------------------
    /**
     * Create a new ArrayQueue object with a given capacity
     * 
     * @param cap
     *            is the capacity of the array
     */
    @SuppressWarnings({ "unchecked" })
    public ArrayQueue(int cap)
    {
        queue = (T[])new Object[cap];
        dequeueIndex = 0;
        enqueueIndex = queue.length - 1;
        size = 0;
    }


    // ----------------------------------------------------------
    /**
     * returns how many elements are in the queue.
     * 
     * @return size
     */
    public int getSize()
    {
        return size;
    }


    // ----------------------------------------------------------
    /**
     * gets the length of the underlying array
     * 
     * @return length of queue + 1
     */
    public int getLengthOfUnderlyingArray()
    {
        return (queue.length);
    }


    @SuppressWarnings("unchecked")
    private void ensureCapacity()
    {
        if ((enqueueIndex + 2) % queue.length == dequeueIndex)
        {
            T[] originalQueue = queue;
            int originalSize = originalQueue.length;
            int newSize = 2 * originalSize;
            queue = (T[])new Object[newSize];
            int j = dequeueIndex;
            for (int i = 0; i < originalSize - 1; i++)
            {
                queue[i] = originalQueue[j];
                j = (j + 1) % originalSize;
            }
            dequeueIndex = 0;
            enqueueIndex = originalSize - 2;
        }
    }


    private int incrementIndex(int index)
    {
        return ((index + 1) % queue.length);
    }


    // ----------------------------------------------------------
    /**
     * turns the queue into an array that starts at index zero and only have as
     * many elements as teh queue size.
     * 
     * @return an array of songs
     */
    public Object[] toArray()
    {
        int index = dequeueIndex;
        @SuppressWarnings("unchecked")
        T[] array = (T[])new Object[size];
        for (int i = 0; i < size; i++)
        {
            array[i] = queue[index];
            index = incrementIndex(index);
        }
        return array;
    }


    // ----------------------------------------------------------
    /**
     * turns the queue into a string of the songs
     * 
     * @return a string of the songs in the queue.
     */
    @Override
    public String toString()
    {
        Object[] array = toArray();
        StringBuilder str = new StringBuilder();
        if (size == 0)
        {
            return ("[]");
        }
        str.append("[" + array[0]);
        if (size > 1)
        {
            for (int i = 1; i < array.length; i++)
            {
                str.append(", " + array[i]);
            }
        }
        str.append("]");
        return str.toString();
    }


    // ----------------------------------------------------------
    /**
     * clears all the elements of the queue entriely
     */
    @Override
    public void clear()
    {
        while (size > 1)
        {
            dequeue();
        }
        dequeueIndex = 0;
        size = 0;
        enqueueIndex = 0;
    }


    // ----------------------------------------------------------
    /**
     * gets rid of the element at the front of the queue and returns the value
     * 
     * @return the data that was dequeued
     */
    @Override
    public T dequeue()
    {
        T front = getFront();
        queue[dequeueIndex] = null;
        dequeueIndex = incrementIndex(dequeueIndex);
        size--;
        return front;
    }


    // ----------------------------------------------------------
    /**
     * adds the parameter given to teh back of the queue
     * 
     * @param anEntry
     *            data that is wanting to be entered
     */
    @Override
    public void enqueue(T anEntry)
    {
        ensureCapacity();
        enqueueIndex = incrementIndex(enqueueIndex);
        queue[enqueueIndex] = anEntry;
        size++;

    }


    // ----------------------------------------------------------
    /**
     * returns the front of the queue
     * 
     * @return what is at the front
     */
    @Override
    public T getFront()
    {
        if (isEmpty())
        {
            throw new EmptyQueueException();
        }

        return (queue[dequeueIndex]);
    }


    // ----------------------------------------------------------
    /**
     * determines weather the queue is empty
     * 
     * @return the value of wheather it is empty or not
     */
    @Override
    public boolean isEmpty()
    {
        return (((enqueueIndex + 1) % queue.length) == dequeueIndex);
    }

}
