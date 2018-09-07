package threads.thread1.immutable;

import java.util.List;

/**
 * @program: selfplay
 * @description:
 * @author: zx
 * @create: 2018-08-26 15:12
 **/
public class ReadThread extends Thread {
    private final List<Integer> list;
    private final int n;

    public ReadThread(List<Integer> list, int n) {
        super("ReadThread");
        this.n=n;
        this.list = list;
    }

    @Override
    public void run() {

        for (int i = 0; true; i++) {
            System.out.println("---------"+i);
            for (Integer n : list) {
              /**  try {
                    Thread.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*/
                System.out.println("read:"+n);
            }
        }
    }
}
