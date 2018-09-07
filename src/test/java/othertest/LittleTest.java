package othertest;


import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @program: selfplay
 * @description: 别的测试
 * @author: zx
 * @create: 2018-08-10 16:39
 **/
public class LittleTest {
    public static void main(String[] args) {
        String s="select from asdfa from dfsdfg ";
        int sdf = s.compareTo("sdf");
        Integer l=1;
        int i = l.compareTo(2);
        System.out.println(i);
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        numbers.replaceAll(a->a*a);

        System.out.println(numbers);
        ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();
        for (int j = 0; j <100; j++) {
           map.put(String.valueOf(j),j);
        }
        Optional<Integer> maxValue =
                Optional.of(map.reduceValues(1, Integer::max));
        System.out.println("maxValue    "+maxValue.get());

    }
}
