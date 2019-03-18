package threads.thread1.guardedsuspension;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @program: selfplay
 * @description: 用于存放请求的类
 * @author: zx
 * @create: 2018-08-29 20:24
 **/
public class RequestQueue {
    private final BlockingQueue<Request> qu = new LinkedBlockingQueue<Request>();
    private final Queue<Request> queue = new LinkedList<>();

    public Request getRequest() {
        System.out.println("-----get---------");
        /*while(queue.peek()==null){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return  queue.remove();*/
        Request request = null;
        try {
            request = qu.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return request;
    }

    public void putRequest(Request request) {
        System.out.println("====put======");
      /*  queue.offer(request);
        notifyAll();*/
        try {
            qu.put(request);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
