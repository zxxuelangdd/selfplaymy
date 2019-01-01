package bussinessLicense;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import xunfeidemo.utils.FileUtil;
import xunfeidemo.utils.HttpUtil;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * 图像识别 WebAPI 接口调用示例
 * <p>
 * 运行方法：直接运行 main() 即可
 * <p>
 * 结果： 控制台输出图像识别结果信息
 * <p>
 * 备注:这里识别的是营业执照信息,未能识别执业许可证信息
 *
 * @author iflytek
 */
public class WebBussinessLicenseOCR {
    // OCR webapi 接口地址
    private static final String WEBOCR_URL = "http://webapi.xfyun.cn/v1/service/v1/ocr/business_license";
    // 应用ID
    private static final String APPID = "5b57d5ca";
    // 接口密钥
    private static final String API_KEY = "56b7a60600c8a8809a1eb730168f0579";

    // 图片地址
    private static final String AUDIO_PATH = "C:\\Users\\DELL\\Desktop\\Desktop";

    /**
     * OCR WebAPI 调用示例程序
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        File file = new File(AUDIO_PATH);
        File[] files = file.listFiles();
        for (File file1 : files) {
            String absolutePath = file1.getAbsolutePath();
            System.out.println("absolutePath:" + absolutePath);


            Map<String, String> header = buildHttpHeader();
            byte[] imageByteArray = FileUtil.read(absolutePath);
            String imageBase64 = new String(Base64.encodeBase64(imageByteArray), "UTF-8");
            String result = HttpUtil.doPost1(WEBOCR_URL, header, "image=" + URLEncoder.encode(imageBase64, "UTF-8"));
            System.out.println("OCR WebAPI 接口调用结果：" + result);

        }


    }

    /**
     * 组装http请求头
     */
    private static Map<String, String> buildHttpHeader() throws UnsupportedEncodingException {
        String curTime = System.currentTimeMillis() / 1000L + "";
        String param = "{\"engine_type\": \"business_license\"}";
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
