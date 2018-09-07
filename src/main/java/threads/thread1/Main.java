package threads.thread1;

/**
 * @program: selfplay
 * @description: 创建门, 并让几个人不断地通过
 * @author: zx
 * @create: 2018-08-26 13:15
 **/
public class Main {
    public static void main(String[] args) {
        System.out.println("begin enter Gate");
        Gate gate = new Gate();
        UserThread userThread = new UserThread( new Gate(), "Alice", "Alaska");
        userThread.setName("userThread---------a");
        userThread.start();
        UserThread userThread1 = new UserThread( new Gate(), "Bobby", "Brazil");
        userThread1.setName("userThread---------b");
        userThread1.start();
        UserThread userThread2 = new UserThread( new Gate(), "Chirs", "Canada");
        userThread2.setName("userThread--------c");
        userThread2.start();
    }
}
