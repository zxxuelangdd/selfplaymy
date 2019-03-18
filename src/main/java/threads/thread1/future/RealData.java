package threads.thread1.future;

/**
 * @program: selfplay
 * @description: 表示实际的处理类构造函数的处理会花很长时间
 * @author: zx
 * @create: 2018-09-08 11:52
 **/
public class RealData {
    private final String content;

    public RealData(int count, char c) {
        System.out.println("    making RealData(" + count + "," + c + ")begin");
        char[] buffer = new char[count];
        for (int i = 0; i < count; i++) {
            buffer[i] = c;
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("    making RealData(" + count + "," + c + ")end");
        this.content = new String(buffer);
    }

    public String getContent() {
        return content;
    }
}
