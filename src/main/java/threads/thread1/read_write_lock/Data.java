package threads.thread1.read_write_lock;

/**
 * @program: selfplay
 * @description: 可以读写的类
 * @author: zx
 * @create: 2018-09-02 17:47
 **/
public class Data {
    private final char[] buffer;
    private final ReadWriteLock lock = new ReadWriteLock();

    public Data(int size) {
        this.buffer = new char[size];
        for (int i = 0; i < buffer.length; i++) {
            buffer[i] = '*';
        }
    }

    public char[] read() throws InterruptedException {
        lock.readLock();
        try {
            return doread();
        } finally {
            lock.readUnlock();
        }
    }

    private char[] doread() throws InterruptedException {
        char[] chars = new char[buffer.length];
        for (int i = 0; i < chars.length; i++) {
            chars[i] = buffer[i];
        }
        Thread.sleep(500);
        return chars;
    }

    public void write(char a) throws InterruptedException {

        lock.writeLock();
        try {
            System.out.println("写入ing  暂停其他执行  begin" + Thread.currentThread().getName());
            doWriter(a);
            System.out.println("写入end  可以执行其它 end" + Thread.currentThread().getName());
        } finally {
            lock.writeUnlock();
        }

    }

    private void doWriter(char a) throws InterruptedException {
        for (int i = 0; i < buffer.length; i++) {
            buffer[i] = a;
        }

        Thread.sleep(5000);
    }
}
