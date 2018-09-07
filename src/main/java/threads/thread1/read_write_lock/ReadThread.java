package threads.thread1.read_write_lock;

/**
 * @program: selfplay
 * @description: 表示读取线程的类
 * @author: zx
 * @create: 2018-09-02 18:00
 **/
public class ReadThread extends Thread {
    private final Data data;

    public ReadThread(Data data) {
        this.data = data;
    }

    @Override
    public void run() {
        while (true){
            try {
                char[] read = data.read();
                System.out.println(Thread.currentThread().getName()+"  reads  "+String.valueOf(read));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
