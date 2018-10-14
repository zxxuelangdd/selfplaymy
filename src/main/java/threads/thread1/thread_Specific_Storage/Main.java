package threads.thread1.thread_Specific_Storage;

/**
 * @program: selfplay
 * @description: 不使用threadSpecificStorage, 测试日志的类
 * @author: zx
 * @create: 2018-09-09 15:22
 **/
public class Main {
    public static void main(String[] args) {
        System.out.println("BEGIN");
        for (int i = 0; i < 10; i++) {
         Log.println("main i="+i);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Log.close();
        System.out.println("END");
    }
}
