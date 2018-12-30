package sortbasic.tree;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.google.common.collect.Lists;


public class Main {
	public static void main(String[] args) {
		String filename = "D:\\eclipse-jee-oxygen-2-win32-x86_64.zip\\oxyworkspace\\algorithmictest\\src\\main\\java\\sortbasic\\tree\\bible.txt";
		 List<String> list = Lists.newArrayList();
		 FileOperations.readFile(filename, list);
		// System.out.println(list);
		 BST<String, Integer> bst = new BST<String,Integer>();
		 long ss = System.currentTimeMillis();
		 List<String> list2 = Lists.newArrayList();
		 for (String string : list) {
			 list2.add(string);
		}
		 //list.stream().forEach(e->list2.add(e));
		 
		 
		 for (String string : list) {
			 Integer search = bst.search(string);
			 if(search==null) {
				 bst.insert(string, 1);
			 }else {
				 bst.insert(string, search+1);
			 }
		}
		 long ee = System.currentTimeMillis();
		 Integer search = bst.search("god");
		 System.out.println("整个词典中出现god的个数是:"+search  +  "    使用树查找用时"+(ee-ss));
		 
		
		 
		 long aa = System.currentTimeMillis();
		 list2.stream().collect(Collectors.groupingBy(e->e)).entrySet().forEach(e->{
			 if(e.getKey().equals("god"))
				 System.out.println("整个词典中出现god的个数ss是:"+e.getValue().size());
		 });;
		 long bb = System.currentTimeMillis();
		 System.out.println("整个词典中出现god的个数是:"+11  +  "    使用jdk8查找用时"+(bb-aa));
		 System.out.println();
		 
		/* long cc = System.currentTimeMillis();
		 Long long1 = list.stream().collect(Collectors.groupingBy(Function.identity(),Collectors.counting())).get("gov");
		 long dd = System.currentTimeMillis();
		 System.out.println("整个词典中adaf出现god的个数是:"+11  +  "    使用jdk8查找用时"+(dd-cc));
		 
		 */
		 
		 
		 /*if(bst.contain("god")) {
			 
		 }*/
		 
		 
		 }

}
