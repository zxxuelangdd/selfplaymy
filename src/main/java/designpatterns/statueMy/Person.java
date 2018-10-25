package designpatterns.statueMy;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @program: selfplay
 * @description:
 * @author: zx
 * @create: 2018-10-25 20:42
 **/
@Data
@Accessors(chain = true)
public class Person {
    private String name;
    private String age;
}
