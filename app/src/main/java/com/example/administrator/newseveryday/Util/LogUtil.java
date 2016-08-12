package com.example.administrator.newseveryday.Util;

import android.util.Log;

/**
 * Created by Administrator on 2016/7/12.
 */
public class LogUtil {
    public static final String TAG = "新闻随意看,看看看看看看看看";
    public static boolean isDeBug = true;

    public static void d(String tag, String msg) {
        if (isDeBug) {
            Log.d(tag, msg);
        }
    }
    public static void d (String msg){
        if (isDeBug){
            Log.d(LogUtil.TAG,msg);
        }
    }
}
