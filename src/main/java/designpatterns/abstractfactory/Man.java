package designpatterns.abstractfactory;

/**
 * @program: selfplay
 * @description: 产品具体实现
 * @author: zx
 * @create: 2018-09-06 10:33
 **/
public class Man implements Human {

    @Override
    public void say() {
        System.out.println("this is man");
    }
}
