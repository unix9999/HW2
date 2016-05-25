import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

import org.junit.Test;


public class ArrayHeapTester {

    @Test
    /** Test add with no duplicate priorities. */
    public void testAdd() {
        ArrayHeap mh= new ArrayHeap();
        mh.add(5);
        assertEquals("[5]", mh.toString());
        assertEquals(1, mh.size());

        mh.add(7);
        assertEquals("[5, 7]", mh.toString());
        assertEquals(2, mh.size());
        mh.add(3);
        assertEquals("[3, 7, 5]", mh.toString());
        assertEquals(3, mh.size());
        mh.add(1);
        assertEquals("[1, 3, 5, 7]", mh.toString());
        assertEquals(4, mh.size());
        mh.add(9);
        assertEquals("[1, 3, 5, 7, 9]", mh.toString());
        assertEquals(5, mh.size());
        mh.add(2);
        assertEquals("[1, 3, 2, 7, 9, 5]", mh.toString());
        assertEquals(6, mh.size());
        mh.add(0);
        assertEquals("[0, 3, 1, 7, 9, 5, 2]", mh.toString());
        assertEquals(7, mh.size());
    }

    @Test
    /** Test peek. */
    public void testPeek() {
        ArrayHeap mh= new ArrayHeap();
        mh.add(3);
        assertEquals(3, mh.peek());
        mh.add(1);
        assertEquals(1, mh.peek());
        mh.add(-5);
        assertEquals(-5, mh.peek());
    }

    @Test
    /** Test poll with no duplicate. */
    public void testPoll() {
        ArrayHeap mh= new ArrayHeap();
        mh.add(5);
        int res= mh.poll();
        assertEquals("[]", mh.toString());
        assertEquals(0, mh.size());
        assertEquals(5, res);

        mh= new ArrayHeap();
        mh.add(5);
        mh.add(3);
        assertEquals("[3, 5]", mh.toString());
        res= mh.poll();
        assertEquals("[5]", mh.toString());
        assertEquals(1, mh.size());
        assertEquals(3, res);

        mh= new ArrayHeap();
        mh.add(3);
        mh.add(5);
        mh.add(7);
        assertEquals("[3, 5, 7]", mh.toString());
        res= mh.poll();
        assertEquals("[5, 7]", mh.toString());
        assertEquals(2, mh.size());
        assertEquals(3, res);

        mh= new ArrayHeap();
        mh.add(3);
        mh.add(7);
        mh.add(5);
        assertEquals("[3, 7, 5]", mh.toString());
        res= mh.poll();
        assertEquals("[5, 7]", mh.toString());
        assertEquals(2, mh.size());
        assertEquals(3, res);

        int[] b= new int[] {0, 1, 2, 3, 7, 6, 4, 5, 8, 9, 10, 11, 15, 14, 13, 12};
        mh= new ArrayHeap();
        for (int k= 0; k < b.length; k= k+1) {
           // System.out.println("Adding " + b[k]);
            mh.add(b[k]);
           // System.out.println("heap is " + mh);
        }
        //System.out.println("Just added elements: " + mh);

        //System.out.println("heap is: m " + mh);

        for (int k= 0; k < b.length; k= k+1) {
            int result= mh.poll();
           // System.out.println("Just polled: " + result);
           // System.out.println(mh);
            assertEquals(k, result);
        }
    }

    @Test
    /** Test add and poll with duplicates. */
    public void testAddAndPollWithDuplicates() {
         Random rand= new Random(20);

        // The values to put in heap
        int[] b= new int[1000];
        for (int k= 0; k < b.length; k= k+1) {
            b[k]= rand.nextInt(40);
        }

        // Build the heap and map to be able to get priorities easily
        ArrayHeap mh= new ArrayHeap();
        for (int k= 0; k < b.length; k= k+1) {
             mh.add(b[k]);
        }
        

        // poll values one by one, check that priorities are in order, store
        // in dups the number of duplicate priorities, and save polled values
        // in array bpoll.
        int prevPriority= -1;
        int dups= 0; //Number of duplicate keys,
        int[] bpoll= new int[b.length];
        for (int k= 0; k < b.length; k= k+1) {
            bpoll[k]= mh.poll();
            
            if (bpoll[k] == prevPriority) {
                dups= dups + 1;
            }
            assertEquals(true, prevPriority <= bpoll[k]);
            prevPriority= bpoll[k];
        }

        // Sort bpoll and b and check that they are equal
        Arrays.sort(bpoll);
        Arrays.sort(b);
        for (int k= 0; k < b.length; k= k+1) {
            assertEquals(b[k], bpoll[k]);
        }
        System.out.println("duplicate priorities: " + dups);
    }

}
