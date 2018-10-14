package threads.thread1.activeObject;

/**
 * @program: selfplay
 * @description: 创建主动对象的类
 * @author: zx
 * @create: 2018-09-10 19:56
 **/
public class ActiveObjectFactory {
    public static ActiveObject createActiveObject(){
        return new ActiveObjectImpl();
    }
}
