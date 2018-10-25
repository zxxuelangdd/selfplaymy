package designpatterns.state;

/**
 * @program: selfplay
 * @description:
 * @author: zx
 * @create: 2018-10-25 20:23
 **/
public class Customer {
    public void consume() throws MovieException {
        // 普通电影
        Movie regularMovie = new Movie();
        regularMovie.setPrice(Movie.REGULAR);
        // 最新发布电影
        Movie newReleaseMovie = new Movie();
        newReleaseMovie.setPrice(Movie.NEW_RELEASE);
        // 儿童电影
        Movie childrenMovie = new Movie();
        childrenMovie.setPrice(Movie.CHILDREN);

        System.out.println("普通影片租赁10天的价格"
                + regularMovie.getPrice().getCharge(10));
        System.out.println("最新影片租赁10天的价格"
                + newReleaseMovie.getPrice().getCharge(10));
        System.out.println("儿童影片租赁10天的价格"
                + childrenMovie.getPrice().getCharge(10));

        System.out.println("普通影片租赁10天的积分"
                + regularMovie.getPrice().getIntegral(10));
        System.out.println("最新影片租赁10天的积分"
                + newReleaseMovie.getPrice().getIntegral(10));
        System.out.println("儿童影片租赁10天的积分"
                + childrenMovie.getPrice().getIntegral(10));


    }
}
