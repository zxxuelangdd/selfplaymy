package designpatterns.adapt;

/**
 * @program: selfplay
 * @description: 电压适配器
 * @author: zx
 * @create: 2018-09-06 11:23
 **/
public class PowerAdaptor implements  Power {

    private Power power;

    public PowerAdaptor(Power power) {
        this.power = power;
    }

    public void connect() {
        power.connect();
    }
}
