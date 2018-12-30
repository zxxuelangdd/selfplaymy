package sortbasic.heap;

import java.util.Arrays;

import sortbasic.selectcomparable.SortHealper;

public class HeapTest {

	public static void main(String[] args){
		int N=1000000;
		//MaxHeap<Comparable> maxHeap = new MaxHeap<>(N);
 	Integer[] arr1 = SortHealper.generateRandomArray(N, 0, N);
 	Integer[] arr2 = Arrays.copyOf(arr1, arr1.length);
 	Integer[] arr3 = Arrays.copyOf(arr1, arr1.length);
//		Integer[] arr = SortHealper.generateNearlyOrderedArray(N, 100);
		
		 SortHealper.testSort("sortbasic.heap.Heapsort1", arr1,"sort");
		//SortHealper.printArray(arr1);
 	     SortHealper.testSort("sortbasic.heap.Heapsort2", arr2,"sort");
		//SortHealper.printArray(arr2);
 	   // SortHealper.printArray(arr2);
    	SortHealper.testSort("sortbasic.heap.Heapsort", arr3,"sort");
    	System.out.println(SortHealper.isSorted(arr3));
    	//SortHealper.printArray(arr2);
		/*Integer[] arr = SortHealper.generateRandomArray(N, 0, 10);
		for (int i = 0; i < arr.length; i++) {
			maxHeap.insert(arr[i]);
		}
		long be = System.currentTimeMillis();
		Integer[] integer = new Integer[N];
		for (int i = arr.length-1; i >=0; i--) {
			integer[i]=(Integer) maxHeap.extractMax();
		}
		long en = System.currentTimeMillis();
		System.out.println("time:"+(en-be));
		boolean sorted = SortHealper.isSorted(integer);
		System.out.println("sorted:"+sorted);
		System.out.println(integer.length);
		for (int i = 0; i < integer.length; i++) {
			System.out.print(integer[i]+",");
		}*/
	}
}
