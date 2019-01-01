package threads.thread1.workerThread;

/**
 * @program: selfplay
 * @description: 表示工人线程的类
 * @author: zx
 * @create: 2018-09-03 20:42
 **/
public class WorkerThread extends Thread {
    private final Channel channel;


    public WorkerThread(String name, Channel channel) {
        super(name);
        this.channel = channel;
    }


    @Override
    public void run() {
        while (true) {
            Request request = channel.takeRequest();
            request.excute();
        }
    }
}
