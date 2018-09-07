package threads.thread1.workerThread;

/**
 * @program: selfplay
 * @description: 工作没来就等着, 工作来了就执行
 * @author: zx
 * @create: 2018-09-03 20:32
 **/
public class Main {
    public static void main(String[] args) {
        Channel channel = new Channel(5);
        channel.startWorkers();//工人线程个数
        new ClientThread("alice",channel).start();
        new ClientThread("bobby",channel).start();
        new ClientThread("chris",channel).start();
    }
}
