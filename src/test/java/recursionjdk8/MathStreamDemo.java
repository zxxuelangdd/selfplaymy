package recursionjdk8;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.partitioningBy;

/**
 * @program: selfplay
 * @description: 数学类的stream 处理 查找素数 质数
 * @author: zx
 * @create: 2018-08-09 10:48
 **/
public class MathStreamDemo {
    public static void main(String[] args) {
        Map<Boolean, List<Integer>> collect = IntStream.rangeClosed(2, 100).boxed()
                .collect(
                        partitioningBy(candidate -> isPrime(candidate)));
        System.out.println(collect);

        System.out.println((int) Math.sqrt((double) 97));
        /**
         * 求和
         * */
        OptionalInt reduce = IntStream.rangeClosed(1, 100).filter(a -> a % 2 == 0).reduce((a, b) -> (a + b));
        OptionalInt reduce1 = IntStream.rangeClosed(1, 100).filter(a -> a % 2 != 0).reduce((a, b) -> (a + b));
        int asInt = reduce.getAsInt();
        System.out.println(asInt);
        System.out.println(reduce1.getAsInt());

        System.out.println("使用内置方法求和");
        int sum = IntStream.rangeClosed(0, 100).sum();
        IntStream intStream = IntStream.rangeClosed(0, 100);
        IntSummaryStatistics intSummaryStatistics = intStream.summaryStatistics();
        System.out.println("intSummaryStatistics:" + intSummaryStatistics);
        System.out.println("使用内置方法求和 sum:" + sum);


        List<Integer> numbers = Arrays.asList(6, 7, 2, 3, 4, 5);
        List<Integer> li = numbers.stream().peek(e -> System.out.println("from stream:" + e))
                .map(e -> e + 11).peek(e -> System.out.println("after map:" + e))
                .filter(x -> x % 2 == 0)
                .peek(e -> System.out.println("after filter:" + e))
                .limit(3)
                .peek(e -> System.out.println("after limit:" + e))
                .collect(Collectors.toList());
        System.out.println("查看最终结果:" + li);
        System.out.println("-------------排序---------------");
        System.out.println("排序前:" + numbers);
        //内置了两种排序方式  其中reverseOrder 按照 降序排列      naturalOrder 按照升序排列
        // numbers.sort(Comparator.reverseOrder());
        numbers.sort(Comparator.naturalOrder());
        System.out.println("排序后" + numbers);

    }

    public static boolean isPrime(int candidate) {
        int candidateRoot = (int) Math.sqrt((double) candidate);
        return IntStream.rangeClosed(2, candidateRoot)
                .noneMatch(i -> candidate % i == 0);
    }
}
