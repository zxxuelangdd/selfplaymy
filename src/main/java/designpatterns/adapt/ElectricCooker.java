package designpatterns.adapt;

/**
 * @program: selfplay
 * @description: 美国110v的电饭煲
 * @author: zx
 * @create: 2018-09-06 11:21
 **/
public class ElectricCooker {

    private Power usa110;//110电饭煲要用110电压接口

    public ElectricCooker(Power usa110) {
        super();
        this.usa110 = usa110;
    }

    public void cook(){
        usa110.connect();//通电
        System.out.println("开始做饭。。。");
    }
}
