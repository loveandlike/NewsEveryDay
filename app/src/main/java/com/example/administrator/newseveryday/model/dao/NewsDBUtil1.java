package com.example.administrator.newseveryday.model.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.administrator.newseveryday.entity.News;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/7/13.
 */
public class NewsDBUtil1 {
    private DBHelper1 dbHelper1;
    private static NewsDBUtil1 sInstance;
    private SQLiteDatabase database;
    private static ArrayList<News> likeList = new ArrayList<>();//喜爱列表

    private NewsDBUtil1(Context context) {
        dbHelper1 = new DBHelper1(context);
        database = dbHelper1.getWritableDatabase();

    }

    public static NewsDBUtil1 getsInstance(Context context) {
//        if (sInstance == null) {
//            synchronized (NewsDBUtil.class) {
        if (sInstance == null) {
            sInstance = new NewsDBUtil1(context);
//                }
//            }
        }
        return sInstance;
    }

    //增删改查
    public void insertNews(News news) {//增加

        ContentValues values = new ContentValues();
        values.put("summary", news.getSummary());
        values.put("icon", news.getIcon());
        values.put("stamp", news.getStamp());
        values.put("title", news.getTitle());
        values.put("nid", news.getNid());
        values.put("link", news.getLink());
        values.put("type", news.getType());
        database.insert(DBHelper1.TABLE_NAME, null, values);
    }


    public ArrayList<News> getNewsList() {
        ArrayList<News> list = new ArrayList<>();
        Cursor cursor = database.query(DBHelper1.TABLE_NAME, null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            String summary = cursor.getString(cursor.getColumnIndex("summary"));
            String icon = cursor.getString(cursor.getColumnIndex("icon"));
            String stamp = cursor.getString(cursor.getColumnIndex("stamp"));
            String title = cursor.getString(cursor.getColumnIndex("title"));
            String link = cursor.getString(cursor.getColumnIndex("link"));
            int nid = cursor.getInt(cursor.getColumnIndex("nid"));
            int type = cursor.getInt(cursor.getColumnIndex("type"));

            list.add(new News(icon, link, nid, stamp, summary, title, type));
        }
        return list;
    }


    public void removeData() {
    }

    public void updateData() {
    }


}
