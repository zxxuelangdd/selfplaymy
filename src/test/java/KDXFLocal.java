import com.asprise.ocr.Ocr;
import org.apache.commons.codec.binary.Base64;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @program: random
 * @description: 卫生监督验证码测试
 * @author: zx
 * @create: 2018-07-18 09:13
 **/
public class KDXFLocal {

    public static void main(String[] args) {
        String filePath="D:\\tem\\image";
        File file = new File(filePath);
        File[] files = file.listFiles();
        for (File file1 : files) {
            Base64 b64 = new Base64();
            try {
                FileInputStream fis = new FileInputStream(file1);
                System.out.print(file.length());
                byte[] buffer = new byte[(int)file.length()];
                System.out.print(buffer.length);
                fis.read(buffer);
                fis.close();
                String encodeToString = b64.encodeToString(buffer);
                System.out.println(file1.getName()+"==========="+encodeToString);

            } catch (IOException e) {
                e.printStackTrace();
            }


        }
    }
}
