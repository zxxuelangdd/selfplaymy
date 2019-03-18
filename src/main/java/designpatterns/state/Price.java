package designpatterns.state;

/**
 * @program: selfplay
 * @description: 价格基类
 * @author: zx
 * @create: 2018-10-25 20:20
 **/
public abstract class Price {
    /**
     * 获取租赁影片价格需实现该此方法
     *
     * @param days 租赁天数
     * @return 返回影片价格
     */
    public abstract double getCharge(int days);

    /**
     * 获取租赁影片积分需实现此方法
     *
     * @param days 租赁天数
     * @return 返回影片积分
     */
    public abstract double getIntegral(int days);
}
