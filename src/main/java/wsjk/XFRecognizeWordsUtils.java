package wsjk;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Http Client 工具类
 */
public class XFRecognizeWordsUtils {
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

	private static Logger logger=LoggerFactory.getLogger(XFRecognizeWordsUtils.class);
	/**
	 * 发送post请求
	 * 
	 * @param url
	 * @param header
	 * @param body
	 * @return
	 */
	private static String doPostRecognozeWords(String url, Map<String, String> header, String body) {
		String result = "";
		BufferedReader in = null;
		PrintWriter out = null;
		try {
			// 设置 url
			URL realUrl = new URL(url);
			URLConnection connection = realUrl.openConnection();
			HttpURLConnection httpURLConnection = (HttpURLConnection) connection;
			// 设置 header
			for (String key : header.keySet()) {
				httpURLConnection.setRequestProperty(key, header.get(key));
			}
			// 设置请求 body
			httpURLConnection.setDoOutput(true);
			httpURLConnection.setDoInput(true);
			out = new PrintWriter(httpURLConnection.getOutputStream());
			// 保存body
			out.print(body);
			// 发送body
			out.flush();
			if (HttpURLConnection.HTTP_OK != httpURLConnection.getResponseCode()) {
				logger.error("fail message","Http 请求失败，状态码：" + httpURLConnection.getResponseCode());
				return null;
			}

			// 获取响应body
			in = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			return null;
		}
		return result;
	}

	public static String discernImage(byte[] img) throws UnsupportedEncodingException {
		logger.info("开始图片识别");
		Map<String, String> header = buildHttpHeader();
		String imageBase64 = new String(Base64.encodeBase64(img), "UTF-8");
		String result = doPostRecognozeWords(WEBOCR_URL, header, "image=" + URLEncoder.encode(imageBase64, "UTF-8"));

		//解析获取识别结果
		String recognition = getRecognition(result);

		return recognition;
	}



	private static String getRecognition(String result) {
		logger.info("图片请求讯飞api返回结果:"+result);
		JSONObject jsonObject = new JSONObject(result);
		String code = jsonObject.getString("code");
		if (code.equals("0")){
			logger.info("请求返回结果 0");
			try {
				String string =  jsonObject.getJSONObject("data").getJSONArray("block").getJSONObject(0).getJSONArray("line").getJSONObject(0).getJSONArray("word").getJSONObject(0).getString("content");
				return string;
			} catch (JSONException e) {
				logger.error("error message",e);
			}
			return null;
		}else{
			logger.error("请求异常,请参照返回结果日志处理");
			return null;
		}

	}

	/**
	 * 组装http请求头
	 */
	private static Map<String, String> buildHttpHeader() throws UnsupportedEncodingException {
		logger.info("组装请求头");
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
