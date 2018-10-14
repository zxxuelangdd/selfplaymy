package threads.thread1.twoPhaseTermination;

/**
 * @program: selfplay
 * @description: 收拾完房间再睡觉, 测试程序行为的类
 * @author: zx
 * @create: 2018-09-08 13:14
 **/
public class Main {
    public static void main(String[] args) {
        System.out.println("main begin");
        try {
            CountupThread t = new CountupThread();
            t.start();

            Thread.sleep(10000);

            //线程的终止请求
            System.out.println("Main shutdownRequest");
            t.shutdownRequest();
            System.out.println("main:join");
            //等待线程终止
            //t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("main : end");
    }
}