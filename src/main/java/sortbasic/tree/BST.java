package sortbasic.tree;

import java.util.Arrays;
import java.util.Random;

/**
 * @author DELL
 *
 * @param <Key>
 * @param <Value>
 * 填坑:对象比较不能当做具体类型 
 * 使用递归 对象里边的元素返回那一层一层返回
 */
public class BST <Key extends Comparable<Key>,Value>{

	//节点为私有类,外界不需要了解具体实现
	private class Node{
		private Key key;
		private Value value;
		private Node left,right;
		public Node(Key key,Value value){
			this.key=key;
			this.value=value;
			left=null;
			right=null;
		}
	}
	private Node root;
	private int count;
	
	//节点个数
	public  int size() {
		return count;
	}
	//构造函数 默认构造一颗空的二叉树
	public BST() {
		root=null;
		count=0;
	}
	
	
	//向二分搜索树中插入元素
	public void insert(Key key,Value value) {
		root=insert(root,key,value);
	}
	
	private Node insert(Node node, Key key, Value value) {
		// TODO Auto-generated method stub
		if(node==null) {
			count ++;
			return new Node(key, value);
		}
		
		if(node.key.compareTo(key)==0) {
			node.value=value;
		}else if(node.key.compareTo(key)<0) {
			node.right=insert(node.right, key, value);
		}else {
			node.left=insert(node.left, key, value);
		}
		
		return node;
	}
	
	//判断某个元素是不是在树中
	public boolean contain(Node node,Key key) {
		return iscontain(node,key);
	}
	private boolean iscontain(Node node, Key key) {
		if(node==null)
			return false;
		if(node.key==key) {
			return true;
		}else if(node.key.compareTo(key)>0) {
			return iscontain(node.left, key);
		}else {
			return iscontain(node.right, key);
		}
	}
	
	//查找某个元素的值
	public Value search(Key key) {
		 return search(root,key);
	}
	private Value search(Node node, Key key) {
		  if(node == null) {
			   return null;
		   }
		   if(node.key.compareTo(key)==0) {
				return node.value;
			}else if(node.key.compareTo(key)>0) {
				return search(node.left, key);
			}else {
				return search(node.right, key);
			}
	}
	
	//下面三个函数  preOrder 前序遍历   inOrder 中序遍历   postOrder 后续遍历
	private void preOrder(Node node) {
		if(node!=null) {
			System.out.print(node.key+",");
			preOrder(node.left);
			preOrder(node.right);
		}
	}
	private void inOrder(Node node) {
		if(node!=null) {
			preOrder(node.left);
			System.out.print(node.key+",");
			preOrder(node.right);
		}
	}
	private void postOrder(Node node) {
		if(node!=null) {
			preOrder(node.left);
			preOrder(node.right);
			System.out.print(node.key+",");
		}
	}
	
	
	private static void swap(Object[] arr, int j, int i) {
		Object obj = arr[i];
		arr[i]=arr[j];
		arr[j]=obj;
		
	}
	
	public static void main(String[] args) {
		
		 int N = 10;

	        // 创建一个数组，包含[0...N)的所有元素
	        Integer[] arr = new Integer[N];
	        for(int i = 0 ; i < N ; i ++)
	            arr[i] = new Integer(i);
	        Arrays.asList(arr).stream().forEach(e->{
	        	System.out.print(e+",");
	        });
	        System.out.println();
	        // 打乱数组顺序
	      /*   for(int i = 0 ; i < N ; i ++){
	            int pos = (int) (Math.random() * (i+1));
	            Integer t = arr[pos];
	            arr[pos] = arr[i];
	            arr[i] = arr[pos];
	        } 
	     	*/
	     		
	         //查看中位数
	        /* int midAttr = getMidAttr(arr);
	         swap(arr, 0, midAttr);*/
	        
	         Arrays.asList(arr).stream().forEach(e->{
		        	System.out.print(e+",");
		        });
		        System.out.println("上面一行作用  第一个表示中间数");
		        
		       
		        
		        
		        
	        BST<Integer, String> bst = new BST<Integer,String>();
	        if(bst.root!=null)
	        System.out.println("查看是否优质:"+bst.root.key);
	        for (int i = 0; i < arr.length; i++) {
				bst.insert(new Integer(arr[i]), arr[i].toString());
			}
	        
	        int count2 = bst.count;
	        System.out.println("数量:"+count2);
	        System.out.println("查看是否优质:"+bst.root.key);
	        
	        
	       /* for (int i = 0; i < 2*N; i++) {
	        	String value = bst.search(i);
	        	System.out.println("查找结果; key:"+i+"  value:"+value);
			}*/
	        System.out.println("开始遍历并输出结果,前序中序后序");
	        bst.preOrder(bst.root);
	        System.out.println();
	        bst.inOrder(bst.root);
	        System.out.println();
	        bst.postOrder(bst.root);
	        System.out.println();
	}
}
