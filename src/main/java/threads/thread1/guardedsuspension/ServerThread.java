package threads.thread1.guardedsuspension;

import java.util.Random;

/**
 * @program: selfplay
 * @description: 接收请求的类
 * @author: zx
 * @create: 2018-08-29 20:39
 **/
public class ServerThread extends Thread {
    private final Random random;
    private final RequestQueue requestQueue;

    public ServerThread(RequestQueue requestQueue, String name) {
        super(name);
        this.random = new Random();
        this.requestQueue = requestQueue;
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            Request request = requestQueue.getRequest();
            System.out.println(Thread.currentThread().getName() + " handles " + request);

            try {
                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        RequestQueue requestQueue = new RequestQueue();
        new ClientThread(requestQueue, "Alicen").start();
        new ServerThread(requestQueue, "Bobby").start();
    }
}
