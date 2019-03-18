package threads.thread1.future;

/**
 * @program: selfplay
 * @description: 向请求返回FutureData的实体类
 * @author: zx
 * @create: 2018-09-08 11:49
 **/
public class Host {
    public Data request(final int count, final char c) {
        System.out.println("    request(" + count + "," + c + ")begin");
        //创建Future的实例
        FutureData futureData = new FutureData();
        //启动一个新的线程用于创建RealData的实例
        new Thread() {
            @Override
            public void run() {
                RealData realData = new RealData(count, c);
                //在线程里边使用的方法一般都会使用同步的方式  用于保证线程安全
                futureData.setRealdata(realData);
            }
        }.start();
        System.out.println("    request(" + count + "," + c + ")end");
        //返回实例
        return futureData;
    }
}
