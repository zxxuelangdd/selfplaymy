package sortbasic.heap;

public class Heapsort1 {
	
	private Heapsort1() {};
	
	public static void  sort(Comparable[] arr) {

		int n = arr.length;
		MaxHeap<Comparable> maxHeap = new MaxHeap<>(n);
		for (int i = 0; i < arr.length; i++) {
			maxHeap.insert(arr[i]);
		}
		for( int i = n-1 ; i >= 0 ; i -- )
            arr[i] = maxHeap.extractMax();
    }

}
