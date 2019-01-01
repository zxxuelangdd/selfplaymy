package threads.thread1.twoPhaseTermination.countdownLatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @program: selfplay
 * @description: 使用countdownLatch包下的方法, 等待指定次数的某种操作的发生
 * 该测试中描述  可以指定多少个线程执行完成之后的操作,仅仅是线程完成数
 * @author: zx
 * @create: 2018-09-09 07:52
 **/
public class Main {
    private static final int TASKS = 10;

    public static void main(String[] args) {
        System.out.println("BEGIN");
        ExecutorService service = Executors.newFixedThreadPool(20);
        CountDownLatch countDownLatch = new CountDownLatch(11);

        new Thread() {
            public void run() {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("这里测试所谓的其他线程");
            }
        }.start();

        try {
            for (int i = 0; i < TASKS; i++) {
                service.execute(new MyTask(countDownLatch, i));
            }
            System.out.println("await");

            //等待加入countDownLatch的执行 要么线程全部执行完毕,要么指定线程数执行完毕
            countDownLatch.await();
            System.out.println("当前线程与其他线程之间的关系");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            service.shutdown();
            System.out.println("END");
        }
    }
}
