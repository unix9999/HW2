/* Assignment-2
 * Name: Weilong Guo
 * Net-ID: wg97
 * 
 */

import java.util.*;

/** An instance is a min-heap of distinct elements of type E.
 *  Priorities are double values. Since it's a min-heap, the value
 *  with the smallest priority is at the root of the heap. */
public class Heap<E> {

    private int size; // number of elements in the heap

    /** The heap invariant is given below. Note that / denotes int division.
     * 
     *  b[0..size-1] is viewed as a min-heap, i.e. 
     *  0. The values in b[0..size-1] are all different.
     *  1. Each array element in b[0..size-1] contains a value of the heap.
     *  2. The children of each b[i] are b[2i+1] and b[2i+2].
     *  3. The parent of each b[i] is b[(i-1)/2].
     *  4. Each b[i]'s priority is >= its parent's priority.
     *  5. Priorities for the b[i] used for the comparison in point 4
     *     are given in map. Field map contains one entry for each element of
     *     the heap, so map and b have the same size.
     *     For each element e in the heap, its map entry contains in the Prindex
     *     (for PRIority and INDEX) object the priority of e and its index in b.
     */
    public ArrayList<E> b;
    private HashMap<E, Prindex> map= new HashMap<E, Prindex>();

    /** Constructor: an empty heap. */
    public Heap() {
        b= new ArrayList<E>();
    }

    /** Return a string that gives this heap, in the format:
     * [item0:priority0, item1:priority1, ..., item(N-1):priority(N-1)]
     * Thus, the list is delimited by '['  and ']' and ", " (i.e. a
     * comma and a space char) separate adjacent items. */
    @Override public String toString() {
        String s= "[";
        for (E t : b) {
            if (s.length() > 1) {
                s = s + ", ";
            }
            s = s + t + ":" + map.get(t).priority;
        }
        return s + "]";
    }

    /** Return a string that gives the priorities in this heap,
     * in the format: [priority0, priority1, ..., priority(N-1)]
     * Thus, the list is delimited by '['  and ']' and ", " (i.e. a
     * comma and a space char) separate adjacent items. */
    public String toStringPriorities() {
        String s= "[";
        for (E t : b) {
            if (s.length() > 1) {
                s = s + ", ";
            }
            s = s + map.get(t).priority;
        }
        return s + "]";
    }

    /** Return the number of elements in this heap.
     * This operation takes constant time. */
    public int size() {
        return size;
    }

    /** Add e with priority p to the heap.
     *  Throw an illegalArgumentException if e is already in the heap.
     *  The expected time is logarithmic and the worst-case time is linear
     *  in the size of the queue. */ 
    public void add(E e, double p) throws IllegalArgumentException {
    	if (b.contains(e)) throw new IllegalArgumentException("e is already in the heap");  	
    	b.add(e);
        size = size + 1;  
        Prindex i0 = new Prindex(size-1, p);  
        map.put(e, i0);
    	bubbleUp(b.size()-1);
    }

    /** Return the element of this heap with lowest priority, without
     *  changing the heap. This operation takes constant time.
     *  Throw a HeapException if the heap is empty. */
    public E peek() throws HeapException{
    	if (size == 0){
    		throw new HeapException("It is empty");
    	}
        return b.get(0);
    }

    /** Remove and return the element of this heap with lowest priority.
     *  The expected time is logarithmic and the worst-case time is linear
     *  in the size of the heap.
     *  Throw a HeapException if the heap is empty. */
    public E poll() throws HeapException{
    	if (size == 0){
    		throw new HeapException("It is empty");
    	}
        else if (size == 1){
        	E top = b.get(0);
        	map.remove(b.get(0));
        	b.remove(0);
        	size = size - 1;
        	return top;
        }
        else{
         E val = b.get(0); //top
         E val2 = b.get(size-1); //bottom
         b.remove(size-1);
         b.set(0,val2);
         map.remove(val);
         map.get(val2).index = 0;
         size = size - 1;
         bubbleDown(0);
         return val;
        }
    }

    /** Change the priority of element e to p.
     *  The expected time is logarithmic and the worst-case time is linear
     *  in the size of the heap.
     *  Throw an illegalArgumentException if e is not in the heap. */
    public void changePriority(E e, double p) throws IllegalArgumentException{
    	if (!map.containsKey(e)){
    		throw new IllegalArgumentException("e is not included");
    	}
        int k = map.get(e).index;
        int par = (k-1) / 2; //index of k's parent
        par = par >= 0 ? par : 0;
        int smchildindex = smallerChildOf(k);
        map.get(e).priority = p;
        
       
        if(p < map.get(b.get(par)).priority ){
         bubbleUp(k);
        }
        else if (p > map.get(b.get(smchildindex)).priority){
         bubbleDown(k);
        }
        else{
        	return;
        }
    }

    /** Bubble b[k] up in heap to its right place.
     *  Precondition: Each b[i]'s priority >= parent's priority 
     *                except perhaps for b[k] */
    private void bubbleUp(int k) {
        // TODO1 Do add and bubbleUp together.
        // Do not use recursion; use iteration.
        // Do NOT create new map entries
    	 // Inv: Every b[i] >= its parent except perhaps b[k]
    	int p= (k-1) / 2;  //p is k's parent
    	E bk = b.get(k);
    	E bp = b.get(p);
        while (k > 0 && map.get(b.get(k)).priority < map.get(b.get(p)).priority) {       
            
            b.set(p, bk); 
            b.set(k, bp); 
            map.get(bk).index = p;
            map.get(bp).index = k;
            
            k= p;
            p= (k-1) / 2;  //p is k's parent
            bk = b.get(k);
            bp = b.get(p);        
        }

    }

    /** Bubble b[k] down in heap until it finds the right place.
     *  Precondition: Each b[i]'s priority <= childrens' priorities 
     *                except perhaps for b[k] */
    private void bubbleDown(int k) {
    // Invariant: Every b[i] <= its children except perhaps for b[k]
    	int c = smallerChildOf(k);
    	 E bc = b.get(c); 
         E bk = b.get(k);
        while (c < size && map.get(b.get(k)).priority > map.get(b.get(c)).priority) {   
            // Swap b[k] and b[c]
            b.set(k, bc);  
            b.set(c, bk); 
            map.get(bk).index = c;
            map.get(bc).index = k;
            
            k = c;
            c = smallerChildOf(k);
            bc = b.get(c); 
            bk = b.get(k);
        }
    }

    /** Return the index of the smaller child of b[n]
     *  Precondition: left child exists: 2n+1 < size of heap */
    private int smallerChildOf(int n) {
        int c = 2*n + 1; //left children  
        if (c > size-1){
        	return n;
        }
        else if (c == size-1 || c+1 < size && map.get(b.get(c)).priority < map.get(b.get(c+1)).priority) {
        	return c;
        }
        return c+1 ;
    }

    /** An instance contains the priority and index an element of the heap. */
    private static class Prindex {
        private double priority; // priority of this element
        private int index;  // index of this element in map

        /** Constructor: an instance in b[i] with priority p. */
        private Prindex(int i, double p) {
            index= i;
            priority= p;
        }
    }
}