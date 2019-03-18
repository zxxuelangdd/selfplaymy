import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import wsjk.CommonFileds;

import java.io.IOException;

/**
 * @program: selfplay
 * @description: 使用浏览器的相关参数直接请求测试结果
 * @author: zx
 * @create: 2018-07-19 13:39
 **/
public class WSJDRequest {
    public static void main(String[] args) throws IOException {
        String url = "https://credit.wsjd.gov.cn/portal/pubsearch/org/0114000000?NAME=%E7%BE%8E%E5%B9%B4%E5%A4%A7%E5%81%A5%E5%BA%B7&PASSCODE=&BEGIN_DATE=&END_DATE=&validCode=";
        String code = "3296";
        String JSESSIONID = "A352D487A22974D9D9BCD59F13D51707.portal01";
        String requestUrl = url + code;
        Document doc = Jsoup.connect(requestUrl).userAgent(CommonFileds.userAgent).cookie("JSESSIONID", JSESSIONID).get();
        System.out.println("========================================");
        System.out.println(doc);
        System.out.println("=========================================");
        toparse(doc);


    }

    private static void toparse(Document doc) {
        Elements select = doc.getElementById("formresult").select("tbody tr");
        System.out.println("获取的长度:" + select.size());
        for (Element element : select) {
            System.out.println("tr==============" + element.text());
        }
    }
}
