package threads.thread1;

/**
 * @program: selfplay
 * @description: 简单的线程
 * @author: zx
 * @create: 2018-08-26 12:12
 **/
public class SimplethreadDemo {
    public static void main(String[] args) {
          new PrintThread("a").start();
          /**
           * run 方法只是一个方法,并没有启动一个线程
           * */
          new PrintThread("b").run();
        System.out.println();
        System.out.println("ok");
    }
    public static class PrintThread extends Thread{

        private String str;

        public PrintThread(String str) {
            this.str = str;
        }
        public void run() {
            for (int i = 0; i < 1000; i++) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.print(str);
            }

        }
    }
}
