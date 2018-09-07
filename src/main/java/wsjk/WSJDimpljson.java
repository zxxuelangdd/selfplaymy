package wsjk;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
/**
 * question 这个网站验证逻辑
 *          关于 验证码  验证码有时候会带回来一个JSESSIONID   在用这个JSESSIONID去请求后台
 *         这个JSESSIONID会缓存最近一次查询  不需要使用验证码即可查询
 *         这个 JSESSIONID 的使用很重要  每一次不一定会有一个确定的结果
 *
 * */
/**
 * @description: 实现卫生监督的查询功能, 包括验证码识别和再次请求
 **/
public class WSJDimpljson {

    private static  final String JSESSIONID="JSESSIONID";


    private static Logger logger=LoggerFactory.getLogger(WSJDimpljson.class);
    public static void main(String[] args) {

//        String re = wsjdSearchClinicService("呼和浩特美年大健康体检有限公司云鼎门诊部");
//        String re = wsjdSearchClinicService("深圳安安诊所");
         String re = wsjdSearchClinicService("北京同仁堂");
        logger.info("result=============="+re);
    }

    private static String  wsjdSearchClinicService(String name){
        WsjdSearchRes wsjdSearchRes = new  WsjdSearchRes();
        System.out.println("=="+StringUtils.isBlank(name));
        System.out.println("=="+StringUtils.isNumeric(name));
        System.out.println("=="+StringUtils.isAlpha(name));
        System.out.println("=="+StringUtils.isNumeric(name));
        if (StringUtils.isBlank(name) || StringUtils.isNumeric(name) || name.length()<4){
            wsjdSearchRes.setCode("-1");
            wsjdSearchRes.setMessage("Illegal parameter");
        }else {
            System.out.println("开始测试");
            String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.84 Safari/537.36";
            //baseurl  访问网站,获取一个有效的 jsessionid;
            String baseurl = "https://credit.wsjd.gov.cn/portal";
            //imageUrl 该地址可以获取一个可用有时效的验证码,并可以获取一个新的jsessionid
            String imageUrl = "https://credit.wsjd.gov.cn/portal/captcha?temp=";
            //searchUrl 携带验证码识别结果和验证码返回的jsessionid和相关请求名称(需要进行url编码才有效)进行请求,获取结果
            String searchUrl = "https://credit.wsjd.gov.cn/portal/pubsearch/org/0114000000";

            // WsjdSearchRes wsjdSearchRes = WsjdSearchRes.PersonFactory();

            long millis = System.currentTimeMillis();
            Connection con = Jsoup.connect(baseurl).userAgent(userAgent).timeout(10000);

            Connection.Response response = null;
            for (int i = 0; i < 2; i++) {
                try {
                    response = con.execute();
                } catch (IOException e) {
                    wsjdSearchRes.setCode("3");
                    wsjdSearchRes.setMessage("主页网页请求异常");
                    logger.error("请求获取jsessionid " + baseurl + "出错", e);
                }

                if (response != null)
                    break;
            }
            String jsessionid = response.cookie(JSESSIONID);
            System.out.println("jsessionid----------------------------------"+jsessionid);
            long millis1 = System.currentTimeMillis();
            logger.info("反应时间" + (millis1 - millis));

            Connection.Response imagedoc = null;
            for (int j = 0; j < 2; j++) {
                try {
                    imagedoc = Jsoup.connect(imageUrl).timeout(10000).data("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8").cookie(JSESSIONID,
                            jsessionid)
                            .userAgent(userAgent).execute();
                } catch (IOException e) {
                    wsjdSearchRes.setCode("4");
                    wsjdSearchRes.setMessage("网页验证码请求异常");
                    logger.error("请求获取验证码 " + imagedoc + "出错", e);
                }
                if (imagedoc != null)
                    break;
            }
            byte[] img = imagedoc.bodyAsBytes();
            String jsessionidre = imagedoc.cookie("JSESSIONID");
            System.out.println("---------------------------jsessionidre----------------------"+jsessionidre);
            String recs = null;
            //识别验证码
            for (int j = 0; j < 3; j++) {
                try {
                    recs = XFRecognizeWordsUtils.discernImage(img);
                    System.out.println("识别结果:"+recs);
                    System.out.println("======================"+recs.length());
                } catch (UnsupportedEncodingException e) {
                    wsjdSearchRes.setCode("4");
                    wsjdSearchRes.setMessage("网页验证码识别异常");
                    logger.error("识别验证码出错", e);
                }

                if (recs != null)break;
            }

            for (int k = 0; k < 2; k++) {
                try {
                    String url = searchUrl + "?NAME=" + URLEncoder.encode(name, "UTF-8") + "&PASSCODE=&BEGIN_DATE=&END_DATE=&validCode=" + recs;
                   Document document = Jsoup.connect(url).userAgent(userAgent).cookie("JSESSIONID", jsessionidre).timeout(60000).get();
                    //Document document = Jsoup.connect(url).userAgent(userAgent).cookie("JSESSIONID", "D21FAF3FE8AF2CEEDA1E2778036B8A28.portal01").timeout(10000).get();
/*
                    System.out.println("************************************************");
                    System.out.println(document.getElementsByClass("list-tab"));
                    System.out.println("************************************************");*/

                    toSearchResult(document, wsjdSearchRes);
                    //通过判断是否为空继续请求
                    logger.info("请求最终结果 根据document判断是否使用第二次机会开始请求"+k);
                    if(!document.isBlock())break;

                } catch (IOException e) {
                    wsjdSearchRes.setCode("5");
                    wsjdSearchRes.setMessage("查询结果异常");
                    logger.error("查询结果出错", e);
                    //e.printStackTrace();
                }
                System.out.println("查询最终结果次数:"+k);
            }
        }

        JSONObject jsonObject = new JSONObject(wsjdSearchRes);
        String res = jsonObject.toString();


        return res;


    }

