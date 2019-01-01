package recursionjdk8;

import java.util.Arrays;
import java.util.List;

/**
 * @program: selfplay
 * @description: 将一个List按个数分成多个
 * @author: zx
 * @create: 2018-09-15 21:56
 **/
public class ListSplitMany {
    public static void main(String[] args) {

        Dish2 a = new Dish2("a");
        Dish2 b = new Dish2("a");
        List<Dish2> menu = Arrays.asList(a, b);
        System.out.println(menu);

        System.out.println(menu);
        menu.stream().forEach(e -> {
            e.setName("sdafsdf");
        });
        System.out.println(menu);

    }
}
