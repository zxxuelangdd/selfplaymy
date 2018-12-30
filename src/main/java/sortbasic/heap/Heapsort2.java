package sortbasic.heap;

public class Heapsort2 {
	
	private Heapsort2() {};
	
	public static void  sort(Comparable[] arr) {

		int n = arr.length;
	        MaxHeap<Comparable> maxHeap = new MaxHeap<Comparable>(arr);
	        for( int i = n-1 ; i >= 0 ; i -- )
	            arr[i] = maxHeap.extractMax();
    }

}
