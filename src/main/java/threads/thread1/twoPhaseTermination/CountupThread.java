package threads.thread1.twoPhaseTermination;

/**
 * @program: selfplay
 * @description: 表示进行计数的线程类
 * @author: zx
 * @create: 2018-09-08 13:05
 **/
public class CountupThread extends Thread {
    //计数值
    private long counter=0;

    //发出终止请求后变为true
    private volatile boolean shutdownRequest=false;

    //终止请求
    public void shutdownRequest(){
        shutdownRequest=true;
        //确保线程在sleep和wait的时候也会被终止
        System.out.println("调用现成的interrupt方法");
        interrupt();
    }

    //检查是否发生了终止请求
    public boolean isShutdownRequest() {
        return shutdownRequest;
    }

    //线程体

    @Override
    public final void run() {
        try {
            while (!isShutdownRequest()){
                dowork();
            }
            Thread.sleep(1000);
        } catch (Exception e) {

        } finally {
            doshutdown();
        }
    }

    private void doshutdown() {
        System.out.println("doshutdown:countter="+counter);
    }

    private void dowork() throws InterruptedException {
        counter++;
        System.out.println("dowork:counter="+counter);
        Thread.sleep(500);
    }
}
