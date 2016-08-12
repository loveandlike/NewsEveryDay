package com.example.administrator.newseveryday.model.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2016/7/13.
 */
public class DBHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "news_db";
    public static final String TABLE_NAME = "table_news";

    public DBHelper(Context context) {
        super(context, DB_NAME, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(" create table if not exists " + TABLE_NAME + " ( icon varchar(255) , link varchar(255) , nid int , stamp varchar(255) , summary varchar(255) , title varchar(255) , type int ) ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {




    }
}
