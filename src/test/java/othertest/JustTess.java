package othertest;


import com.google.common.collect.Lists;

import java.util.LinkedList;

/**
 * @program: selfplay
 * @description:
 * @author: zx
 * @create: 2018-08-16 14:26
 **/
public class JustTess {
    public static void main(String[] args) {


        String format = String.format("http://baike.baidu.com/search/word?word=%s%s&pic=1&sug=1&enc=utf8", "水力发电", "风力发电");
        System.out.println(format);

        LinkedList<Object> linked1 = Lists.newLinkedList();
        LinkedList<String> linked2 = Lists.newLinkedList();
        LinkedList<String> linked3 = Lists.newLinkedList();
        linked1.add("a");
        linked1.add("b");
        linked1.add("c");
        linked1.add("d");
        linked1.add("d");
        linked1.add("d");
        linked1.add("d");


        linked3.add("a");
        linked3.add("b");
        linked3.add("c");
        System.out.println("------------------" + linked1);
        linked2.addLast("c");
        linked2.addLast("d");
        linked1.addAll(linked2);
        System.out.println("------------------" + linked1);
        boolean b2 = linked1.containsAll(linked2);
        System.out.println("查看还有一个集合是不是包括另一个集合:" + b2);
        System.out.println("linked1 移除前的 长度 " + linked1.size());
        boolean b1 = linked1.removeAll(linked2);
        System.out.println("是否移除:" + b1);
        System.out.println(linked1);
        System.out.println("linked1 移除后 长度 " + linked1.size());


        boolean b = linked1.containsAll(linked2);
        System.out.println("contains all ?" + b);
        boolean contains = linked1.contains(linked2);
        System.out.println(" contain? " + contains);


        String regex = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{4,16}$";
        String value = "dfgsdf";  // 长度不够
        System.out.println(value.matches(regex));

        int a = 3;
        String[] cc = new String[5];
        cc[a] = "asdfasf";
        a++;
        a++;
        a++;
        a++;
        System.out.println(a);
        System.out.println(cc[3]);


    }
}
