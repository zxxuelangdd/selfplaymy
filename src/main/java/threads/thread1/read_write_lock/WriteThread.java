package threads.thread1.read_write_lock;

import java.util.Random;

/**
 * @program: selfplay
 * @description: 表示可以写入线程的类
 * @author: zx
 * @create: 2018-09-02 17:48
 **/
public class WriteThread extends Thread {
    private final Data data;
    private final String filler;
    private int index = 0;

    public WriteThread(Data data, String filler) {
        this.data = data;
        this.filler = filler;
    }

    @Override
    public void run() {
        try {
            while (true) {
                char c = nextchar();

                data.write(c);
                Thread.sleep(new Random().nextInt(3000));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private char nextchar() {
        char c = filler.charAt(index);
        index++;
        if (index >= filler.length()) {
            index = 0;
        }
        return c;
    }
}
