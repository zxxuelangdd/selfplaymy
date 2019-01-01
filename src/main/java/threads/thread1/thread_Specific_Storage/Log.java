package threads.thread1.thread_Specific_Storage;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @program: selfplay
 * @description: 创建日志的类
 * @author: zx
 * @create: 2018-09-09 15:23
 **/
public class Log {
    private static PrintWriter printWriter;

    static {
        try {
            printWriter = new PrintWriter(new FileWriter("D:\\log.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //写日志
    public static void println(String s) {
        printWriter.println(s);
    }

    public static void close() {
        printWriter.println("======END OF LOG======");
        printWriter.close();
    }
}
