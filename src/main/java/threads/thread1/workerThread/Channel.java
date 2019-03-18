package threads.thread1.workerThread;

/**
 * @program: selfplay
 * @description: 接收工作请求, 并将工作请求交给工人的线程类
 * @author: zx
 * @create: 2018-09-03 20:41
 **/
public class Channel {

    private final int MAX_REQUEST = 100;
    private final Request[] requestQueue;
    private int tail;//下次request的位置
    private int head;//下次takeRequest的位置;
    private int count;//Request的数量
    private final WorkerThread[] threadpool;

    public Channel(int threads) {
        this.requestQueue = new Request[MAX_REQUEST];
        this.tail = 0;
        this.head = 0;
        this.count = 0;
        threadpool = new WorkerThread[threads];
        for (int i = 0; i < threadpool.length; i++) {
            threadpool[i] = new WorkerThread("Worker-" + i, this);

        }
    }

    public void startWorkers() {
        for (int i = 0; i < threadpool.length; i++) {
            threadpool[i].start();

        }
    }

    public synchronized void putRequest(Request request) {
        while (count > requestQueue.length) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        requestQueue[tail] = request;
        tail = tail++ % requestQueue.length;
        count++;
        notifyAll();
    }

    public synchronized Request takeRequest() {
        while (count <= 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Request request = requestQueue[head];
        head = head++ % requestQueue.length;
        count--;
        notifyAll();
        return request;
    }
}
