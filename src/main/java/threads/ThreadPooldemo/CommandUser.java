package threads.ThreadPooldemo;

import com.netflix.hystrix.*;

import java.util.concurrent.TimeUnit;

/**
 * @program: selfplay
 * @description: 用户服务
 * @author: zx
 * @create: 2018-08-28 14:21
 **/
public class CommandUser extends HystrixCommand<String> {
    private String userName;

    public CommandUser(String userName) {

        super(Setter.withGroupKey(
//服务分组
                HystrixCommandGroupKey.Factory.asKey("UserGroup"))
//线程分组
                        .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("UserGroup"))

//线程池配置
                        .andThreadPoolPropertiesDefaults(HystrixThreadPoolProperties.Setter()
                                .withCoreSize(10)
                                .withKeepAliveTimeMinutes(5)
                                .withMaxQueueSize(10)
                                .withQueueSizeRejectionThreshold(10000))


//线程池隔离
                        .andCommandPropertiesDefaults(
                                HystrixCommandProperties.Setter()
                                        .withExecutionIsolationStrategy(HystrixCommandProperties.ExecutionIsolationStrategy.THREAD))
        )
        ;
        this.userName = userName;
    }

    @Override
    public String run() throws Exception {

        System.out.println(" CommandUser  username:" + userName);

        TimeUnit.MILLISECONDS.sleep(100);
        return "userName=" + userName;
    }
}
