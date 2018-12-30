package sortbasic.selectcomparable;

import java.util.Arrays;

import com.sun.javafx.image.impl.IntArgb;

public class MergeSort {
	
	//不匀速产生任何实例
	private MergeSort() {};
	
	public static void sort(Comparable[] arr) {
		sort(arr,0,arr.length-1);
	}

	private static void sort(Comparable[] arr, int i, int j) {
		if(j>i) {
		
		int mid=i+(j-i)/2;
		sort(arr, i, mid);
		sort(arr, mid+1, j);
		merge(arr,i,mid,j);
		}
	}
	
	
	public static void sort2(Comparable[] arr) {
		sort2(arr,0,arr.length-1);
	}
	public static void sort3(Comparable[] arr) {

		 int n = arr.length;

	        // Merge Sort Bottom Up 无优化版本
//	        for (int sz = 1; sz < n; sz *= 2)
//	            for (int i = 0; i < n - sz; i += sz+sz)
//	                // 对 arr[i...i+sz-1] 和 arr[i+sz...i+2*sz-1] 进行归并
//	                merge(arr, i, i+sz-1, Math.min(i+sz+sz-1,n-1));

	        // Merge Sort Bottom Up 优化
	        // 对于小数组, 使用插入排序优化
	        for( int i = 0 ; i < n ; i += 16 )
	        	InsertSort.sort(arr, i, Math.min(i+15, n-1) );

	        for( int sz = 16; sz < n ; sz += sz )
	            for( int i = 0 ; i < n - sz ; i += sz+sz )
	                // 对于arr[mid] <= arr[mid+1]的情况,不进行merge
	                if( arr[i+sz-1].compareTo(arr[i+sz]) > 0 )
	                    merge(arr, i, i+sz-1, Math.min(i+sz+sz-1,n-1) );
	}

	private static void sort2(Comparable[] arr, int i, int j) {
		if(j-i<15) {
			InsertSort.sort(arr, i, j);
			return;
		}
		
		int mid=i+(j-i)/2;
		sort(arr, i, mid);
		sort(arr, mid+1, j);
		merge(arr,i,mid,j);
	}
	//归并 
	private static void  merge(Comparable[] arr, int l, int mid, int h) {
		assert h>l;
		Integer[] res=new Integer[h-l+1];
		int k=l,j=mid+1;
		int g=0;
		for (int i = k; i <=h; i++) {
			if(k<=mid && j<=h ) {
				if(arr[k].compareTo(arr[j])<0) {
					res[i-l]=(Integer) arr[k];
					k++;
					
				}else {
					res[i-l]=(Integer) arr[j];
					j++;
				}
				
			}else {
				if(k>mid) {
					res[i-l]=(Integer) arr[j];
					j++;
				}else {
					res[i-l]=(Integer) arr[k];
					k++;
				}
				
			}
		}
		
		for (int i = 0; i < res.length; i++) {
			arr[l+i]=res[i];
		}
		
		
	}
	
    // 测试MergeSort
    public static void main(String[] args) {
        // Merge Sort是我们学习的第一个O(nlogn)复杂度的算法
        // 可以在1秒之内轻松处理100万数量级的数据
        // 注意：不要轻易尝试使用SelectionSort, InsertionSort或者BubbleSort处理100万级的数据
        // 否则，你就见识了O(n^2)的算法和O(nlogn)算法的本质差异：）
        int N = 100000;
        Integer[] arr = SortHealper.generateRandomArray(N, 0, 1000000);
        SortHealper.printArray(arr);
        SortHealper.testSort("sortbasic.selectcomparable.MergeSort", arr,"sort2");
       SortHealper.printArray(arr);
        return;
    }
}
