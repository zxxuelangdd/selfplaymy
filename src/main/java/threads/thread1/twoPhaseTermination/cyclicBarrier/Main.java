package threads.thread1.twoPhaseTermination.cyclicBarrier;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @program: selfplay
 * @description:这里描述指定线程数完成之后的操作和指定某个位置线程数达设定目标之后的操作
 * @author: zx
 * @create: 2018-09-09 08:37
 **/
public class Main {
    private static final int THREADS = 3;

    public static void main(String[] args) {
        System.out.println("begin");
        ExecutorService service = Executors.newFixedThreadPool(THREADS);
        Runnable barrier_action = new Runnable() {
            public void run() {
                System.out.println("--Barrier action--");
            }
        };

        //用于线程步调一致   这里的参数 parties必须大于 实际执行的线程  否则会一直处于等待状态
        //参数一般和执行线程数相同  表示一个阶段执行完毕之后执行下一个阶段
        CyclicBarrier cyclicBarrier = new CyclicBarrier(THREADS, barrier_action);
        //用于确认工作是否结束  反馈继续执行的条件  有指定参数个反馈的时候继续
        CountDownLatch countDownLatch = new CountDownLatch(2);
        try {
            for (int i = 0; i < THREADS; i++) {
                Mytask mytask = new Mytask(cyclicBarrier, countDownLatch, i);
                service.execute(mytask);
                System.out.println("---------------------" + i);
            }
            System.out.println("await");
            //未达到指定反馈条件时候等待  满足反馈条件之后继续执行
            countDownLatch.await();
            System.out.println("await   after");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            service.shutdown();
            System.out.println("END");
        }
    }
}
