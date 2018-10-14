package threads.thread1.activeObject;
/**
 * @program: selfplay
 * @description: 委托ActiveObject来显示字符串的线程
 * @author: zx
 * @create: 2018-09-10 19:54
 **/
public class DisplayClientThread extends Thread{
    private final ActiveObject activeObject;

    public DisplayClientThread(String NAME,ActiveObject activeObject) {
        super(NAME);
        this.activeObject = activeObject;
    }

    @Override
    public void run() {
        try {
            for (int i = 0;true; i++) {
                String name = Thread.currentThread().getName()+"    "+i;
                activeObject.displayString(name);
                Thread.sleep(200);
            }
        } catch (Exception e) {
            System.out.println(Thread.currentThread().getName()+"   "+e);
        }
    }
}
