package outofdate;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.time.temporal.TemporalAmount;

/**
 * @program: selfplay
 * @description: 过期疫苗
 * @author: zx
 * @create: 2018-08-05 17:51
 **/
public class Vaccine {
    public static void main(String[] args) {
        int i=0;
        for (int j = 0; j < 10000; j++) {
            String url="https://www.baidu.com/s?ie=utf-8&mod=1&isbd=1&isid=89502b1100024575&ie=utf-8&f=8&rsv_bp=1&rsv_idx=1&tn=baidu&wd=%E8%BF%87%E6%9C%9F%E7%96%AB%E8%8B%97&oq=%25E8%25BF%2587%25E6%259C%259F%25E7%2596%25AB%25E8%258B%2597&rsv_pq=89502b1100024575&rsv_t=9e99wWQKigQ%2FVYowYbBTtnP5lW3yva2SnbLSvv0kWK1XrdygM2q0pfyE%2Bv0&rqlang=cn&rsv_enter=0&bs=%E8%BF%87%E6%9C%9F%E7%96%AB%E8%8B%97&rsv_sid=26525_1432_21099_26350_26920_22075&_ss=1&clist=eb54de54a45af904&hsug=&f4s=1&csor=4&_cr1=31276";
            try {
                String userAgent="Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.84 Safari/537.36";
                Document document = Jsoup.connect(url).userAgent(userAgent).get();
                String title = document.title();
                i++;
                System.out.println(title+"==========="+i);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
