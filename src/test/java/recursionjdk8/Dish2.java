package recursionjdk8;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @program: selfplay
 * @description: 碟 盘 一道菜
 * @author: zx
 **/
@Data
@Accessors(chain=true) //采用链式  相当于直接返回
public class Dish2 {
      public String name;

    public Dish2(String name) {
        this.name = name;
    }
}
