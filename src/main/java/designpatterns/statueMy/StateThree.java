package designpatterns.statueMy;

/**
 * @program: selfplay
 * @description:
 * @author: zx
 * @create: 2018-10-25 20:43
 **/
public class StateThree extends BaseState<Person> {
    @Override
    public void doYouWant(Person person) {
        System.out.println("StateThree 333333-------");
        System.out.println(person);
    }
}
