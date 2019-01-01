package threads.thread1.activeObject;

/**
 * @program: selfplay
 * @description: 测试程序行为的类
 * @author: zx
 * @create: 2018-09-10 19:52
 **/
public class Main {
    public static void main(String[] args) {
        ActiveObject activeObject = ActiveObjectFactory.createActiveObject();
        try {
            new MakerClientThread("Alice", activeObject).start();
            new MakerClientThread("Bobby", activeObject).start();
            new DisplayClientThread("Chris", activeObject).start();
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            activeObject.shutdown();
        }
    }
}
