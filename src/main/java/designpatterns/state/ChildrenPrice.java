package designpatterns.state;

/**
 * @program: selfplay
 * @description:
 * @author: zx
 * @create: 2018-10-25 20:20
 **/
public class ChildrenPrice extends Price {
    /**
     * 儿童片返回租赁积分,儿童片积分规则为： 根据
     */
    public double getIntegral(int days) {
        // 返回租赁影片积分
        return days * 1.5;
    }

    /**
     * 儿童片返回租赁价格
     */
    public double getCharge(int days) {
        // 影片单价
        double result = 1.5;
        // 如果租赁时间大于3天则做价格优惠
        if (days > 3) {
            result += (days - 3) * 1.5;
        }
        // 返回租赁影片总价
        return result;
    }
}
