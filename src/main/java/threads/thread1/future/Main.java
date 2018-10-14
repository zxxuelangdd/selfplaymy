package threads.thread1.future;

/**
 * @program: selfplay
 * @description: 先给您提货单 向Host发送请求并获取数据的类
 *
 * 这里可以使用concurrent包下边的FutureTask来进行实现 它里面本来就是一个启动线程等待待执行完有返回值的方式 线程安全,里边封装了同步的方法,因此只需继承就好
 * @author: zx
 * @create: 2018-09-08 11:48
 **/
public class Main {
    public static void main(String[] args) {
        System.out.println("main Begin");
        Host host = new Host();
        Data a = host.request(10, 'A');
        Data b = host.request(20, 'B');
        Data c = host.request(30, 'C');
        System.out.println("Main  OtherJob Begin");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Main  OtherJob End");

        System.out.println("c="+c.getContent());
        System.out.println("a="+a.getContent());
        System.out.println("b="+b.getContent());
        System.out.println("main end");
    }
}
