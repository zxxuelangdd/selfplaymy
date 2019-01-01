package githubdemo;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

/**
 * AIUI WebAPI V2接口调用示例
 * <p>
 * 运行方法：直接运行 main()
 * <p>
 * 结果： 控制台输出接口返回值信息
 *
 * @author iflytek_aiui
 */
public class WebaiuiDemo {
    private static final String URL = "http://openapi.xfyun.cn/v2/aiui";
    private static final String APPID = "5b5090bd";
    private static final String API_KEY = "08ee36f0df4012f30f080687646e574e";
    private static final String FILE_PATH = "D:\\tem\\image\\6419.bmp";

    public static void main(String[] args) throws IOException, ParseException, InterruptedException {
        Map<String, String> header = buildHeader();
        byte[] dataByteArray = readFile(FILE_PATH);
        String result = httpPost(URL, header, dataByteArray);
        System.out.println(result);
    }

    private static Map<String, String> buildHeader() throws UnsupportedEncodingException, ParseException {
        String curTime = System.currentTimeMillis() / 1000L + "";
        String param = "{\"language\": \"en\",\"location\": \"false\"}";
        //使用个性化参数时参数格式如下：
        //String param = "{\"aue\":\""+AUE+"\",\"sample_rate\":\""+SAMPLE_RATE+"\",\"auth_id\":\""+AUTH_ID+"\",\"data_type\":\""+DATA_TYPE+"\",\"scene\":\""+SCENE+"\",\"pers_param\":\""+PERS_PARAM+"\"}";
        String paramBase64 = new String(Base64.encodeBase64(param.getBytes("UTF-8")));
        String checkSum = DigestUtils.md5Hex(API_KEY + curTime + paramBase64);

        Map<String, String> header = new HashMap<String, String>();
        header.put("X-Param", paramBase64);
        header.put("X-CurTime", curTime);
        header.put("X-CheckSum", checkSum);
        header.put("X-Appid", APPID);
        return header;
    }

    private static byte[] readFile(String filePath) throws IOException {
        InputStream in = new FileInputStream(filePath);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024 * 4];
        int n = 0;
        while ((n = in.read(buffer)) != -1) {
            out.write(buffer, 0, n);
        }
        byte[] data = out.toByteArray();
        in.close();
        return data;
    }

    private static String httpPost(String url, Map<String, String> header, byte[] body) {
        String result = "";
        BufferedReader in = null;
        OutputStream out = null;
        try {
            URL realUrl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) realUrl.openConnection();
            for (String key : header.keySet()) {
                connection.setRequestProperty(key, header.get(key));
            }
            connection.setDoOutput(true);
            connection.setDoInput(true);

            //connection.setConnectTimeout(20000);
            //connection.setReadTimeout(20000);
            try {
                out = connection.getOutputStream();
                out.write(body);
                out.flush();
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                while ((line = in.readLine()) != null) {
                    result += line;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
