package threads.thread1.producter_comsumer;

import java.util.Random;

/**
 * @program: selfplay
 * @description: 表示糕点师的类
 * @author: zx
 * @create: 2018-09-02 12:02
 **/
public class MakerThread extends Thread {
    private final Table table;
    private static int id=0;//蛋糕的流水号


    public MakerThread(String name,Table table) {
        super(name);
        this.table = table;
    }

    @Override
    public void run() {
        while (true){
            try {
                Thread.sleep(new Random().nextInt(1000));
                table.put("Cake No "+ nextid() +" by name:"+getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private synchronized int nextid() {
        return id++;
    }
}
