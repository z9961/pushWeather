package org.example;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Hello world!
 */
public class App {
    public static String main(KeyValueClass kv) {
        String qurl = "https://www.qdfd.com.cn/qdweb/realweb/fh/FhHouseDetail.jsp?houseID=35000006760632";
        Request request = new Request.Builder().url(qurl)
                .get().build();
        OkHttpClient client = new OkHttpClient().newBuilder().connectTimeout(50, TimeUnit.SECONDS).readTimeout(120,
                TimeUnit.SECONDS).build();
        try {
            ResponseBody responseBody = client.newCall(request).execute().body();
            String string = responseBody.string();
            String s1 = string.substring(string.indexOf("状态"));
            s1 = s1.substring(s1.indexOf("st3\">") + 5);

            if (s1.equals("已签约")) {
                Date date = new Date();
                if (date.getHours() < 9 || date.getHours() > 20 || date.getHours() % 3 != 0) {
                    return s1;
                }
            }

            s1 = "正阳府进展：" + s1.substring(0, 3);
            System.out.println(s1);

            qurl = "https://api.htm.fun/api/Wechat/text/?corpid=wwdc1f851ff6ccb4f4&corpsecret" +
                    "=sKX2XcBAP_EL4e1R0mdn9If0g5Lhm5nzyMb1pmK3xrA&agentid=1000002&text=" + s1;
            request = new Request.Builder().url(qurl)
                    .get().build();
            client = new OkHttpClient().newBuilder().connectTimeout(50, TimeUnit.SECONDS).readTimeout(120,
                    TimeUnit.SECONDS).build();
            client.newCall(request).execute();
            return s1;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "error";
    }
}
