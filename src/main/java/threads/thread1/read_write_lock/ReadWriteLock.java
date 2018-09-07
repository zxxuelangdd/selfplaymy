package threads.thread1.read_write_lock;

/**
 * @program: selfplay
 * @description: 提供读写锁的类
 * @author: zx
 * @create: 2018-09-02 18:01
 **/
public class ReadWriteLock {

    private int readingReaders=0; //实际正在读取的线程个数
    private int waitingWriters=0;//等待写的线程个数
    private int writerWriters=0;//实际正在写入的线程个数
    private boolean preferWriter=true; //若写入优先则为true
    public synchronized void readLock() throws InterruptedException {
        while (waitingWriters>0 || preferWriter && waitingWriters>0){
            wait();
        }
        readingReaders++;
    }
    public synchronized void readUnlock(){
        readingReaders--;
        preferWriter=true;
        notifyAll();
    }
    public synchronized void writeLock() throws InterruptedException {
        waitingWriters++;
        try {
            while (readingReaders>0 || writerWriters>0){
                wait();
            }
        } finally {
            waitingWriters--;
        }
        writerWriters++;

    }
    public synchronized void writeUnlock(){
        writerWriters--;
        preferWriter = false;
        notifyAll();
    }
}
