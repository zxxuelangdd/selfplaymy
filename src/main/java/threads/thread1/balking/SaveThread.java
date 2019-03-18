package threads.thread1.balking;

/**
 * @program: selfplay
 * @description: 定期保存数据的类
 * @author: zx
 * @create: 2018-09-02 10:15
 **/
public class SaveThread extends Thread {
    private final Data data;


    public SaveThread(String name, Data data) {
        super(name);
        this.data = data;
    }

    @Override
    public void run() {
        while (true) {
            try {
                data.save();
                Thread.sleep(3000);
            } catch (Exception e) {
                e.printStackTrace();

            }
        }

    }
}
