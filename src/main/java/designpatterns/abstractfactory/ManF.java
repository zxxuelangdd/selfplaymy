package designpatterns.abstractfactory;

/**
 * @program: selfplay
 * @description: 工厂类的具体实现
 * @author: zx
 * @create: 2018-09-06 10:36
 **/
public class ManF implements HumanFactory {

    @Override
    public Human creathuman() {
        return new Man();
    }
}
