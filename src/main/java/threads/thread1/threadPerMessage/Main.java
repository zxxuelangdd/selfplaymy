package threads.thread1.threadPerMessage;

/**
 * @program: selfplay
 * @description: 这项工作就交给你了, 向Host发送字符显示的请求
 * @author: zx
 * @create: 2018-09-03 20:04
 **/
public class Main {
    public static void main(String[] args) {
        System.out.println("main begin");
        Host host = new Host();
        host.request(10,'A');
        host.request(20,'B');
        host.request(30,'C');
        System.out.println("main  end");
    }
}
