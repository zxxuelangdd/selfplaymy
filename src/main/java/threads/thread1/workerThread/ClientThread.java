package threads.thread1.workerThread;

import java.util.Random;

/**
 * @program: selfplay
 * @description: 表示发出工作请求的线程
 * @author: zx
 * @create: 2018-09-03 20:40
 **/
public class ClientThread extends Thread {
    private final Channel channel;

    public ClientThread(String name, Channel channel) {
        super(name);
        this.channel = channel;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; true; i++) {
                Request request = new Request(getName(), i);
                channel.putRequest(request);
                Thread.sleep(new Random().nextInt(1000));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
