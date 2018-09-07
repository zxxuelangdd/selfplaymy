package threads.thread1.producter_comsumer;

/**
 * @program: selfplay
 * @description: 表示桌子的类
 * @author: zx
 * @create: 2018-09-02 12:03
 **/
public class Table {
    private final String[]  buffer;
    private int tail;//下次put的位置
    private int head;//下次take的位置
    private int count;//buffer中蛋糕的个数

    public Table(int count) {
        this.buffer = new String[count];
        this.tail = 0;
        this.head = 0;
        this.count = 0;
    }

    //放置蛋糕
    public synchronized void put(String cake) throws InterruptedException {
        System.out.println(Thread.currentThread().getName()+" put "+cake);
        while (count>=buffer.length){
            wait();
        }
        buffer[tail]=cake;
        tail=tail++%buffer.length;
        count++;
        notifyAll();

    }

    //拿蛋糕
    public synchronized String take() throws InterruptedException {
        while (count<=0){
            wait();
        }
        String cake=buffer[head];
        head=head++%buffer.length;
        count--;
        notifyAll();
        System.out.println(Thread.currentThread().getName()+" takes "+cake);
        return cake;
    }
}
