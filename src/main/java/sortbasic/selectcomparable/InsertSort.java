package sortbasic.selectcomparable;

import java.util.Arrays;

/**
 * 插入排序
 *
 */
public class InsertSort {

	private InsertSort() {
	}
	
	//插入排序
	public static void sort(Comparable[] arr) {
		
		for (int i = 0; i < arr.length; i++) {
			for (int j = i; j >0 && arr[j].compareTo(arr[j-1])<0; j--) {
				swap(arr, j,j-1);
			}
		}
		
	}
	public static void sort(Comparable[] arr,int low,int high) {
		int n = arr.length;
		for (int i = low; i < high+1; i++) {
    		// 寻找元素arr[i]合适的插入位置
    		// 写法3  将交换改进成比较  复制一份出来 让数组内部复制 减少交换次数
    		Comparable c = arr[i];
    		int j = i;
    		for( ; j > 0 && c.compareTo(arr[j-1]) < 0 ; j--)
    			arr[j]=arr[j-1];
    		arr[j]=c;
    	}
	}
	//插入排序的另一种写法
	 public static void sort2(Comparable[] arr){
	    	
	    	int n = arr.length;
	    	for (int i = 0; i < n; i++) {
	    		// 寻找元素arr[i]合适的插入位置
	    		// 写法3  将交换改进成比较  复制一份出来 让数组内部复制 减少交换次数
	    		Comparable c = arr[i];
	    		int j = i;
	    		for( ; j > 0 && c.compareTo(arr[j-1]) < 0 ; j--)
	    			arr[j]=arr[j-1];
	    		
	    		arr[j]=c;
	    	}
	    }
	 //插入排序的另一种写法
	 public static void sort3(Comparable[] arr){
		Object[] array = Arrays.asList(arr).parallelStream().sorted().toArray();
		System.out.println(array[50000]+"===========");
		//System.out.println();
		// Arrays.sort(arr);
		// boolean sorted = SortHealper.isSorted(arr);
		// System.out.println("Arrays.sort(arr):"+sorted);
	 }

	private static void swap(Object[] arr, int j, int i) {
		Object obj = arr[i];
		arr[i]=arr[j];
		arr[j]=obj;
		
	}

	 public static void main(String[] args) {
		 
	/*	Integer[] arr = SortHealper.generateRandomArray(10000, 0, 3);
		Integer[] arr2 = Arrays.copyOf(arr, arr.length);
		SortHealper.testSort("sortbasic.selectcomparable.InsertSort", arr,"sort2");
		SortHealper.testSort("sortbasic.selectcomparable.SelectionSort", arr2,"sort");
		SortHealper.printArray(arr);
		SortHealper.printArray(arr2);
		System.out.println("====================================");*/
		/*
		Integer[] arr3 =  SortHealper.generateRandomArray(10000, 0, 10000);
		Integer[] arr4 = Arrays.copyOf(arr3, arr3.length);
		SortHealper.testSort("sortbasic.selectcomparable.InsertSort", arr3,"sort2");
		SortHealper.testSort("sortbasic.selectcomparable.SelectionSort", arr4,"sort");
		SortHealper.printArray(arr3);
		SortHealper.printArray(arr4);
		
		Integer[] arr5 = SortHealper.generateNearlyOrderedArray(10000,10);
		Integer[] arr6 = Arrays.copyOf(arr5, arr5.length);
		SortHealper.testSort("sortbasic.selectcomparable.InsertSort", arr5,"sort2");
		SortHealper.testSort("sortbasic.selectcomparable.InsertSort", arr6,"sort");
		SortHealper.printArray(arr5);
		SortHealper.printArray(arr6);*/
		
		Integer[] arr7 = SortHealper.generateRandomArray(1000000,0,1000000);
		// Integer[] arr7 = SortHealper.generateNearlyOrderedArray(1000000,100);
		Integer[] arr8 = Arrays.copyOf(arr7, arr7.length);
		Integer[] arr9 = Arrays.copyOf(arr7, arr7.length);
		Integer[] arr10 = Arrays.copyOf(arr7, arr7.length);
		Integer[] arr11 = Arrays.copyOf(arr7, arr7.length);
		Integer[] arr12 = Arrays.copyOf(arr7, arr7.length);
		Integer[] arr13 = Arrays.copyOf(arr7, arr7.length);
		//SortHealper.testSort("sortbasic.selectcomparable.InsertSort", arr7,"sort2");
		//SortHealper.testSort("sortbasic.selectcomparable.InsertSort", arr7,"sort2");
		SortHealper.testSort("sortbasic.selectcomparable.MergeSort", arr8,"sort");
		SortHealper.testSort("sortbasic.selectcomparable.MergeSort", arr9,"sort2");
	//	SortHealper.testSort("sortbasic.selectcomparable.QuickSort", arr11,"sort");
		SortHealper.testSort("sortbasic.selectcomparable.QuickSort2", arr12,"sort");
		SortHealper.testSort("sortbasic.selectcomparable.QuickSort3", arr13,"sort");
		SortHealper.testSort("sortbasic.selectcomparable.InsertSort", arr7,"sort3");
		/*SortHealper.printArray(arr7);
		SortHealper.printArray(arr8);
		SortHealper.printArray(arr9);
		SortHealper.printArray(arr10);
		SortHealper.printArray(arr11);*/
		/*
		 * result 选择排序 : 每次从被选择的数组中找出最小的 index 进行交换    插入排序:将新的元素插入到已经排好序的数组中 
		 *        对于近乎有序的数组插入排序效率高   所以在后续优化的时候当数组范围较小的时候 直接使用插入排序
		 *        
		 *        使用插入排序的时候注意  最终改进 减少了交换次数  直接采用赋值的操作  节省时间
		 * InsertSort : 0
		SelectionSort : 16*/

	}
}
