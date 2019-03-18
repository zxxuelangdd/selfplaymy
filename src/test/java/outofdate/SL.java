package outofdate;

import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @program: selfplay
 * @description: 过期疫苗
 * @author: zx
 * @create: 2018-08-05 17:51
 **/
public class SL {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger();
        String url2 = "http://www.shangluo.gov.cn/info/1054/77352.htm";
        String url = "http://www.shangluo.gov.cn/index.htm";
        for (int i = 0; i < 200; i++) {
            new Thread("线程1") {
                public void run() {

                    for (int j = 0; j < 100000; j++) {
                        try {
                            SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            String format = time.format(new Date());
                            String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.84 Safari/537.36";
                            Connection.Response response = Jsoup.connect(url).data("Connection", "Keep-Alive").userAgent(userAgent).execute();
                            Connection.Response response2 = Jsoup.connect(url2).data("Connection", "Keep-Alive").userAgent(userAgent).execute();
                            //int i1 = response.statusCode();
                            System.out.println(format + "    " + response.statusCode() + "--" + response2.statusCode() + "=====" + atomicInteger.incrementAndGet());
                            // System.out.println(i1+"====="+atomicInteger.incrementAndGet());
                           /* Document document = Jsoup.connect(url).data("Connection","Keep-Alive").userAgent(userAgent).get();
                            String title = document.title();
                            Document document2 = Jsoup.connect(url2).data("Connection","Keep-Alive").userAgent(userAgent).get();
                            String title2 = document2.title();
                            System.out.println(title+"====="+title2+"======"+atomicInteger.incrementAndGet());
                       */
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                }
            }.start();

        }


    }
}
