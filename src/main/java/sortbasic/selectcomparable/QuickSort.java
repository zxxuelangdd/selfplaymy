package sortbasic.selectcomparable;

import java.util.Random;

public class QuickSort {
	
	private QuickSort() {
	}
	private static int o=0;

	public static void sort(Comparable[] arr) {
		int n=arr.length;
		sort(arr,0,n-1);
	}

	private static void sort(Comparable[] arr, int l, int h) {
		if(h-l<15) {
			InsertSort.sort(arr, l, h);
			return;
		}
		/*if(h<=l) {
			return;
		}*/
		
		int partition=partiton(arr,l,h);
		sort(arr,l,partition-1);
		sort(arr,partition+1,h);
	}

	//单路排序
	private static int partiton(Comparable[] arr, int l, int h) {
		swap( arr, l , (int)(Math.random()*(h-l+1))+l );
		Comparable v=arr[l];
		int j=l;
		for (int i = l+1; i < h+1; i++) {
			if(arr[i].compareTo(v)<0) {
				j++;
				swap(arr, j, i);
			}
			
		}
		swap(arr, j, l);
		return j;
	}
	
	//双路排序
	/*private static int partiton(Comparable[] arr, int l, int h) {
		int s=new Random().nextInt(h-l)+l;
		swap(arr, l, s);
		
		Comparable v=arr[l];
		
		int i=l+1, j=h;
		
		while(true) {
			while(i<=h && arr[i].compareTo(v)<0)
				i++;
			while(j>=l+1 && arr[j].compareTo(v)>0)
				j--;
			
			if(i>j)
				break;
			 swap( arr, i, j );
	            i ++;
	            j --;
		}
		swap( arr, l, j );
		return j;
	}*/
	private static void swap(Object[] arr, int j, int i) {
		Object obj = arr[i];
		arr[i]=arr[j];
		arr[j]=obj;
		
	}
	public static void main(String[] args) {
		 int N = 1000000;
	     //  Integer[] arr = SortHealper.generateRandomArray(N, 0, 100000);
	         Integer[] arr = SortHealper.generateNearlyOrderedArray(N, 10);
	        SortHealper.printArray(arr);
	        SortHealper.testSort("sortbasic.selectcomparable.QuickSort", arr,"sort");
	        SortHealper.printArray(arr);
	        boolean sorted = SortHealper.isSorted(arr);
	        System.out.println("ïssorted:"+sorted);
	        System.out.println(o);
	        return;
	}
}
