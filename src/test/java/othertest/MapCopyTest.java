package othertest;

import com.google.common.collect.Maps;
import com.sun.prism.shader.Solid_TextureYV12_AlphaTest_Loader;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: selfplay
 * @description: 尝试copy一个map
 * @author: zx
 * @create: 2018-08-16 11:31
 **/
public class MapCopyTest {
    public static void main(String[] args) {
        HashMap<String, String> map = Maps.newHashMap();
        for (int i = 0; i < 10; i++) {
            map.put(String.valueOf(i)+"序列","value"+i);
        }
        HashMap<String, String> mapc = Maps.newHashMap();
        map.entrySet().forEach(e->{
            mapc.put(e.getKey(),e.getValue());
        });
        String remove = map.remove("7序列");
        System.out.println("================="+mapc);
        System.out.println("================="+map);

        HashMap<String, String> mapr = Maps.newHashMap();
        mapr=map;
        System.out.println("mapr:"+mapr);
        System.out.println("map:"+map);
        System.out.println("-------------------------------------------");
        map.remove("6序列");
        System.out.println("mapr:"+mapr);
        System.out.println("map:"+map);

    }
}
