package designpatterns.statueMy;

/**
 * @program: selfplay
 * @description:
 * @author: zx
 * @create: 2018-10-25 20:39
 **/
public abstract  class BaseState<T> {
    public abstract void doYouWant(T t);
}
