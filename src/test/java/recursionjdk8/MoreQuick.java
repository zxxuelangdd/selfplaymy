package recursionjdk8;

/**
 * @program: selfplay
 * @description: 尝试高效
 * @author: zx
 * @create: 2018-08-09 14:31
 *
 *
 *
 *
 **/
public class MoreQuick {
    public static void main(String[] args) {
        Runnable hello = new Runnable() {
            public void run() {
                System.out.println("hello 匿名类");
            }
        };
        hello.run();
        Runnable hi=()-> System.out.println("hello lambda");
        hi.run();
    }
}
