package threads.thread1.thread_Specific_Storage.with;

/**
 * @program: selfplay
 * @description: 表名调用Log线程的类
 * @author: zx
 * @create: 2018-09-09 15:32
 **/
public class ClientThread extends Thread {
    public ClientThread(String name) {
        super(name);
    }

    @Override
    public void run() {
        System.out.println(getName() + " begin");
        for (int i = 0; i < 5; i++) {
            Log.println(Thread.currentThread().getName() + "    i-" + i);
            try {
                // Thread.sleep(100);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        Log.close();
        System.out.println(getName() + ":end");
    }
}
