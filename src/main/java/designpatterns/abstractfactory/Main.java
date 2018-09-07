package designpatterns.abstractfactory;

/**
 * @program: selfplay
 * @description: 测试工厂方法   主要是产品抽象  和 工厂类抽象以及二者的实现  之前用到却不知道这工厂方法模式
 * @author: zx
 * @create: 2018-09-06 10:38
 **/
public class Main {
    public static void main(String[] args) {
        Human creathuman = new ManF().creathuman();
        creathuman.say();
        Human creathuman1 = new WomanF().creathuman();
        creathuman1.say();
    }
}
