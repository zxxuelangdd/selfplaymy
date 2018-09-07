package threads.ThreadPooldemo;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @program: selfplay
 * @description: 执行
 * @author: zx
 * @create: 2018-08-28 14:26
 **/
public class Main {
    public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {
        CommandOrder commandPhone = new CommandOrder("手机");
        CommandOrder command = new CommandOrder("电视");
        //阻塞方式执行
        String execute = commandPhone.execute();
        System.out.println("main "+execute);

        //异步非阻塞方式
        Future<String> queue = command.queue();
        String value = queue.get(200, TimeUnit.MILLISECONDS);
        System.out.println("main:"+value);
        CommandUser commandUser = new CommandUser("张网格");
        String name = commandUser.execute();
        System.out.println("main:"+name);

    }
}
