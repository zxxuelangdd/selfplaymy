package threads.thread1.activeObject;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @program: selfplay
 * @description: 委托ActiveObject来生成字符串的线程
 * @author: zx
 * @create: 2018-09-10 19:53
 **/
public class MakerClientThread extends Thread{
    private final ActiveObject activeObject;
    private final char fillchar;

    public MakerClientThread(String name, ActiveObject activeObject) {
        super(name);
        this.activeObject = activeObject;
        this.fillchar = name.charAt(0);
    }

    @Override
    public void run() {
        try {
            for (int i=0;true;i++){
                Future<String> future = activeObject.makeString(i, fillchar);
                Thread.sleep(10);
                String s = future.get();
                System.out.println(Thread.currentThread().getName()+"   :value="+s);
            }
        } catch (Exception e) {
            System.out.println(Thread.currentThread().getName()+"       "+e);
        }
    }
}
