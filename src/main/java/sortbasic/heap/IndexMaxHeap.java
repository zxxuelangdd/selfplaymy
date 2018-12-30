package sortbasic.heap;

import sortbasic.selectcomparable.SortHealper;

/**
 * @author DELL
 *
 * 索引堆
 * 主要用于动态数据的排序   性能比归并排序和快速排序慢 
 */
public class IndexMaxHeap<Item extends Comparable>{
	
	protected Item[] data;
	protected int count;
	protected int capacity;
	protected int[] indexs;
	protected int[] reverse;
	
	//capacity 容量  初始化时给堆一个容量
	public IndexMaxHeap(int capacity) {
		reverse=new int[capacity+1];
		for (int i = 0; i <= capacity; i++) {
			reverse[i]=0;
		}
		indexs=new int[capacity+1];
		data = (Item[]) new Comparable[capacity+1];
		count=0;
		//这里的capacity代表容量
		this.capacity=capacity;
	}
	
/*	
	 public IndexMaxHeap(Comparable[] arr) {
		int n = arr.length;
		data = (Item[]) new Comparable[n+1];
		capacity=n;
		
		for (int i = 0; i < arr.length; i++) {
			data[i+1]=(Item) arr[i];
		}
		count=n;
		for (int i = n/2; i >0; i--) {
		    shiftDown(i);
		}
	}*/
 
	
	//返回堆中个数
	public int size() {
		return count;
	}
	
	//判断堆是否为空
	public boolean isEmpty() {
		return count==0;
	}
	
	public Item getMaxItem() {
		return data[indexs[1]];
	}
	
	//移除堆中最大的元素 extract 取出
	public int extractMaxIndex() {
		// 取出最大的元素然后进行shiftDown 操作  将最后一个元素赋值给第一个元素位置  然后改变顺序
		assert count>1;
		int res=indexs[1];
		reverse[count]=0;
		swap(1, count);
		count--;
		shiftDown(1);
		return res;
	}
	
	public void change(int i,Item item) {
		
		data[indexs[reverse[i]]]=item;
		shifUp(i);
		shiftDown(i);
		/*for (int j = 0; j < indexs.length; j++) {
			if(i==j) {
				shifUp(j);
				shiftDown(j);
				break;
			}*/
		}
		
	
	//对该父节点进行操作 让以该节点为父的节点符合堆 的属性
	private void shiftDown(int k) {
		while(2*(k+1) <= count){
			int j=2*k;
			
			if(j+1 <=count && data[indexs[j+1]].compareTo(data[indexs[j]])>0) {
				j++;
			}
			if(data[indexs[j]].compareTo(data[indexs[k]])>0)
				swap(j,  k);
			k=j;
		}
	}

	//向堆中添加一个元素  
	public void insert(int i,Item item) {
		
		//插入一个元素之前应该判该位置是有有元素   如果有的话应该转成change操作
		if(contain(i)) {
			
		}else {
		//这里的capacity代表
		assert i+1<=capacity;
		assert count+1<=capacity;
		data[i+1]=item;
		count++;
		indexs[count]=i;
		reverse[i]=count;
		shifUp(count);
		}
	}
	
	private boolean contain(int i) {
		assert i+1<=capacity;
		return reverse[i+1] !=0;
	}

	private void shifUp(int k) {
		while(k>1 && data[indexs[k/2]].compareTo(data[indexs[k]]) < 0) {
				//交换并重新赋值
				swap( k , k/2);
				k/=2;
		}
		
	}
	private void swap(int j, int i) {
		reverse[indexs[i]]=i;
		reverse[indexs[j]]=j;
		
		int obj = indexs[i];
		indexs[i]=indexs[j];
		indexs[j]=obj;
		
	}
	public static void main(String[] args) {
		
		int M =100;  //表示堆中最大元素个数
		int N=50;    //表示堆中元素个数
		Integer[] arr = SortHealper.generateRandomArray(100, 0, 100);

		
		/*IndexMaxHeap<Integer> heap = new IndexMaxHeap<Integer>(arr);
		for (int i = 1; i <heap.data.length ; i++) {
			System.out.println();
		}
		*/
		
	/*	MaxHeap<Integer> maxHeap = new MaxHeap<Integer>(M);
		for (int i = 0; i < N; i++) {
			maxHeap.insert(new Integer((int)(Math.random()*M)));
		}
		
		System.out.println(maxHeap.count);
		maxHeap.treePrint();
		Integer extractMax = maxHeap.extractMax();
		System.out.println("extractMax:===================="+extractMax);
		maxHeap.treePrint();*/
	}

	
	
