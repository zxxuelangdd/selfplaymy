package designpatterns.abstractfactory;

/**
 * @program: selfplay
 * @description: 产品具体实现接口
 * @author: zx
 * @create: 2018-09-06 10:34
 **/
public class Woman implements Human {

    @Override
    public void say() {
        System.out.println("this is woman");
    }
}