    private static void toSearchResult(Document document,WsjdSearchRes wsjdSearchRes) {
        Elements select = document.getElementById("formresult").select("tbody tr");
        System.out.println("获取的长度:"+select.size());


        ArrayList<Map<String,String>> list = Lists.newArrayList();
        int size = select.size();
        if(size==0){
            wsjdSearchRes.setCode("2");
            wsjdSearchRes.setMessage("未能查询到结果");
            return;
        }else if (size == 1 ){
            wsjdSearchRes.setCode("0");
            wsjdSearchRes.setMessage("success");

        }else{
            wsjdSearchRes.setCode("1");
            wsjdSearchRes.setMessage("查询结不准确,查询到多条,此处仅展示第一页");
        }
        logger.info("开始解析请求结果");
        for (Element element : select) {
            try {
                HashMap<String, String> map = Maps.newHashMap();
                Elements tds = element.select("td");
                //机构名称
                String name = tds.get(0).text();
                map.put("name",name);
                //机构类别
                String category = tds.get(1).text();
                map.put("category",category);
                //机构地址
                String address = tds.get(2).text();
                map.put("address",address);
                //批准文号
                String approveNo = tds.get(3).text();
                map.put("approveNo",approveNo);
                //发证机关
                String demp = tds.get(4).text();
                map.put("demp",demp);
                //有效期开始日期
                String validityDateStart = tds.get(5).text();
                map.put("validityDateStart",validityDateStart);
                //有效截止日期
                String validityDateEnd = tds.get(6).text();
                map.put("validityDateEnd",validityDateEnd);
                boolean empty = map.isEmpty();
                if (empty)
                    continue;
                list.add(map);
                //   System.out.println("tr=============="+element.text());
            }catch (Exception e){
                logger.error("解析请求结果异常",e);
            }
        }
        wsjdSearchRes.setData(list);



    }





}
