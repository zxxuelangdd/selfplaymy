package sortbasic.heap;

public class Heapsort {
	
	private Heapsort() {};
	
	public static void  sort(Comparable[] arr) {

		int n = arr.length;
		//对每一个父元素进行shiftDown操作
		for (int i =(n-2)/2; i >=0 ; i--){
			shiftDown(arr,i,n);
		}
		
		for (int i = n-1; i >0; i--) {
			swap(arr,0,i);
			shiftDown(arr,0,i);
		}

   }
	
	private static void swap(Object[] arr, int j, int i) {
		Object obj = arr[i];
		arr[i]=arr[j];
		arr[j]=obj;
		
	}

	private static void swap(Comparable[] arr, int i, int j) {
		Comparable obj = arr[i];
		arr[i]=arr[j];
		arr[j]=obj;
		
	}

	private static void shiftDown(Comparable[] arr, int k, int n) {
		
		while((k*2+1)<n) {
			//开始操作 比较两个元素的子节点
			int j=2*k+1;
			if(j+1<=n-1 && arr[j+1].compareTo(arr[j])>0) {
				j++;
			}
			if(arr[k].compareTo(arr[j])<0) {
				swap(arr, k, j);
				k=j;
			}else {
				break;
			}
			
		}
		
	}

}