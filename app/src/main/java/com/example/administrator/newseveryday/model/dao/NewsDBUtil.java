package com.example.administrator.newseveryday.model.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.administrator.newseveryday.entity.News;
import com.example.administrator.newseveryday.entity.NewsInfo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * Created by Administrator on 2016/7/13.
 */
public class NewsDBUtil {
    private DBHelper dbHelper;
    private static NewsDBUtil sInstance;
    private SQLiteDatabase database;
    private static ArrayList<News> likeList = new ArrayList<>();//喜爱列表
    private static final String LIST_FILE_PATH = "/data/data/com.example.administrator.NewsEveryDay/list";

    private NewsDBUtil(Context context) {
        dbHelper = new DBHelper(context);
        database = dbHelper.getWritableDatabase();

    }

    public static NewsDBUtil getsInstance(Context context) {
//        if (sInstance == null) {
//            synchronized (NewsDBUtil.class) {
        if (sInstance == null) {
            sInstance = new NewsDBUtil(context);
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
        database.insert(DBHelper.TABLE_NAME, null, values);
    }


    public ArrayList<News> getNewsList() {
        ArrayList<News> list = new ArrayList<>();
        Cursor cursor = database.query(DBHelper.TABLE_NAME, null, null, null, null, null, null);
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

    //新加的


    public static ArrayList<News> getLikeList(int position) {
        likeList.clear();
        File f = new File(LIST_FILE_PATH + "likeList");
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
            while (true) {
                NewsInfo info = (NewsInfo) ois.readObject();
                if (info != null) {
                    likeList.add(info.getData().get(position));
                } else {
                    break;
                }
            }
            ois.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return likeList;
    }

    public static void writeLikeInfo(News news) {
        for (News news1 : likeList) {
            if (news1.getLink() == news.getLink()) {
                likeList.remove(news1);
                break;
            }
        }
        likeList.add(news);
        File f = new File(LIST_FILE_PATH + "likeList");
        try {
            f.delete();
            if (!f.exists()) {
                f.createNewFile();
            }
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f));
            for (News news1 : likeList) {
                oos.writeObject(news1);
            }
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void removeLikeINfo(News news) {
        for (News news1 : likeList) {
            if (news1.getLink() == news.getLink()) {
                likeList.remove(news1);
                break;
            }
        }
        File f = new File(LIST_FILE_PATH + "likeList");
        try {
            f.delete();
            if (!f.exists()) {
                f.createNewFile();
            }
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f));
            for (News news1 : likeList) {
                oos.writeObject(news1);
            }
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
