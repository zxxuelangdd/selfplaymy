import com.asprise.ocr.Ocr;
import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.io.*;

/**
 * @program: random
 * @description: 卫生监督验证码测试
 * @author: zx
 * @create: 2018-07-18 09:13
 **/
public class WSJDCaptcha {

    public static void main(String[] args) {
//        String baseurl="https://credit.wsjd.gov.cn/portal/";
        String baseurl="https://credit.wsjd.gov.cn/portal/pubsearch/person/0101000000";
        String userAgent="Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.84 Safari/537.36";
        String imageUrl="https://credit.wsjd.gov.cn/portal/captcha?temp=";

        for (int i = 0; i < 10; i++) {
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
            savaImage(img,"D:\\tem\\image",i+".bmp");

         /*   Ocr.setUp();
            Ocr ocr = new Ocr();
            String s = ocr.recognize("test.png", -1, 0, 0, 400, 200,
                    Ocr.RECOGNIZE_TYPE_TEXT, Ocr.OUTPUT_FORMAT_PLAINTEXT);
*/
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
