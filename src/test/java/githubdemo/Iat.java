package githubdemo;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.MessageDigest;

public class Iat {


    private final static String[] hexDigits = {"0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};

    public static void main(String[] args) {
        //讯飞开放平台注册申请应用的应用ID(APPID)
        String xAppid = "5b5090bd";
        System.out.println("X-Appid:" + xAppid);
        long time = System.currentTimeMillis() / 1000;
        String curTime = String.valueOf(time);
        System.out.println("X-CurTime:" + curTime);
        String xParam = "{\"language\": \"en\",\"location\": \"false\"}";
        String xParamBase64 = getBase64(xParam);
        System.out.println("X-Param:" + xParamBase64);
        //音频文件
        File file = new File("D:\\tem\\image\\5698.bmp");
        String fileData = null;
        try {
            InputStream is = new FileInputStream(file);
            byte[] bytes = IOUtils.toByteArray(is);
            fileData = Base64.encodeBase64String(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //fileData = "data=" + fileData;
        //ApiKey创建应用时自动生成
        String apiKey = "08ee36f0df4012f30f080687646e574e";
        String token = apiKey + curTime + xParamBase64;
        String xCheckSum = md5Encode(token);
        System.out.println("X-CheckSum:" + xCheckSum);
        String resBody = "";
        PrintWriter out = null;
        BufferedReader in = null;
        try {
            //String url = "http://webapi.xfyun.cn/v1/service/v1/ocr/general";
            String url = "http://webapi.xfyun.cn/v1/service/v1/ocr/handwriting";
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            HttpURLConnection conn = (HttpURLConnection) realUrl
                    .openConnection();
            conn.setReadTimeout(2000);
            conn.setConnectTimeout(1000);
            conn.setRequestMethod("POST");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestProperty("X-Appid", xAppid);
            conn.setRequestProperty("X-CurTime", curTime);
            conn.setRequestProperty("X-Param", xParamBase64);
            conn.setRequestProperty("X-CheckSum", xCheckSum);
            conn.setRequestProperty("Connection", "keep-alive");
            conn.setRequestProperty("Content-type",
                    "application/x-www-form-urlencoded; charset=utf-8");

            conn.setRequestProperty("image", URLEncoder.encode(fileData));
            //conn.setRequestProperty("image",fileData);

            InputStream input = conn.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(input, "UTF-8"));
            StringBuffer bs = new StringBuffer();
            String l = null;
            while ((l = bufferedReader.readLine()) != null) {
                bs.append(l).append("\n");
            }
            System.out.println(bs.toString());


            String responseMessage = conn.getResponseMessage();
            System.out.println("responseMessage:" + responseMessage);


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Base64加密
     *
     * @param str 加密字符串
     * @return
     * @author jlchen4
     * @date 2017年9月16日 下午3:45:30
     */
    public static String getBase64(String str) {
        if (str == null || "".equals(str)) {
            return "";
        }
        try {
            byte[] encodeBase64 = Base64.encodeBase64(str.getBytes("UTF-8"));
            str = new String(encodeBase64);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return str;
    }

    /**
     * md5加密
     *
     * @param source 加密字符串
     * @return
     * @author jlchen4
     * @date 2017年9月16日 下午3:44:46
     */
    public static String md5Encode(String source) {
        String result = null;
        try {
            result = source;
            // 获得MD5摘要对象
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            // 使用指定的字节数组更新摘要信息
            messageDigest.update(result.getBytes("utf-8"));
            // messageDigest.digest()获得16位长度
            result = byteArrayToHexString(messageDigest.digest());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    private static String byteArrayToHexString(byte[] bytes) {
        StringBuilder stringBuilder = new StringBuilder();
        for (byte tem : bytes) {
            stringBuilder.append(byteToHexString(tem));
        }
        return stringBuilder.toString();
    }

    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0) {
            n = 256 + n;
        }
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }
}
