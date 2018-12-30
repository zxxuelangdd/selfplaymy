package sortbasic.selectcomparable;

/**
 * 选择排序
 *
 */
public class SelectionSort {
	
	//该算法类不允许产生任何实例
	private SelectionSort() {};
	
	public static void sort(Comparable[] arr) {
		assert arr.length>1;
		for (int i = 0; i < arr.length; i++) {
			
			//寻找[i,n)区间的最小索引
			int minidext=i;
			for (int j = i+1; j < arr.length; j++) {
				if(arr[j].compareTo(arr[minidext])<0)
				minidext=j;
			}
			swap(arr, i, minidext);
		}
	}
	private static void swap(Object[] arr, int j, int i) {
		Object obj = arr[i];
		arr[i]=arr[j];
		arr[j]=obj;
		
	}
	public static void main(String[] args) {
		Integer[] arr = SortHealper.generateRandomArray(10000, 0, 10000);
		SortHealper.testSort("sortbasic.selectcomparable.SelectionSort", arr,"sort");
	}

}
