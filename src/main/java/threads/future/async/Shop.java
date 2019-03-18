package threads.future.async;

import lombok.Data;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

/**
 * @program: selfplay
 * @description: 描述异步调用计算价格的类
 * @author: zx
 * @create: 2018-09-10 13:45
 **/
@Data
public class Shop {

    private String name;

    public Shop(String name) {
        this.name = name;
    }

    public Future<Double> getAsyncprice(String product) {
        return CompletableFuture.supplyAsync(() -> calculatePrice(product));


      /*  CompletableFuture<Double> future = new CompletableFuture<>();
        new Thread(()->{
            double price = calculatePrice(product);
            future.complete(price);
        }).start();
        return future;*/

    }

    private Double calculatePrice(String product) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new Random().nextDouble() * product.charAt(0) + product.charAt(1);
    }

    public double getPrice(String product) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new Random().nextDouble() * product.charAt(0) + product.charAt(1);
    }
}
