package threads.thread1.future;

/**
 * @program: selfplay
 * @description: 表示RealData的提货单的类, 其他线程会创建RealData的实例
 * @author: zx
 * @create: 2018-09-08 11:51
 **/
public class FutureData implements Data {

    private RealData realdata = null;
    private boolean ready = false;

    public synchronized void setRealdata(RealData realdata) {
        if (ready) {
            return;
        }
        ;
        this.realdata = realdata;
        this.ready = true;
        notifyAll();
    }

    public synchronized String getContent() {

        //这里用于判断一定是set之后执行的 否则get方法没有什么实际意义 如果没有一直name一直等待并且一旦执行上边的set就会被即时唤醒
        while (!ready) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return realdata.getContent();
    }
}
