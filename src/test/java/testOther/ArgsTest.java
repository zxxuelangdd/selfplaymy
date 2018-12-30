package testOther;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: selfplay
 * @description:
 * @author: zx
 * @create: 2018-11-04 11:09
 **/
public class ArgsTest {
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        String ass = map.get("ass");
        System.out.println(ass);
        System.out.println(args);
        System.out.println(args.length);
        List<String> strings = Arrays.asList(args);
        System.out.println(strings);
        System.out.println(strings.size());
        boolean sadfasdf = strings.add("sadfasdf");
        System.out.println(sadfasdf);
        System.out.println(strings);
    }
}
