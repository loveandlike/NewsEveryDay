package com.example.administrator.newseveryday.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Administrator on 2016/7/12.
 */
public class HttpUtil {
    public static String getUrlContent() {
        StringBuffer sb = new StringBuffer();
        //统一资源定位符
        try {
            URL url = new URL("http://118.244.212.82:9092/newsClient/news_list?ver=1&subid=1&dir=1&nid=1&stamp=20140321&cnt=20");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            //设置超时 超过五秒未连接 则断开连接
//            connection.setConnectTimeout(500000);
            //如果响应码是200 证明连接正确
            if (connection.getResponseCode() == 200) {
                InputStream is = connection.getInputStream();
//                byte[] b = new byte[1024];
//                int len = 0;
//                while ((len = is.read(b)) != -1) {
//                    sb.append(new String(b, 0, len));
//                }
//                is.close();
                BufferedReader br = new BufferedReader(new InputStreamReader(is,"utf-8"));
                String line = " ";
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }
                br.close();
                connection.disconnect(); //断开连接
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}
