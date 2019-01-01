package recursionjdk8;

import org.json.JSONObject;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

/**
 * @program: selfplay
 * @description: 用流收集数据
 * @author: zx
 **/
public class CollectionByStream {
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


        //创建一个Comparator来根据所含热量对菜肴进行比较，并把它传递给
        //Collectors.maxBy：
        Comparator<Dish> dishComparator = Comparator.comparingInt(Dish::getCalories);

        //Optional 一个容器  可以包含也可以不包含值 java8 引入的房子返回空值的exception
        Optional<Dish> dish = menu.stream().collect(minBy(dishComparator));
        System.out.println("====" + dish);

        System.out.println("求某一项总和");
        Integer collect = menu.stream().collect(summingInt(Dish::getCalories));
        System.out.println("菜单中所哟普热量总和:" + collect);
        int collect1 = menu.stream().collect(averagingInt(e -> e.getCalories())).intValue();
        System.out.println("所有菜品中热量的平均数:" + collect1);

        //使用 summarizingInt 工厂方法可以一次性统计多个  直接返回 count sum min max average
        IntSummaryStatistics collect2 = menu.stream().collect(summarizingInt(Dish::getCalories));
        System.out.println("查看总和统计结果数目:" + collect2);

        //尝试使用join对字符串进行合并
        String collect3 = menu.stream().map(e -> e.toString()).collect(joining("============"));
        System.out.println("字符串合并结果:" + collect3);

        System.out.println("直接合并的方式:");

        Stream<Integer> integerStream = menu.stream().map(e -> e.getCalories());
        //long count = integerStream.count();
        //System.out.println("=====================:"+count);
        //reduce(Integer::sum)返回的不是int
        //而是Optional<Integer>，以便在空流的情况下安全地执行归约操作
        Optional<Integer> reduce = integerStream.reduce(Integer::sum);
        System.out.println("==============reduce:" + reduce);
        Long collect4 = menu.stream().map(e -> e.getCalories()).collect(Collectors.counting());
        System.out.println("需要查看上边的源码 :" + collect4);

        System.out.println("上面的等同于下边的操作");
        Integer collect5 = menu.stream().collect(reducing(0, Dish::getCalories, Integer::sum));
        System.out.println("进行总和统计:" + collect5);

        Map<Dish.Type, List<Dish>> collect6 = menu.stream().collect(groupingBy(Dish::getType));

        collect6.entrySet().forEach(e -> {
            System.out.println(e.getKey() + "===========" + e.getValue());
        });
        System.out.println("根据包含字段进行分组:" + collect6.toString());

        //按照热量高低进行分类
        Map<CaloricLevel, List<Dish>> collect7 = menu.stream().collect(groupingBy(as -> {
            if (as.getCalories() < 401) return CaloricLevel.DIET;
            else if (as.getCalories() > 700) return CaloricLevel.FAT;
            else return CaloricLevel.NORMAL;
        }));
        System.out.println("自定义分组策略:" + collect7);


        System.out.println("看看二级分组什么鬼");

        //根据结果分析 二级分组 是在一级分组的结果上再次进行分组  将一级分组里边的value 载转换成一个map  key为组别 value 为分组结果
        Map<Dish.Type, Map<CaloricLevel, List<Dish>>> collect8 = menu.stream().collect(groupingBy(Dish::getType, groupingBy(asss -> {
            if (asss.getCalories() < 400) return CaloricLevel.DIET;
            else if (asss.getCalories() > 700) return CaloricLevel.FAT;
            else return CaloricLevel.NORMAL;
        })));
        System.out.println("查看二级分组结果:" + collect8);

        //将对象转化成json 查看结果
        JSONObject jsonObject = new JSONObject(collect8);

        OutPutJson.output(jsonObject);
        System.out.println("=======================================================");
        OutPutJson.output(new JSONObject(collect6));
        String s = jsonObject.toString();

        System.out.println("查看二级分组转化成json的结果:" + s);


        Map<Dish.Type, Optional<Dish>> collect9 = menu.stream().collect(groupingBy(Dish::getType, maxBy(Comparator.comparingInt(Dish::getCalories))));
        System.out.println("======================================");
        System.out.println("collect9" + collect9);

        //统计分类个数
        System.out.println("统计分类个数");
        Map<Dish.Type, Long> collect10 = menu.stream().collect(groupingBy(Dish::getType, counting()));
        System.out.println("统计分类个数:" + collect10);

        //查找每个子组中热量最高的 Dish
        System.out.println("开始查找每个子组中热量最高的Dish  使用工厂的方法");

        Map<Dish.Type, Dish> collect11 = menu.stream().collect(groupingBy(Dish::getType, collectingAndThen(maxBy(Comparator.comparingInt(Dish::getCalories)), Optional::get)));
        System.out.println("看看这个输出和collect9有什么不一样  res 类型不一样 一个Dish蕾西你个 一个包装了Dish类型的Optional类型");
        System.out.println("collect9:" + collect9);
        System.out.println("collect11:" + collect11);
        System.out.println("next 能否将collect9中的Optional直接输出为Dish  没搞出来 需要借助collectingAndThen() 中第二个参数作为类型转换方式进行提出转换结果");
        //menu.stream().collect(groupingBy(Dish::getType, maxBy(Comparator.comparingInt(Dish::getCalories))));
        Map<Dish.Type, String> collect12 = menu.stream().collect(groupingBy(Dish::getType, collectingAndThen(maxBy(Comparator.comparingInt(Dish::getCalories)), e -> e.get().getName())));
        System.out.println("继续转换  筛选出最大最小类型对象的某一个字段:" + collect12);


        //分组后求和
        Map<Dish.Type, Integer> collect13 = menu.stream().collect(groupingBy(Dish::getType, summingInt(Dish::getCalories)));
        System.out.println("分组后对分组类别中的某一项求和操作:" + collect13);
        Map<Dish.Type, IntSummaryStatistics> collect14 = menu.stream().collect(groupingBy(Dish::getType, summarizingInt(Dish::getCalories)));
        System.out.println("对分组后的结果中某一项进行数值操作:" + collect14);

        System.out.println(" partitioningBy  true 和 false");
        Map<Boolean, String> collect15 = menu.stream().collect(partitioningBy(Dish::isVegetarian, collectingAndThen(maxBy(Comparator.comparingInt(Dish::getCalories)), e -> e.get().getName())));
        System.out.println("素食和主食中最大热量食物名称:" + collect15);

    }

    public enum CaloricLevel {DIET, NORMAL, FAT}
}
