package designpatterns.adapt;

/**
 * @program: selfplay
 * @description:
 * @author: zx
 * @create: 2018-09-06 11:15
 **/
public class USA110impl implements Power {
    @Override
    public void connect() {
        System.out.println("USA110impl 110伏电压  已接通  可以开始工作");
    }
}
