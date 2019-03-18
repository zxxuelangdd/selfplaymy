package othertest;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.HashMap;

/**
 * @program: selfplay
 * @description: 别的测试
 * @author: zx
 * @create: 2018-08-10 16:39
 **/
public class LittleTest {
    public static void main(String[] args) throws IOException {
        HashMap<String, String> data = new HashMap<>();

        data.put("if-none-match", "4ce96f67fd9872a748752d2c8dabe550;gzip");
        data.put("x-requested-with", "XMLHttpRequest");
        data.put("accept-language", "zh-CN,zh;q=0.9");
        data.put("if-none-match-", "55b03-1f0b7ba35853a4e65c03981509001597");
        data.put("Accept-Encoding", " gzip, deflate, br");
        data.put("Cookie", " _ga=GA1.3.562965941.1537172212; _gid=GA1.3.817641223.1537172212; SPC_IA=-1; SPC_EC=-; SPC_U=-; SPC_F=Plp1xs4jzYer7T42RxjGnoCXdiJYjP1C; REC_T_ID=13f423ce-ba52-11e8-98ec-52540035657b; SPC_T_ID=\"2yAw1GerCKQ6hjLqTxC7VLVnNlTMDWlTnP8H9TEcXDfrj7T/58fYt4Uuyfhp+XhMs4cAKhtXfFfvHLC+R2S2OuWCmeIzdPGV92t2rl5f4as=\"; SPC_SI=nidgm0a56xp7o4gbk1zcir2kfaoztnwp; SPC_T_IV=\"97D4h8KXN/WYUx4Veo/PEA==\"; csrftoken=9MNDuJb3CZOPsjpvTfE92mPCWcNhHh22; language=en; _gcl_au=1.1.97441554.1537233156");


        String url = "https://shopee.com.my/api/v2/search_items/?by=pop&limit=50&match_id=2280&newest=50&order=desc&page_type=search";
        String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/69.0.3497.81 Safari/537.36";
        Document document = Jsoup.connect(url).data(data).userAgent(userAgent).get();
        System.out.println(document);

/*        HttpClient httpClient = new HttpClient();
        GetMethod getMethod = new GetMethod(url);
        httpClient.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
        getMethod.addRequestHeader("if-none-match","4ce96f67fd9872a748752d2c8dabe550;gzip");
        getMethod.addRequestHeader("x-requested-with","XMLHttpRequest");
        getMethod.addRequestHeader("accept-language","zh-CN,zh;q=0.9");
        getMethod.addRequestHeader("if-none-match-","55b03-1f0b7ba35853a4e65c03981509001597");
        getMethod.addRequestHeader("Accept-Encoding"," gzip, deflate, br");
        getMethod.addRequestHeader("Cookie"," _ga=GA1.3.562965941.1537172212; _gid=GA1.3.817641223.1537172212; SPC_IA=-1; SPC_EC=-; SPC_U=-; SPC_F=Plp1xs4jzYer7T42RxjGnoCXdiJYjP1C; REC_T_ID=13f423ce-ba52-11e8-98ec-52540035657b; SPC_T_ID=\"2yAw1GerCKQ6hjLqTxC7VLVnNlTMDWlTnP8H9TEcXDfrj7T/58fYt4Uuyfhp+XhMs4cAKhtXfFfvHLC+R2S2OuWCmeIzdPGV92t2rl5f4as=\"; SPC_SI=nidgm0a56xp7o4gbk1zcir2kfaoztnwp; SPC_T_IV=\"97D4h8KXN/WYUx4Veo/PEA==\"; csrftoken=9MNDuJb3CZOPsjpvTfE92mPCWcNhHh22; language=en; _gcl_au=1.1.97441554.1537233156");
        int i = httpClient.executeMethod(getMethod);
        String responseBodyAsString = getMethod.getResponseBodyAsString();
        System.out.println("============================="+i);
        System.out.println(responseBodyAsString);*/





       /* String s="select from asdfa from dfsdfg ";
        int sdf = s.compareTo("sdf");
        Integer l=1;
        int i = l.compareTo(2);
        System.out.println(i);
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        numbers.replaceAll(a->a*a);

        System.out.println(numbers);
        ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();
        for (int j = 0; j <100; j++) {
           map.put(String.valueOf(j),j);
        }
        Optional<Integer> maxValue =
                Optional.of(map.reduceValues(1, Integer::max));
        System.out.println("maxValue    "+maxValue.get());*/

    }
}
