package threads.thread1.balking;

/**
 * @program: selfplay
 * @description: 流程测试主类
 * @author: zx
 * @create: 2018-09-02 10:16
 **/
public class Main {
    public static void main(String[] args) {
        Data data = new Data("D:\\balking.txt", "first");
        new ChangeThread("ChangedThread", data).start();
        new SaveThread("SaveThread", data).start();
    }
}
