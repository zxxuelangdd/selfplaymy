package threads.thread1.activeObject;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @program: selfplay
 * @description: 实现ActiveObject接口的类
 * @author: zx
 * @create: 2018-09-10 19:57
 **/
public class ActiveObjectImpl implements ActiveObject {
    private final ExecutorService service = Executors.newSingleThreadExecutor();

    public Future<String> makeString(final int count, final char c) {
        class MakeStringRequest implements Callable<String> {
            @Override
            public String call() {
                char[] buffer = new char[count];
                for (int i = 0; i < count; i++) {
                    buffer[i] = c;
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
                return new String(buffer);
            }
        }
        return service.submit(new MakeStringRequest());
    }

    public void displayString(final String string) {
        class DisplayStringRequest implements Runnable {
            public void run() {
                try {
                    System.out.println("displayString:" + string);
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        service.execute(new DisplayStringRequest());

    }

    @Override
    public void shutdown() {
        System.out.println("******shut down*******");
        service.shutdown();
    }
}
