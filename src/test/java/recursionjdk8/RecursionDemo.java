package recursionjdk8;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

import static java.util.stream.Collectors.toList;

/**
 * @program: selfplay
 * @description: 递归相关操作
 * @author: zx
 * @create: 2018-08-07 16:29
 **/
public class RecursionDemo {
    public static void main(String[] args) {
        int i = 100;
        long a = factorialRecursion(i);
        System.out.println("递归结果:" + a);

        long l = factorialStream(i);
        System.out.println("使用stream 进行阶乘:" + l);

        List<String> words = Arrays.asList("Java 8", "Lambdas", "In", "Action");
        List<Integer> wordLengths = words.stream()
                .map(String::length)
                .collect(toList());

    }

    private static long factorialRecursion(int i) {
        return i == 1 ? 1 : i * factorialRecursion(i - 1);
    }

    private static long factorialStream(int i) {
        return LongStream.rangeClosed(1, i).reduce(1, (long a, long b) -> a * b);
    }

    private static long factor(int i) {
        return IntStream.rangeClosed(1, i).reduce(1, (int a, int b) -> a * b);
    }
}
