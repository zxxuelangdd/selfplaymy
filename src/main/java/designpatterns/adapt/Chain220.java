package designpatterns.adapt;

/**
 * @program: selfplay
 * @description: 中国标准电压接口
 * @author: zx
 * @create: 2018-09-06 11:16
 **/
public class Chain220 implements Power{

    @Override
    public void connect() {
        System.out.println("这是中国电源  已连接 220   请使用");
    }
}
