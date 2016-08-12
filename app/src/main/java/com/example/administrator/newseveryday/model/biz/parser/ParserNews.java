package com.example.administrator.newseveryday.model.biz.parser;

import com.example.administrator.newseveryday.entity.News;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/7/13.     json解析
 */
public class ParserNews {
    public static ArrayList<News> getNewsINfo(String jsonStr) {
        ArrayList<News> list = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(jsonStr);
            JSONArray array = jsonObject.getJSONArray("data");
            for (int i = 0; i < array.length(); i++) {
                JSONObject object = array.getJSONObject(i);
                String summary = object.getString("summary");
                String title = object.getString("title");
                String icon = object.getString("icon");
                String link = object.getString("link");
                String stamp = object.getString("stamp");
                int nid = object.getInt("nid");
                int type = object.getInt("type");
                list.add(new News(icon, link, nid, stamp, summary, title, type));


            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }
}
