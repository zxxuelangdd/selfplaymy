package threads.thread1.activeObject;

import java.util.concurrent.Future;

/**
 * @program: selfplay
 * @description: 定义主动对象API的接口
 * @author: zx
 * @create: 2018-09-10 19:56
 **/
public interface ActiveObject {
    public abstract Future<String> makeString(int count, char c);

    public abstract void displayString(String string);

    public abstract void shutdown();
}
