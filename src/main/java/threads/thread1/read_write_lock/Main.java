package threads.thread1.read_write_lock;

/**
 * @program: selfplay
 * @description: 测试主类, 大家一起读没问题, 但是读的时候不要写
 * @author: zx
 * @create: 2018-09-02 17:47
 **/
public class Main {
    public static void main(String[] args) {
        Data data = new Data(10);
        for (int i = 0; i <5; i++)
            new ReadThread(data).start();
        for (int i = 0; i <2; i++)
            new WriteThread(data,"qweripasdjklzxcvbnm").start();

    }
}
