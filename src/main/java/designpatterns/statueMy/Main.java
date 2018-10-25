package designpatterns.statueMy;

/**
 * @program: selfplay
 * @description:
 * @author: zx
 * @create: 2018-10-25 20:46
 **/
public class Main {
    public static void main(String[] args) {
        Person person = new Person().setAge("小学生").setName("little student");
        BaseState stateOne = new StateOne();

        Zoo zoo = new Zoo();
        zoo.setCat("傻帽");
        zoo.setRabbit("rabbitmq");
        stateOne.doYouWant(zoo);

        Statetwo statetwo = new Statetwo();

        Mystate mystate = new Mystate(statetwo);
        mystate.getBaseState().doYouWant(zoo);

        CombinStatus combinStatus = new CombinStatus();
        combinStatus.setBaseState(stateOne);
        combinStatus.setPerson(person);

        combinStatus.getBaseState().doYouWant(combinStatus.getPerson());
    }
}
