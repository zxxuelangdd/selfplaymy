package threads.thread1.balking;

import java.io.FileWriter;
import java.io.IOException;

/**
 * @program: selfplay
 * @description: 表示可以修改并保存数据的类
 * @author: zx
 * @create: 2018-09-02 10:15
 **/
public class Data {
    private final String filename;
    private  String content;
    private  boolean changed;

    public Data(String filename, String content) {
        this.filename = filename;
        this.content = content;
        this.changed = true;
    }

    //修改保存数据
    public synchronized void change(String newConteng){
        content=newConteng;
        changed=true;
        System.out.println(Thread.currentThread().getName()+" change:"+content);
    }
    public synchronized void save() throws IOException {
        if(!changed){
            return;
        }
        dosave();
        changed=false;
    }

    private void dosave() throws IOException {
        System.out.println(Thread.currentThread().getName()+" dosave:"+content);
       /* FileWriter fileWriter = new FileWriter(filename);
        fileWriter.write(content);
        fileWriter.close();*/
    }

}
