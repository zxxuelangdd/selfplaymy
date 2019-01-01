package recursionjdk8;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @program: selfplay
 * @description: 测试join方式
 * @author: zx
 * @create: 2019-01-01 15:04
 **/
public class JoinTest {
    public static void main(String[] args) {
        List<Dish> menu = Arrays.asList(
                Dish.PersonFactory("pork", false, 800, Dish.Type.MEAT),
                Dish.PersonFactory("beef", false, 700, Dish.Type.MEAT),
                Dish.PersonFactory("chicken", false, 400, Dish.Type.MEAT),
                Dish.PersonFactory("french fries", true, 530, Dish.Type.OTHER),
                Dish.PersonFactory("rice", true, 350, Dish.Type.OTHER),
                Dish.PersonFactory("season fruit", true, 120, Dish.Type.OTHER),
                Dish.PersonFactory("pizza", true, 550, Dish.Type.OTHER),
                Dish.PersonFactory("prawns", false, 300, Dish.Type.FISH),
                Dish.PersonFactory("salmon", false, 450, Dish.Type.FISH));
        List<Integer> collect = menu.stream().map(Dish::getCalories).collect(Collectors.toList());
        String collect1 = menu.stream().map(Dish::getCalories).map(e -> e.toString()).collect(Collectors.joining("----"));
        System.out.println(collect1);
        String reduce = menu.stream().map(Dish::getCalories).map(e -> e.toString()).reduce("===", String::concat);
        System.out.println(reduce);
    }
}
