package designpatterns.statueMy;

import lombok.Data;

/**
 * @program: selfplay
 * @description:
 * @author: zx
 * @create: 2018-10-25 20:45
 **/
@Data
public class Mystate {
    private  BaseState baseState;

    public Mystate(BaseState baseState) {
        this.baseState = baseState;
    }
}
