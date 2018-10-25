package designpatterns.state;

/**
 * @program: selfplay
 * @description:
 * @author: zx
 * @create: 2018-10-25 20:21
 **/
public class RegularlPrice extends Price {
    /**
     * 普通片返回租赁积分，普通片积分规则
     */
    public double getIntegral(int days) {
        // 返回租赁影片积分
        return days * 2;
    }

    /**
     * 普通片返回租赁价格
     */
    public double getCharge(int days) {
        // 单价为2
        double result = 2;
        // 如果租赁天数大于2则，则优惠
        if (days > 2) {
            result += (days - 2) * 1.5;
        }
        // 返回总价
        return result;
    }

}
