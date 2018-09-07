package threads.thread1.immutable;

import java.util.List;

/**
 * @program: selfplay
 * @description:
 * @author: zx
 * @create: 2018-08-26 15:12
 **/
public class WriterThread extends Thread {
    private final List<Integer> list;
    private final int n;

    public WriterThread(List<Integer> list,int n) {
        super("WriterThread");
        this.n=n;
        this.list = list;
    }

    @Override
    public void run() {
        for (int i = 0; true; i++) {
           list.add(i);
          /*  try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
            list.remove(0);
           // System.out.println(list.size()+"-------------");

        }
    }
}
