package com.str.net;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class MyHttpClient {
    public static void main(String[] args) throws IOException {
        URL url = new URL("http://www.jtthink.com/");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setUseCaches(false);
        conn.setConnectTimeout(5000);
        // 设置http头
        conn.setRequestProperty("Accept", "*/*");
        conn.setRequestProperty("User-Agent", "Mozilla/5.0");

        // 连接并发送HTTP请求
        conn.connect();

        // 判断HTTP响应是否为200
        if (conn.getResponseCode() != 200) {
            throw new RuntimeException("bad response");
        }

        // 获取所有响应Header
        /*Map<String, List<String>> map = conn.getHeaderFields();
        for (String key : map.keySet()) {
            System.out.println(key + ": " + map.get(key));
        }*/

        // 获取响应内容
        InputStream input =conn.getInputStream();

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(input, StandardCharsets.UTF_8));
        for (int i = 0; i < 50; i++) {
            String resp = reader.readLine();
            System.out.println(resp);
        }

        /*byte[] buffer = new byte[1024];
        input.read(buffer);
        System.out.print(Arrays.toString(buffer));*/
    }
}
