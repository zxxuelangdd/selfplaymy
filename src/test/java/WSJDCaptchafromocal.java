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
public class WSJDCaptchafromocal {

    public static void main(String[] args) {
        String filePath = "D:\\tem\\image\\test";
        File file = new File(filePath);
        File[] files = file.listFiles();

        Ocr.setUp(); // one time setup
        Ocr ocr = new Ocr(); // create a new OCR engine
        ocr.startEngine("eng", Ocr.SPEED_FASTEST); // English
        String s = ocr.recognize(files,
                Ocr.RECOGNIZE_TYPE_TEXT, Ocr.RECOGNIZE_TYPE_TEXT); // PLAINTEXT | XML | PDF | RTF
        System.out.println("Result========================== " + s);
        ocr.stopEngine();


        // System.out.println("bmp:================="+bmp);

      /*  File[] files = file.listFiles();
        for (File file1 : files) {

            Ocr ocr = new Ocr();
            ocr.recognize()

        }*/


    }
}
