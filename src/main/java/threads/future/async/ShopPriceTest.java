package threads.future.async;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static java.util.stream.Collectors.toList;

/**
 * @program: selfplay
 * @description: 异步调用商品价获取价格
 * @author: zx
 * @create: 2018-09-10 13:53
 **/
public class ShopPriceTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        List<Shop> shops = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Shop shop = new Shop("A----" + i);
            shops.add(shop);
        }
        long start = System.nanoTime();
        System.out.println(findPrices("myPhone27S", shops));
        long duration = (System.nanoTime() - start) / 1_000_000;
        System.out.println("Done in " + duration + " msecs");


        /*Shop shop = new Shop("balala");
        long start = System.nanoTime();
        Future<Double> my_pro = shop.getAsyncprice("My pro");
        long invocationTime = ((System.nanoTime() - start) / 1_000_000);
        System.out.println("Invocation returned after " + invocationTime
                + " msecs");
        dosomethingelse();

        System.out.println("my_pro.isDone() "+my_pro.isDone());
        System.out.println(" my_pro.isCancelled() "+my_pro.isCancelled());

        System.out.println("my_pro get:"+my_pro.get());
        System.out.println("------获取价格进行其他操作:");
        System.out.println("my_pro.isDone() "+my_pro.isDone());
        System.out.println(" my_pro.isCancelled() "+my_pro.isCancelled());*/
    }

    private static List<String> findPrices(String name, List<Shop> shops) {

        //使用CompletableFuture的方式计算每种商品在不同商店中的价格
         /*List<CompletableFuture<String>> collect = shops.parallelStream()
                .map(shop -> CompletableFuture.supplyAsync(
                        () -> String.format("%s price is %.2f",
                                shop.getName(), shop.getPrice(name))))
                .collect(toList());*/
        //等待所有异步(商品计算价格的结果)执行完成进行合并操作,使用
        //List<String> collect1 = collect.stream().map(CompletableFuture::join).collect(Collectors.toList());
        return shops.parallelStream()
                .map(shop -> String.format("%s price is %.2f",
                        shop.getName(), shop.getPrice("name")))
                .collect(toList());


    }

    private static void dosomethingelse() {
        System.out.println("在主线程中等待执行一些其他操作");

    }
}
