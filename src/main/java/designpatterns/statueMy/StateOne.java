package designpatterns.statueMy;

/**
 * @program: selfplay
 * @description:
 * @author: zx
 * @create: 2018-10-25 20:43
 **/
public class StateOne extends BaseState {
    @Override
    public void doYouWant(Object o) {
        System.out.println(o + " 尝试Object  StatuOne");
        System.out.println(o.toString());
    }

   /* public void doYouWant(Zoo zoo){
        System.out.println("dui Zoo 进行处理");
        System.out.println(zoo);
    }*/
}
