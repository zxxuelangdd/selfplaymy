package recursionjdk8;



import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @program: selfplay
 * @description:尝试使用stream操作
 * @author: zx
 **/
public class MapCollectionDemo {
    public static void main(String[] args) {
        List<String> list = Stream.of("a", "b", "c", "a").collect(Collectors.toList());
        System.out.println(list);


        //map  把input的每一个元素 映射成 output的另外一个元素 和scala流式编程语言一样
        /**
         * 这里不是处理本身元素  而是将处理后的结果重新存入  不改变本身的数值
         * */
        List<String> collect = list.stream().map(String::toUpperCase).collect(Collectors.toList());
        List<String> collect1 = list.stream().map(n->n.toUpperCase()).collect(Collectors.toList());
        System.out.println(list);
        System.out.println(collect);
        System.out.println(collect1);

        List<Integer> integers = Arrays.asList(1, 2, 35, 6, 7, 8);
        List<Integer> integers1 = integers.stream().map(n -> n * n).collect(Collectors.toList());
        System.out.println("integers:"+integers);
        System.out.println("integers1:"+integers1);


        Stream<List<Integer>> inputStream = Stream.of(
                Arrays.asList(1,2),
                Arrays.asList(2, 3),
                Arrays.asList(4, 5, 6)
        );
        Stream<Integer> outputStream = inputStream.
                flatMap(childList -> childList.stream());
        List<Integer> collect2 = outputStream.collect(Collectors.toList());
        System.out.println("collect2:"+collect2);


        //关于使用 reduce
        // 字符串连接，concat = "ABCD"
        String concat = Stream.of("A", "B", "C", "D").reduce("", String::concat);
        System.out.println("concat:"+concat);

        final List<Integer> numbers = Arrays.asList(1, 2, 3, 4);
        final Integer sum = numbers.stream()
                .reduce(0, (a, b) -> a + b);
        System.out.println("sum:"+sum);
        Integer reduce = Arrays.asList(1, 2, 3, 4).stream()
                .reduce(0, (a, b) -> a + b);
        System.out.println("reduce:"+reduce);

        System.out.println("------------  flatmap");
        String[] arrayOfWords = {"Goodbye", "World"};
        List<String> stringList = Arrays.stream(arrayOfWords).collect(Collectors.toList());

        List<Stream<String>> collect4 = stringList.stream().map(a -> a.split("")).map(Arrays::stream).collect(Collectors.toList());
        System.out.println(" 这里的map 让每个数组变成一个单独的流:"+collect4);


        List<String> collect3 = stringList.stream().map(a -> a.split(""))//将每个单词转换为由其字母组成的数组
                .flatMap(Arrays::stream)//将各个生成扁平化为单个流
                .distinct()
                .collect(Collectors.toList());
        System.out.println("使用 flatmap 将各个流扁平化为单个流:"+collect3);

        System.out.println("----------------生成数对");
        List<Integer> numbers1 = Arrays.asList(1, 2, 3);
        List<Integer> numbers2 = Arrays.asList(3, 4);

        //尝试使用join
        System.out.println("====================尝试使用join 操作 数组  直接转化 看看结果");
        String collect10 = numbers1.stream().flatMap(i -> numbers2.stream().map(j -> Arrays.toString(new int[]{i, j}))).collect(Collectors.joining(","));
        System.out.println("使用join  元素中的字符串进行合并:"+collect10);


        List<String> collect5 = numbers1.stream().flatMap(i -> numbers2.stream().map(j -> Arrays.toString(new int[]{i, j}))).collect(Collectors.toList());
        System.out.println("在处理过程中将数组转换成字符串:"+collect5);
        System.out.println("使用生成数对:"+collect5.toString());



        List<int[]> collect6 = numbers1.stream().flatMap(i -> numbers2.stream().map(j -> new int[]{i, j})).collect(Collectors.toList());
        collect6.forEach(e-> System.out.println(Arrays.toString(e)));

        System.out.println("--------再增加一个条件看看数对的和能不能被某个数整除");

        //这里的filter是过滤条件  是满足这个条件的结果
        List<String> collect7 = numbers1.stream().flatMap(a -> numbers2.stream().filter(b -> (a + b) % 3 != 0).map(j -> Arrays.toString(new int[]{a, j}))).collect(Collectors.toList());
        System.out.println("查看对数对过滤后的结果:"+collect7);

        System.out.println("-----------------------归约");
        Integer reduce1 = numbers1.stream().reduce(0, Integer::sum);
        System.out.println(reduce1);
        Optional<Integer> reduce2 = numbers1.stream().reduce((a, b) -> a + b);
        Integer integer = reduce2.get();
        System.out.println("------------------"+integer);

        //IntStream和LongStream  使用 range 和 rangeclose 区别  rangeclose包含结束值
        //参数  第一个参数起始值  第二个参数 结束值
        int sum1 = IntStream.rangeClosed(1, 100).filter(a->a%2==0).sum();
        System.out.println("sum1:"+sum1);

        //看看勾股定理
        Stream<int[]> pythagoreanTriples =
                IntStream.rangeClosed(1, 100).boxed()
                        .flatMap(a ->
                                IntStream.rangeClosed(a, 100)
                                        .filter(b -> Math.sqrt(a*a + b*b) % 1 == 0)
                                        .mapToObj(b ->
                                                new int[]{a, b, (int)Math.sqrt(a * a + b * b)})
                        );
        pythagoreanTriples
                .forEach(t ->
                        System.out.println(t[0] + ", " + t[1] + ", " + t[2]));
        double sqrt = Math.sqrt(9);
        double a = Math.sqrt(0);
        System.out.println(sqrt+"-------------------"+a);

        System.out.println("-------使用一个平方构建--------");
        Stream<double[]> as = IntStream.rangeClosed(1, 100).boxed().flatMap(w -> IntStream.rangeClosed(w, 100).mapToObj(
                b -> new double[]{w, b, Math.sqrt(w * w + b * b)}
        ).filter(t -> t[2] % 1 == 0));
        List<double[]> collect8 = as.collect(Collectors.toList());
        collect8.forEach(e->{
            System.out.print(Arrays.toString(e)+",");
        });



        /**
         * 这里用limit限制流的大小,否则会一直输出
         * */
        System.out.println("-------------------生成斐波那契数列");
        Stream.iterate(new int[]{0, 1},
                t -> new int[]{t[1],t[0] + t[1]})
                .limit(10)
                .map(t -> t[0])
                .forEach(d-> System.out.print(d+","));


        System.out.println("-----------随机生成--  limit 后边跟上数字表示限制个数   --------");
        List<Double> collect9 = Stream.generate(Math::random).limit(5).collect(Collectors.toList());
        System.out.println(collect9);
    }
}
