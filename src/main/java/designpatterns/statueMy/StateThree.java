package designpatterns.statueMy;

/**
 * @program: selfplay
 * @description:
 * @author: zx
 * @create: 2018-10-25 20:43
 **/
public class StateThree extends BaseState {
    @Override
    public void doYouWant(Object o) {
        System.out.println(o + " 尝试Object  StatuThree");
        System.out.println(o.toString());
    }
}
