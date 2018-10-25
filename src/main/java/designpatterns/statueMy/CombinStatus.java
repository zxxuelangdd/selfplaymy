package designpatterns.statueMy;

import lombok.Data;

/**
 * @program: selfplay
 * @description:
 * @author: zx
 * @create: 2018-10-25 21:11
 **/
@Data
public class CombinStatus {
    private BaseState baseState;
    private Person person;
}
