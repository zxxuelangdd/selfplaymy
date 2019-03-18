package threads.thread1.threadPerMessage;

/**
 * @program: selfplay
 * @description: 提供字符被动显示的类
 * @author: zx
 * @create: 2018-09-03 20:06
 **/
public class Helper {
    public void handle(int count, char c) {
        System.out.println("   handle(" + count + "," + c + " )beging");
        for (int i = 0; i < count; i++) {
            try {
                Thread.sleep(1000);
                System.out.println(c);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println();
        System.out.println("   handle(" + count + "," + c + " )end");
    }
}
