package threads.thread1.twoPhaseTermination.cyclicBarrier;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @program: selfplay
 * @description:
 * @author: zx
 * @create: 2018-09-09 08:38
 **/
public class Mytask implements Runnable {

    private static final int PHASE=5;
    private final CyclicBarrier phaseBarrier;
    private final CountDownLatch downLatch;
    private final int context;

    public Mytask(CyclicBarrier phaseBarrier, CountDownLatch downLatch, int context) {
        this.phaseBarrier = phaseBarrier;
        this.downLatch = downLatch;
        this.context = context;
    }

    @Override
    public void run() {
         try {
            for (int phase=0;phase<PHASE;phase++){
                System.out.println("begin for ----"+phase);
                doPhase(phase);
              //  System.out.println("before phaseBarrier await--------"+phase);

                //进入等到  执行线程数执行完毕之后才能继续
                //这里用来判断关联的线程执行情况 如果关联线程数执行位置到这了进行等待  等待的数量与初始化设置的数量一致的时候才能继续执行
                //一般设置执行线程数与初始化线程数相等  这样才能一段一段工作  否则没有什么意义
                //相当于这里就是一个标记点   这个点往下执行需要满足初始化该标记点设置的条件
                 phaseBarrier.await();
                 //System.out.println("after phaseBarrier await----"+phase);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
             System.out.println("执行   downLatch.countDown(); ");
            downLatch.countDown();
             try {
                 Thread.sleep(ThreadLocalRandom.current().nextInt(5000));
                 System.out.println("执行   downLatch.countDown();    sleep");
             } catch (InterruptedException e) {
                 e.printStackTrace();
             }
         }

    }

    private void doPhase(int phase) {
        String name = Thread.currentThread().getName();
       // System.out.println(name+":Mytask:begin:context="+context+",phase="+phase);
        try {
            Thread.sleep(ThreadLocalRandom.current().nextInt(3000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            System.out.println(name+":Mytask:end:context="+context+",phase="+phase);
        }
    }
}
