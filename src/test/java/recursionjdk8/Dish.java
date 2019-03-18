package recursionjdk8;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @program: selfplay
 * @description: 碟 盘 一道菜
 * @author: zx
 **/
@Data(staticConstructor = "PersonFactory")
@Accessors(chain = true) //采用链式  相当于直接返回
public class Dish {
    public enum Type {MEAT, FISH, OTHER}

    private final String name;
    private final boolean vegetarian;
    private final int calories;
    private final Type type;
}
