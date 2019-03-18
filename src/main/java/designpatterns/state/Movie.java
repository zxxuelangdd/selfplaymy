package designpatterns.state;

/**
 * @program: selfplay
 * @description:
 * @author: zx
 * @create: 2018-10-25 20:22
 **/
public class Movie {
    // 普通片标识
    public static String REGULAR = "designpatterns.state.RegularlPrice";

    // 新片标识
    public static String NEW_RELEASE = "designpatterns.state.NewReleasePrice";

    // 儿童片标识
    public static String CHILDREN = "designpatterns.state.ChildrenPrice";

    private Price price;

    public Price getPrice() {
        return price;
    }

    /**
     * 确定返回具体某个影片类型的实现类，有点像工厂
     *
     * @param movieCode 影片类型
     * @throws MovieException 若无影片类型则抛异常。
     */
    public void setPrice(String movieClass) throws MovieException {
        try {
            Class cls = Class.forName(movieClass);
            this.price = (Price) cls.newInstance();
        } catch (Exception e) {
            throw new MovieException("影片不存在");
        }
    }
}