	  // 以树状打印整个堆结构
    public void treePrint(){

        if( size() >= 100 ){
            System.out.println("This print function can only work for less than 100 integer");
            return;
        }

        System.out.println("The max heap size is: " + size());
        System.out.println("Data in the max heap: ");
        for( int i = 1 ; i <= size() ; i ++ ){
            // 我们的print函数要求堆中的所有整数在[0, 100)的范围内
            assert (Integer)data[i] >= 0 && (Integer)data[i] < 100;
            System.out.print(data[i] + " ");
        }
        System.out.println();
        System.out.println();

        int n = size();
        int maxLevel = 0;
        int numberPerLevel = 1;
        while( n > 0 ){
            maxLevel += 1;
            n -= numberPerLevel;
            numberPerLevel *= 2;
        }

        int maxLevelNumber = (int)Math.pow(2, maxLevel-1);
        int curTreeMaxLevelNumber = maxLevelNumber;
        int index = 1;
        for( int level = 0 ; level < maxLevel ; level ++ ){

            String line1 = new String(new char[maxLevelNumber*3-1]).replace('\0', ' ');

            int curLevelNumber = Math.min(count-(int)Math.pow(2,level)+1,(int)Math.pow(2,level));
            boolean isLeft = true;
            for( int indexCurLevel = 0 ; indexCurLevel < curLevelNumber ; index ++ , indexCurLevel ++ ){
                line1 = putNumberInLine( (Integer)data[index] , line1 , indexCurLevel , curTreeMaxLevelNumber*3-1 , isLeft );
                isLeft = !isLeft;
            }
            System.out.println(line1);

            if( level == maxLevel - 1 )
                break;

            String line2 = new String(new char[maxLevelNumber*3-1]).replace('\0', ' ');
            for( int indexCurLevel = 0 ; indexCurLevel < curLevelNumber ; indexCurLevel ++ )
                line2 = putBranchInLine( line2 , indexCurLevel , curTreeMaxLevelNumber*3-1 );
            System.out.println(line2);

            curTreeMaxLevelNumber /= 2;
        }
    }

    private String putBranchInLine( String line, int indexCurLevel, int curTreeWidth){

        int subTreeWidth = (curTreeWidth - 1) / 2;
        int subSubTreeWidth = (subTreeWidth - 1) / 2;
        int offsetLeft = indexCurLevel * (curTreeWidth+1) + subSubTreeWidth;
        assert offsetLeft + 1 < line.length();
        int offsetRight = indexCurLevel * (curTreeWidth+1) + subTreeWidth + 1 + subSubTreeWidth;
        assert offsetRight < line.length();

        line = line.substring(0, offsetLeft+1) + "/" + line.substring(offsetLeft+2);
        line = line.substring(0, offsetRight) + "\\" + line.substring(offsetRight+1);

        return line;
    }
    private String putNumberInLine( Integer num, String line, int indexCurLevel, int curTreeWidth, boolean isLeft){

        int subTreeWidth = (curTreeWidth - 1) / 2;
        int offset = indexCurLevel * (curTreeWidth+1) + subTreeWidth;
        assert offset + 1 < line.length();
        if( num >= 10 )
            line = line.substring(0, offset+0) + num.toString()
                    + line.substring(offset+2);
        else{
            if( isLeft)
                line = line.substring(0, offset+0) + num.toString()
                        + line.substring(offset+1);
            else
                line = line.substring(0, offset+1) + num.toString()
                        + line.substring(offset+2);
        }
        return line;
    }
}
