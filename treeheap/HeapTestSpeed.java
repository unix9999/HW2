import static org.junit.Assert.*;

import org.junit.Test;

public class HeapTestSpeed {

	@Test
	public void test() {
		 final long startTime = System.currentTimeMillis();
		 Heap<Integer> mh= new Heap<Integer>();
		 int n = 10000;
		 for (int i = 0; i < n; i++){
			 double t = 1+Math.random()*n;
			 mh.add(i, t);
		 }
		 final long endTime = System.currentTimeMillis();
		 System.out.println("Total time for pointer heap:" + (endTime - startTime) );
		 
	}

	@Test
	public void test2() {
		 final long startTime = System.currentTimeMillis();
		 ArrayHeap mh= new ArrayHeap();
		 int n = 10000;
		 for (int i = 0; i < n; i++){
			 int t = (int) (1+Math.random()*n);
			 mh.add(t);
		 }
		 final long endTime = System.currentTimeMillis();
		 System.out.println("Total time for Array heap:" + (endTime - startTime) );
		 
	}
	
	
	
}
