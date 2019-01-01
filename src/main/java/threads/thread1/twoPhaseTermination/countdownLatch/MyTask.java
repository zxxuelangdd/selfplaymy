package threads.thread1.twoPhaseTermination.countdownLatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @program: selfplay
 * @description: 表示工作的类
 * @author: zx
 * @create: 2018-09-09 07:51
 **/
public class MyTask extends Thread {

    private final CountDownLatch downLatch;
    private final int context;

    public MyTask(CountDownLatch downLatch, int context) {
        this.downLatch = downLatch;
        this.context = context;
    }

    @Override
    public void run() {
        doTask();
        downLatch.countDown();
    }

    private void doTask() {
        if (context > 1) {
            System.out.println("大于3 睡一会");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        String name = Thread.currentThread().getName();
        System.out.println(name + "   myTask begin context=" + context);
        try {
            Thread.sleep(ThreadLocalRandom.current().nextInt(300));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(name + "   myTask end context=" + context);
    }
}
