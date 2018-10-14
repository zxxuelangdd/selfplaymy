package threads.thread1.thread_Specific_Storage.with;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @program: selfplay
 * @description: 创建日志的类, 实例属于各个线程所有
 * @author: zx
 * @create: 2018-09-09 15:31
 **/
public class TSLog {
    private PrintWriter printWriter=null;
    //初始化字段

    public TSLog(String filename) {
        try {
            printWriter=new PrintWriter(new FileWriter(filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //写日志
    public  void println(String s){
        printWriter.println(s);
    }
    public  void close(){
         printWriter.println("======END OF LOG======");
        printWriter.close();
    }
}
