package threads.ThreadPooldemo;


import com.netflix.hystrix.*;

import java.util.concurrent.TimeUnit;

/**
 * @program: selfplay
 * @description: 订单服务
 * @author: zx
 * @create: 2018-08-28 14:14
 **/
public class CommandOrder extends HystrixCommand<String> {

    private String orderName;

    public CommandOrder(String orderName) {
        super(Setter.withGroupKey(
//服务分组
                HystrixCommandGroupKey.Factory.asKey("OrderGroup"))
//线程分组
                        .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("UserPool"))
                        //线程池配置
                        .andThreadPoolPropertiesDefaults(HystrixThreadPoolProperties.Setter()
                                .withCoreSize(10)
                                .withKeepAliveTimeMinutes(5)
                                .withMaxQueueSize(10)
                                .withQueueSizeRejectionThreshold(10000))
                        .andCommandPropertiesDefaults(
                                HystrixCommandProperties.Setter()
                                        .withExecutionIsolationStrategy(HystrixCommandProperties.ExecutionIsolationStrategy.THREAD))
        );
        this.orderName = orderName;
    }

    @Override
    protected String run() throws Exception {
        System.out.println("  CommandOrder  ordername    run():" + orderName);
        TimeUnit.MILLISECONDS.sleep(100);
        return "OrderName" + orderName;
    }
}
