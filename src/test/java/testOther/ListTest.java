package testOther;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: selfplay
 * @description:
 * @author: zx
 * @create: 2019-05-04 15:15
 **/
public class ListTest {
    public static void main(String[] args) {
        List<String> strings = new ArrayList<>(2);
        strings.add("a");
        strings.add("b");
        strings.add("c");
        strings.add("d");
        strings.add("e");
        System.out.println(strings);
    }
}
