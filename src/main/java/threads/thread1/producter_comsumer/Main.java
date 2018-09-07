package threads.thread1.producter_comsumer;

import org.checkerframework.checker.units.qual.A;

/**
 * @program: selfplay
 * @description: 测试程序执行的主类
 * @author: zx
 * @create: 2018-09-02 12:02
 **/
public class Main {
    public static void main(String[] args) {
        Table table = new Table(3);
        for (int i=1;i<3;i++)
            new MakerThread("MakerThread-"+i,table).start();
        for (int i=1;i<3;i++)
            new EaterThread("EaterThread-"+i,table).start();
    }
}
