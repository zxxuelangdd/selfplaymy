import com.asprise.ocr.Ocr;
import org.apache.commons.codec.binary.Base64;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * @program: random
 * @description: 卫生监督验证码测试
 * @author: zx
 * @create: 2018-07-18 09:13
 **/
public class WSJDImageGet {

    public static void main(String[] args) {
//        String baseurl="https://credit.wsjd.gov.cn/portal/";
        String baseurl="https://credit.wsjd.gov.cn/portal/pubsearch/org/0114000000";
        String searchUrl="https://credit.wsjd.gov.cn/portal/pubsearch/org/0114000000";
        //https://credit.wsjd.gov.cn/portal/pubsearch/org/0114000000?NAME=%E7%BE%8E%E5%B9%B4%E5%A4%A7%E5%81%A5%E5%BA%B7&PASSCODE=&BEGIN_DATE=&END_DATE=&validCode=9696
        String userAgent="Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.84 Safari/537.36";
        String imageUrl="https://credit.wsjd.gov.cn/portal/captcha?temp=";

        for (int i = 0; i < 33; i++){
            long millis = System.currentTimeMillis();
            Connection con = Jsoup.connect(baseurl).userAgent(userAgent);

            Connection.Response response=null;
            try {
                response = con.execute();
            } catch (IOException e) {
                e.printStackTrace();
            }
           /* if(response==null || response.statusCode()!=200)
                System.out.println("esponse.statusCode():"+response.statusCode()+"    response:"+response);*/
            String jsessionid = response.cookie("JSESSIONID");
            System.out.println(i+"====== JSESSIONID:"+jsessionid);
            long millis1 = System.currentTimeMillis();
            System.out.println("请求间隔差:"+(millis1-millis));

            Connection.Response imagedoc=null;

            try {
                imagedoc= Jsoup.connect(imageUrl).cookie("JSESSIONID",jsessionid).userAgent(userAgent).execute();
            } catch (IOException e) {
                e.printStackTrace();
            }

            byte[] img = imagedoc.bodyAsBytes();
            System.out.println("ima==========="+img);


            String s = Base64.encodeBase64String(img);
            System.out.println("Base64.encodeBase64String(img)=========="+s);
            ByteArrayInputStream in = new ByteArrayInputStream(img);    //将b作为输入流；
            BufferedImage image = null;
            try {
                 image = ImageIO.read(in);     //将in作为输入流，读取图片存入image中，而这里in可以为ByteArrayInputStream();
            } catch (IOException e) {
                e.printStackTrace();
            }

            savaImage(img,"D:\\tem\\image\\test",i+".bmp");
           // Ocr.setUp(); // one time setup

        }
    }

    private static void toparse(Document document) {
        Elements select = document.getElementById("formresult").select("tbody tr");
        System.out.println("获取的长度:"+select.size());
        for (Element element : select) {
            System.out.println("tr=============="+element.text());
        }
    }

    public static void savaImage(byte[] img,String filePath,String fileName) {
        BufferedOutputStream bos = null;
        FileOutputStream fos = null;
        File file = null;
        File dir = new File(filePath);
        try {
            //判断文件目录是否存在
            if(!dir.exists() && dir.isDirectory()){
                dir.mkdir();
            }
            file = new File(filePath+"\\"+fileName);
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            bos.write(img);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally{
            if(bos!=null){
                try {
                    bos.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            if(fos!=null){
                try {
                    fos.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }



    }
}
