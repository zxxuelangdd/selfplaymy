import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.File;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.Base64;

/**
 * @program: selfplay
 * @description:接口文档测试
 * @author: zx
 * @create: 2018-07-20 09:19
 **/
public class DocApiTest2 {
    public static void main(String[] args) throws Exception {
        String url = "http://webapi.xfyun.cn/v1/service/v1/ocr/general";
        String appid = "5b5090bd";
        String appKey = "08ee36f0df4012f30f080687646e574e";
        String curTime = String.valueOf(System.currentTimeMillis()/1000l);
        String xParam = "{\"language\": \"en\",\"location\": \"false\"}";
        String param = Base64.getEncoder().encodeToString(xParam.getBytes("UTF-8"));

        File file = new File("D:\\tem\\image\\6419.bmp");



        /**
        InputStream inputStream = null;
        byte[] data = null;
        try {
            inputStream = new FileInputStream("D:\\tem\\image\\6419.bmp");
            data = new byte[inputStream.available()];
            inputStream.read(data);
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        BASE64Encoder encoder = new BASE64Encoder();
        String encode1 = encoder.encode(data);*/





        InputStream fileInputStream = FileUtils.openInputStream(file);


       String body = Base64.getEncoder().encodeToString(IOUtils.toByteArray(fileInputStream));

        String encode = URLEncoder.encode(body,"UTF-8");

        String checkSum = DigestUtils.md5Hex((appKey + curTime + param).getBytes());



        HttpPost httpPost = new HttpPost(url);
        CloseableHttpClient httpClient = HttpClients.createDefault();
        StringEntity entity = new StringEntity(encode, "utf-8");
        entity.setContentType("application/x-www-form-urlencoded");
        httpPost.setEntity(entity);
        httpPost.setHeader("X-Appid", appid);
        httpPost.setHeader("X-CurTime", curTime);
        httpPost.setHeader("X-Param", param);
        httpPost.setHeader("X-CheckSum", checkSum);
        HttpResponse response = httpClient.execute(httpPost);
        if (response.getStatusLine().getStatusCode() == 200) {
            HttpEntity responseEntity = response.getEntity();
            String resJson = EntityUtils.toString(responseEntity, "utf-8");
            System.out.println("resJson===============" + resJson);
            JSONObject jsonObject = new JSONObject(resJson);
            String code = jsonObject.getString("code");
            if (code.equals("00000")) { // 成功
                String dataJson = jsonObject.getString("data");
                JSONObject dataObject = new JSONObject(dataJson);
                String result = dataObject.getString("result");
                System.out.println(result);
            } else { // 失败
                String desc = jsonObject.getString("desc");
                System.out.println("******************解析失败******************"+desc);
            }

        }


    }
}