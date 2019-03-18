package designpatterns.adapt;

/**
 * @program: selfplay
 * @description: 适配器模式   用在这里好像都不叫适配器了 不过更方便点
 * 适配器模式使用场景:不准备实现接口中的所有方法的时候可以用 abstract 方式抽象类来实现这样的接口
 * @author: zx
 * @create: 2018-09-06 11:25
 **/
public class AdapterTest {
    public static void main(String[] args) {
        Chain220 chain220 = new Chain220();
        PowerAdaptor powerAdaptor = new PowerAdaptor(chain220);
        ElectricCooker electricCooker = new ElectricCooker(powerAdaptor);
        electricCooker.cook();
    }
}
