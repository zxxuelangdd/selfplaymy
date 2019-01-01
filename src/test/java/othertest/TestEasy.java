package othertest;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import wsjk.CommonFileds;

import java.io.IOException;
import java.net.URLEncoder;

/**
 * @program: selfplay
 * @description: something easy to impl
 * @author: zx
 * @create: 2018-08-09 14:02
 **/
public class TestEasy {
    public static void main(String[] args) throws IOException {
       /* long l = System.nanoTime();
        System.out.println(l);
        System.out.println(l/1_0_0_0);
        System.out.println(l/1_000);
        System.out.println(System.currentTimeMillis());*/
        String name = URLEncoder.encode("美年大健康", "UTF-8");
        String code = "3316";
        String JSESSIONID = "0AD95A58D706052579D25596BD29799B.portal01";
        // String JSESSIONID="D21FAF3FE8AF2CEEDA1E2778036B8A28.portal01";
        String url = "https://credit.wsjd.gov.cn/portal/pubsearch/org/0114000000?NAME=" + name + "&PASSCODE=&BEGIN_DATE=&END_DATE=&validCode=" + code;

        String a = "https://credit.wsjd.gov.cn/portal/pubsearch/org/0114000000?NAME=" + name + "&PASSCODE=&BEGIN_DATE=&END_DATE=&validCode=" + code;
        Document doc = Jsoup.connect(a).userAgent(CommonFileds.userAgent).cookie("JSESSIONID", JSESSIONID).timeout(20000).get();
        System.out.println("--------------------------------------");
        System.out.println(doc);
        System.out.println("--------------------------------------");
        Elements select = doc.getElementsByClass("list-tab");
        System.out.println("*********************************************");
        Elements select2 = doc.getElementById("formresult").select("tbody tr");
        System.out.println("select2:" + select2);
        System.out.println("*********************************************");
        System.out.println("===============:" + select);
    }
}
