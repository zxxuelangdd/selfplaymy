package designpatterns.statueMy;

/**
 * @program: selfplay
 * @description:
 * @author: zx
 * @create: 2018-10-25 20:43
 **/
public class Statetwo extends BaseState<Person> {
    @Override
    public void doYouWant(Person person) {
        System.out.println("StateTwo 2222222222--------");
        System.out.println(person);
    }
}
