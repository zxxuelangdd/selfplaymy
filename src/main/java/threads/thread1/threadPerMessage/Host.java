package threads.thread1.threadPerMessage;

/**
 * @program: selfplay
 * @description: 针对请求创建线程的类
 * @author: zx
 * @create: 2018-09-03 20:05
 **/
public class Host {
    private final Helper helper=new Helper();
    public void request(final int count,final char c){
        System.out.println("   request("+count+","+c+ " )beging");
        new Thread(){
            public void run() {
                helper.handle(count,c);
            }
        }.start();
    }
}
