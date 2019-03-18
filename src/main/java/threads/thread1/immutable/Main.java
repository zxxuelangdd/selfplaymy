package threads.thread1.immutable;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @program: selfplay
 * @description:
 * @author: zx
 * @create: 2018-08-26 15:11
 **/
public class Main {
    public static void main(String[] args) {
//        List<Integer> list = Lists.newArrayList();
//        ArrayList<Integer> list = new ArrayList<>();
        List<Integer> list = new CopyOnWriteArrayList<>();
        new WriterThread(list, 200000000).start();
        new ReadThread(list, 200000000).start();

    }
}
