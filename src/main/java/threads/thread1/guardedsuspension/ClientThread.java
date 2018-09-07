package threads.thread1.guardedsuspension;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

/**
 * @program: selfplay
 * @description: 发送请求的类
 * @author: zx
 * @create: 2018-08-29 20:29
 **/
public class ClientThread extends Thread{
    private final Random random;

    private final RequestQueue requestequeue;
    //private final BlockingQueue blockingQueue;

    public ClientThread(RequestQueue requestequeue, String name) {
        super(name);
        this.requestequeue = requestequeue;
        this.random = new Random();

    }
    @Override
    public void run() {
        for (int i=0;i<10000;i++){
            Request request=new Request("NO."+i);
            System.out.println(Thread.currentThread().getName()+" put request:"+request);
            requestequeue.putRequest(request);
            try {
                int i1 = random.nextInt(1000);
                System.out.println("----client----sleep----"+i1);
                Thread.sleep(i1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
