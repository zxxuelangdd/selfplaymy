package sortbasic.selectcomparable;

import java.util.Random;

public class QuickSort3 {
	
	private QuickSort3() {
	}
	private static int o=0;

	public static void sort(Comparable[] arr) {
		int n=arr.length;
		sort(arr,0,n-1);
	}

	private static void sort(Comparable[] arr, int l, int r) {


        // 对于小规模数组, 使用插入排序
        if( r - l <= 15 ){
        	InsertSort.sort(arr, l, r);
            return;
        }
	  swap( arr, l, (int)(Math.random()*(r-l+1)) + l );

        // 随机在arr[l...r]的范围中, 选择一个数值作为标定点pivot

        Comparable v = arr[l];

        int lt = l;     // arr[l+1...lt] < v
        int gt = r + 1; // arr[gt...r] > v
        int i = l+1;    // arr[lt+1...i) == v
        while( i < gt ){
            if( arr[i].compareTo(v) < 0 ){
                swap( arr, i, lt+1);
                i ++;
                lt ++;
            }
            else if( arr[i].compareTo(v) > 0 ){
                swap( arr, i, gt-1);
                gt --;
            }
            else{ // arr[i] == v
                i ++;
            }
        }

        swap( arr, l, lt );

        sort(arr, l, lt-1);
        sort(arr, gt, r);
    
		
	}

	private static void sortm(Comparable[] arr, int l, int h) {
		
		
		o++;
		if(h-l<10) {
			InsertSort.sort(arr, l, h);
			return;
		}
		
	/*	int s=new Random().nextInt(h-l)+l;
		swap(arr, l, s);*/
		Comparable v=arr[l];
		
		//定义三个变量 分表标志三个位置
		int ll=l;//[l+1,ll] 维护这个区间
		int hl=h+1;//[hl,h]  维护这个区间
		int i=l+1;//[ll+1,i] 维护这个区间
		while (i<hl) {
			if(arr[i].compareTo(v)>0) {
				swap(arr, i, hl-1);
				hl--;
			}else if(arr[i].compareTo(v)<0) {
				swap(arr, i, ll+1);
				i++;ll++;
			}else {
				i++;
			}
		}
		swap(arr, ll, l);
		sort(arr, l, ll-1);
		sort(arr, hl, h);
		
		
		
	}

	private static void swap(Object[] arr, int j, int i) {
		Object obj = arr[i];
		arr[i]=arr[j];
		arr[j]=obj;
		
	}
	public static void main(String[] args) {
		 int N = 1000000;
       Integer[] arr = SortHealper.generateRandomArray(N, 0, 1000000);
		 //  Integer[] arr = SortHealper.generateRandomArray(N, 0, 100);
 //   Integer[] arr = SortHealper.generateNearlyOrderedArray(N, 100);
	       // SortHealper.printArray(arr);
	        SortHealper.testSort("sortbasic.selectcomparable.QuickSort3", arr,"sort");
	      /*  SortHealper.printArray(arr);
	        boolean sorted = SortHealper.isSorted(arr);
	        System.out.println("ïssorted:"+sorted);
	        System.out.println(o);
	        return;*/
	}
}
