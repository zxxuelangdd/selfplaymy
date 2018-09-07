package designpatterns.abstractfactory;

/**
 * @program: selfplay
 * @description:
 * @author: zx
 * @create: 2018-09-06 10:37
 **/
public class WomanF implements HumanFactory {
    @Override
    public Human creathuman() {
        return new Woman();
    }
}
