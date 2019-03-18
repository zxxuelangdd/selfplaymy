package wsjk;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: selfplay
 * @description: 实现卫生监督的查询功能, 包括验证码识别和再次请求
 * @author: zx
 * @create: 2018-07-23 09:39
 **/
public class WSJDimpl2 {

    // OCR webapi 接口地址
    private static final String WEBOCR_URL = "http://webapi.xfyun.cn/v1/service/v1/ocr/handwriting";
    // 应用ID
    private static final String APPID = "5b5090bd";
    // 接口密钥
    private static final String API_KEY = "08ee36f0df4012f30f080687646e574e";
    // 是否返回位置信息
    private static final String LOCATION = "false";
    // 语种
    private static final String LANGUAGE = "en";

    public static void main(String[] args) {

        try {
            wsjdSearchClinicService("北京同仁堂");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    private static void wsjdSearchClinicService(String outername) throws UnsupportedEncodingException {

        String name = URLEncoder.encode(outername, "UTF-8");
        //请求头 userAgent 没有也可以,习惯加上
        String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.84 Safari/537.36";
        //baseurl  访问网站,获取一个有效的 jsessionid;
        String baseurl = "https://credit.wsjd.gov.cn/portal";
        //imageUrl 该地址可以获取一个可用有时效的验证码,并可以获取一个新的jsessionid
        String imageUrl = "https://credit.wsjd.gov.cn/portal/captcha?temp=";
        //searchUrl 携带验证码识别结果和验证码返回的jsessionid和相关请求名称(需要进行url编码才有效)进行请求,获取结果
        String searchUrl = "https://credit.wsjd.gov.cn/portal/pubsearch/org/0114000000";
        Connection con;
        for (int i = 0; i < 3; i++) {
            long millis = System.currentTimeMillis();
            con = Jsoup.connect(baseurl).userAgent(userAgent).timeout(10000);
            Connection.Response response = null;
            try {
                response = con.execute();
            } catch (IOException e) {
                e.printStackTrace();
            }

            String jsessionid = response.cookie("JSESSIONID");
            System.out.println(i + "====== JSESSIONID:" + jsessionid);
            long millis1 = System.currentTimeMillis();
            System.out.println("请求间隔差:" + (millis1 - millis));

            Connection.Response imagedoc = null;

            try {
                imagedoc = Jsoup.connect(imageUrl).data("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8").cookie("JSESSIONID", jsessionid).userAgent(userAgent).execute();
            } catch (IOException e) {
                e.printStackTrace();
            }
            byte[] img = imagedoc.bodyAsBytes();
            String jsessionidre = imagedoc.cookie("JSESSIONID");
            System.out.println(i + "--------jsessionidre----------" + jsessionidre);
            String s = null;
            //识别验证码
            for (int j = 0; j < 3; j++) {
                try {
                    s = discernImage(img);
                    if (s != null)
                        break;
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
            if (s == null)
                return;
            try {

                String a = "https://credit.wsjd.gov.cn/portal/pubsearch/org/0114000000?NAME=" + name + "&PASSCODE=&BEGIN_DATE=&END_DATE=&validCode=" + s;
                String url = searchUrl + "?NAME=" + URLEncoder.encode(name, "UTF-8") + "&PASSCODE=&BEGIN_DATE=&END_DATE=&validCode=" + s;
                System.out.println("***************:" + url);
                //Document document = Jsoup.connect(url).userAgent(userAgent).cookie("JSESSIONID", jsessionidre).get();
                Document document = Jsoup.connect(a).userAgent(userAgent).cookie("JSESSIONID", jsessionidre).timeout(10000).get();
                Elements select2 = document.getElementById("formresult").select("tbody tr");
                System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
                System.out.println(select2);
                System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
                toparse(document);
                Elements tbody_tr = document.select("#formresult").select("tbody tr");

            } catch (IOException e) {
                e.printStackTrace();
            }


        }


    }

    private static String toparse(Document document) {
        System.out.println("-----------------------------");
        System.out.println(document);
        System.out.println("-----------------------------");
        Elements select = document.getElementById("formresult").select("tbody tr");
        System.out.println("获取的长度:" + select.size());

//        WsjdSearchRes wsjdSearchRes = WsjdSearchRes.PersonFactory();
        WsjdSearchRes wsjdSearchRes = new WsjdSearchRes();
        ArrayList<Map<String, String>> list = Lists.newArrayList();
        int size = select.size();

        if (size == 0) {
            wsjdSearchRes.setCode("1");
            wsjdSearchRes.setMessage("未能查询到结果");
        } else if (size == 1) {
            wsjdSearchRes.setCode("0");
            wsjdSearchRes.setMessage("success");
        } else {
            wsjdSearchRes.setCode("2");
            wsjdSearchRes.setMessage("查询结不准确,查询到多条,此处仅展示第一页");
        }


        for (Element element : select) {
            HashMap<String, String> map = Maps.newHashMap();
            Elements tds = element.select("td");
            //机构名称
            String name = tds.get(0).text();
            map.put("name", name);
            //机构类别
            String category = tds.get(1).text();
            map.put("category", category);
            //机构地址
            String address = tds.get(2).text();
            map.put("address", address);
            //批准文号
            String approveNo = tds.get(3).text();
            map.put("approveNo", approveNo);
            //发证机关
            String demp = tds.get(4).text();
            map.put("demp", demp);
            //有效期开始日期
            String validityDateStart = tds.get(5).text();
            map.put("validityDateStart", validityDateStart);

            //有效截止日期
            String validityDateEnd = tds.get(6).text();
            map.put("validityDateEnd", validityDateEnd);

            boolean empty = map.isEmpty();
            if (empty)
                continue;

            list.add(map);

          /*  Elements tr = element.select("tr");
            map.put("name",name);*/
            System.out.println();
           /* for (Element eltr : tr) {


              //  HashMap<String, String> map = Maps.newHashMap();

                //System.out.print(eltr.text()+"=====");
                Elements tds = eltr.select("td");

                //机构名称
                String name = tds.get(0).text();
               // System.out.println("机构名称:"+name);

                //机构类别
                String category = tds.get(1).text();
               // System.out.println("机构类别:"+category);

                //机构类别
                String address = tds.get(2).text();
                //System.out.println("机构地址:"+address);

                //批准文号
                String approveNo = tds.get(3).text();
               // System.out.println("批准文号:"+approveNo);
                //发证机关
                String demp = tds.get(4).text();
                //System.out.println("发证机关:"+demp);

                //有效期开始日期
                String validityDateStart = tds.get(5).text();
                //System.out.println("有效开始日期:"+validityDateStart);

                //有效截止日期
                String validityDateEnd = tds.get(5).text();
                //System.out.println("有效截止日期:"+validityDateEnd);
            }*/
            System.out.println();
            //   System.out.println("tr=============="+element.text());
        }
        wsjdSearchRes.setData(list);


        JSONObject jsonObject = new JSONObject(wsjdSearchRes);
        String s = jsonObject.toString();
        System.out.println("-----------------------");
        System.out.println(s);
        System.out.println("-----------------------");

        System.out.println("wsjdSearchRes:" + wsjdSearchRes);
        return s;

    }

    private static String discernImage(byte[] img) throws UnsupportedEncodingException {
        Map<String, String> header = buildHttpHeader();
        String imageBase64 = new String(Base64.encodeBase64(img), "UTF-8");
        String result = HttpUtil.doPostRecognozeWords(WEBOCR_URL, header, "image=" + URLEncoder.encode(imageBase64, "UTF-8"));
        System.out.println("========================" + result);
        //解析获取验证码结果
        String recognition = getRecognition(result);
        System.out.println("========================" + recognition);
        return recognition;
    }

    private static String getRecognition(String result) {
        JSONObject jsonObject = new JSONObject(result);
        String code = jsonObject.getString("code");
        if (code.equals("0")) {
            // String string = jsonObject.getJSONObject("data").getJSONArray("block").getJSONArray(1).getJSONArray(1).getJSONObject(0).getString("content");
            try {
                String string = jsonObject.getJSONObject("data").getJSONArray("block").getJSONObject(0).getJSONArray("line").getJSONObject(0).getJSONArray("word").getJSONObject(0).getString("content");
                return string;
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;

        } else {
            return null;
        }

    }

    /**
     * 组装http请求头
     */
    private static Map<String, String> buildHttpHeader() throws UnsupportedEncodingException {
        String curTime = System.currentTimeMillis() / 1000L + "";
        String param = "{\"location\":\"" + LOCATION + "\",\"language\":\"" + LANGUAGE + "\"}";
        String paramBase64 = new String(Base64.encodeBase64(param.getBytes("UTF-8")));
        String checkSum = DigestUtils.md5Hex(API_KEY + curTime + paramBase64);
        Map<String, String> header = new HashMap<String, String>();
        header.put("Content-Type", "application/x-www-form-urlencoded; charset=utf-8");
        header.put("X-Param", paramBase64);
        header.put("X-CurTime", curTime);
        header.put("X-CheckSum", checkSum);
        header.put("X-Appid", APPID);
        return header;
    }
}
