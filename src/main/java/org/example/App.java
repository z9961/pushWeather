package org.example;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Hello world!
 */
public class App {

    public static String main(KeyValueClass kv) {
        String qurl = "https://tianqiapi.com/api?version=v6&appid=25174476&appsecret=ToEVL60S&cityid=101120211";
        Request request = new Request.Builder().url(qurl)
                .get().build();
        OkHttpClient client = new OkHttpClient().newBuilder().connectTimeout(50, TimeUnit.SECONDS)
                .readTimeout(120, TimeUnit.SECONDS).build();
        try {
            ResponseBody responseBody = client.newCall(request).execute().body();
            String string = responseBody.string();
            JSONObject json = JSON.parseObject(string);
            StringBuilder s = new StringBuilder();
            s.append("今天").append(json.get("week")).append(",").append(json.get("wea")).append(",")
                    .append("最低").append(json.get("tem2")).append("℃,最高").append(json.get("tem")).append("℃,").append(json.get(
                    "win")).append(json.get("win_speed")).append(",").append(json.get("air_tips").toString().replace(
                    "，", ","));

            qurl = "https://push.xuthus.cc/group/e5327555e79e21682592c46f5d9f2140?c=" + s;
            request = new Request.Builder().url(qurl)
                    .get().build();
            client = new OkHttpClient().newBuilder()
                    .connectTimeout(50, TimeUnit.SECONDS)
                    .readTimeout(120, TimeUnit.SECONDS).build();
            try {
                Response execute = client.newCall(request).execute();
                return "success";
            } catch (IOException e) {
                e.printStackTrace();
            }
            return s.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "error";
    }
}
