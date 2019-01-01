package threads.thread1.balking;

import java.util.Random;

/**
 * @program: selfplay
 * @description: 修改并保存数据的类
 * @author: zx
 * @create: 2018-09-02 10:16
 **/
public class ChangeThread extends Thread {
    private final Data data;
    private final Random random = new Random();


    public ChangeThread(String name, Data data) {
        super(name);
        this.data = data;
    }

    @Override
    public void run() {
        for (int i = 0; true; i++) {
            data.change("No." + i);
            try {
                // Thread.sleep(ThreadLocalRandom.current().nextInt(1000 ));
//                Thread.sleep(random.nextInt(1000));
                // data.save();
                Thread.sleep(1000);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
