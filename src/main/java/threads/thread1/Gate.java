package threads.thread1;

import java.util.Random;

/**
 * @program: selfplay
 * @description: 表示门的类.在人们通过他时记录通过的姓名和出生地
 * @author: zx
 * @create: 2018-08-26 13:03
 **/
public class Gate {
    private int counter = 0;
    private String name;
    private String address;

    public /*synchronized*/ void pass(String name, String address, boolean b) throws InterruptedException {
        this.counter++;
        this.name = name;
/*        String name1 = Thread.currentThread().getName();
        System.out.println("--------------"+name1);*/
        Thread.sleep(new Random().nextInt(1));
        this.address = address;
        cheack(b);
    }

    public String toString() {
        return "Gate{" +
                "counter=" + counter +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' + ",线程name=" + Thread.currentThread().getName() +

                '}';
    }

    public void cheack(boolean a) {
        if (a) {
            System.out.println("*****BROKEN************" + toString());
        } else {
            if (name.charAt(0) != address.charAt(0)) {
                System.out.println("*****BROKEN************" + toString());
            }
        }
    }
}
