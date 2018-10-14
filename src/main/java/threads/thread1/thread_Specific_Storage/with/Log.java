package threads.thread1.thread_Specific_Storage.with;

/**
 * @program: selfplay
 * @description: 创建日志的类, 分配各个线程
 * @author: zx
 * @create: 2018-09-09 15:32
 **/
public class Log {

    private static final ThreadLocal<TSLog> tsLogCollection=new ThreadLocal<TSLog>();

    //写日志
    public static void println(String s){
        getTsLog().println(s);

    }
public static void close(){
        getTsLog().close();
}
    private static TSLog getTsLog() {
        System.out.println(Thread.currentThread().getName()+"       getTsLog");
        //当前线程容器
        TSLog tsLog = tsLogCollection.get();
        System.out.println(" tsLog -----"+tsLog);
        if(tsLog==null){

            System.out.println("如果是第一次调用就生成和注册一个TsLog");
            tsLog=new TSLog("D:\\"+Thread.currentThread().getName()+"-log.txt");
            tsLogCollection.set(tsLog);
        }
        System.out.println(" tsLog ============="+tsLog);
        return  tsLog;
    }
}
