package designpatterns.state;

/**
 * @program: selfplay
 * @description:
 * @author: zx
 * @create: 2018-10-25 20:21
 **/
public class NewReleasePrice extends Price {
    /**
     * 最新发布片返回租赁积分,最新发布片积分规则
     */
    public double getIntegral(int days) {
        // 返回租赁影片积分
        return days * 3;
    }

    /**
     * 最新发布片返回租赁价格
     */
    public double getCharge(int days) {
        // 新片没有优惠，单价为3
        return days * 3;
    }
}
