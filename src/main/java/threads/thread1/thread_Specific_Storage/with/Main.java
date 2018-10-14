package threads.thread1.thread_Specific_Storage.with;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @program: selfplay
 * @description: 测试程序行为的类   主要将 ThreadLocal 作为容器使用  使用ThreadLocal装载Log日志 仅仅作为当前线程
 * 一个线程一个容器,这个线程是特定的
 * @author: zx
 * @create: 2018-09-09 15:33
 **/
public class Main {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);


        for (int i = 0; i < 5; i++) {
            System.out.println("--------------------------"+i);
            ClientThread alice = new ClientThread("Alice");
            ClientThread bobby = new ClientThread("Bobby");
            ClientThread chirs = new ClientThread("Chirs");

            executorService.execute(alice);
            executorService.execute(bobby);

            executorService.execute(chirs);
        }
        Thread.sleep(5000);
        boolean terminated = executorService.isTerminated();
        System.out.println("--------------------------"+terminated);
    }
}
