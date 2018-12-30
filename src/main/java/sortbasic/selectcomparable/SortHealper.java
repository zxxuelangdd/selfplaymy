package sortbasic.selectcomparable;

import java.lang.reflect.Method;
import java.util.Random;

public class SortHealper{
	
	private SortHealper() {};
	// 生成有n个元素的随机数组,每个元素的范随机围为[rangeL, rangeR]
	
	 public static Integer[] generateRandomArray(int n, int rangeL, int rangeR) {
		 
		 assert rangeR>rangeL;
		 
		  Integer[] arr = new Integer[n];
		  
		  for (int i = 0; i < arr.length; i++) {
			 arr[i]=new Random().nextInt(rangeR-rangeL);
		}
		 return arr;
	 }
	  // 打印arr数组的所有内容
	    public static void printArray(Object[] arr) {
	    	
	    	assert arr.length>=1;
	    	for (int i = 0; i < arr.length-1; i++) {
				System.out.print(arr[i]+",");
			}
	    	System.out.print(arr[arr.length-1]);
	    	System.out.println();
	    	System.out.println("============================================");
	    }
	 // 判断arr数组是否有序
	    public static boolean isSorted(Comparable[] arr){
	    	assert arr.length>1;
	    	for (int i = 0; i < arr.length-1; i++) {
				if(arr[i].compareTo(arr[i+1])>0) {
					return false;
				}
			}
	    	return true;
	    }
	    //近乎有序的数组
	    public static Integer[] generateNearlyOrderedArray(int n, int swapTimes){

	        Integer[] arr = new Integer[n];
	        for( int i = 0 ; i < n ; i ++ )
	            arr[i] = new Integer(i);

	        for( int i = 0 ; i < swapTimes ; i ++ ){
	            int a = (int)(Math.random() * n);
	            int b = (int)(Math.random() * n);
	            int t = arr[a];
	            arr[a] = arr[b];
	            arr[b] = t;
	        }

	        return arr;
	    }
	    // 测试sortClassName所对应的排序算法排序arr数组所得到结果的正确性和算法运行时间
	    public static void testSort(String sortClassName, Comparable[] arr,String sortname){
	    	 // 通过Java的反射机制，通过排序的类名，运行排序函数
	    	
	    	try {
	    		//通过对象名称获取对象
				Class  sortClass = Class.forName(sortClassName);
				
				Method method = sortClass.getMethod(sortname,new Class[] {Comparable[].class});
				long starttime = System.currentTimeMillis();
				
				//似乎必须需得加上这个  否则wrong number of arguments
				Object[] parm=new Object[] {arr};
				
				method.invoke(null, parm);
				//boolean sorted = isSorted(arr);
			   // printArray(arr);
				if(arr.length>50000)
				 System.out.println("==========================="+arr[50000]);
				long endtime = System.currentTimeMillis();
				System.out.println(sortClass.getSimpleName()+" : "+(endtime-starttime));
				
			} catch ( Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	    }
}
