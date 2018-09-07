package recursionjdk8;

import com.google.common.collect.ImmutableList;

import java.net.SocketTimeoutException;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.*;


/**
 * @program: selfplay
 * @description: 文本统计
 * @author: zx
 *
 * 如果是处理字符串类型 使用 Function.identify()表示本身  如果是个对象  使用 Dish::getType 表示对象中的某一类
 **/
public class WordCountDemo {

    public static void main(String[] args) {

        List<String> list = ImmutableList.of("中国", "a", "中国", "s", "中国", "d", "d", "日本", "h", "台湾", "k", "l", "s");

        Map<String, Long> collect = list.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        collect.keySet().forEach(e->{
            System.out.println(e+"  "+collect.get(e));
        });
        System.out.println(collect);
        System.out.println("===========================");
        Map<String, Long> collect1 = list.stream().collect(groupingBy(Function.identity(), counting()));
        System.out.println(collect1);

    }
}
