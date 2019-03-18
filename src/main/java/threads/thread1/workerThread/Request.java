package threads.thread1.workerThread;

/**
 * @program: selfplay
 * @description: 表示工作请求的类
 * @author: zx
 * @create: 2018-09-03 20:40
 **/
public class Request {
    private final String name;
    private final int num;

    public Request(String name, int num) {
        this.name = name;
        this.num = num;
    }

    public void excute() {
        System.out.println(Thread.currentThread().getName() + "  execute  " + this);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "Request{" +
                "name='" + name + '\'' +
                ", num=" + num +
                '}';
    }
}
