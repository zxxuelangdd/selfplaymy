package threads.thread1.producter_comsumer;

import java.util.Random;

/**
 * @program: selfplay
 * @description: 表示客人的类
 * @author: zx
 * @create: 2018-09-02 12:03
 **/
public class EaterThread extends Thread{

    private final Table table;

    public EaterThread(String name,Table table) {
        super(name);
        this.table = table;
    }

    @Override
    public void run() {
        while (true){
            try {
                String take = table.take();
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
