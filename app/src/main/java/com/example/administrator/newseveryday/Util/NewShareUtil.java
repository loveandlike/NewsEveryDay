package com.example.administrator.newseveryday.Util;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.Set;

/**
 * Created by Administrator on 2016/8/2.
 */
public class NewShareUtil {
    private SharedPreferences sp;
    private final String FILE_NAME = "title";
    private Context context;
    //单例模式、
    //1。构造私有化
    //2。提供一个方法给外部实例
    //3。在内部new出自己的实例
    private static NewShareUtil newShareUtil;

    private NewShareUtil(Context context) {
        sp=context.getSharedPreferences(FILE_NAME,Context.MODE_PRIVATE);
    }

    public static NewShareUtil getInstance(Context context) {
        if (newShareUtil == null) {
            newShareUtil = new NewShareUtil(context);
        }
        return newShareUtil;
    }

    public void setSpData(String titles) {
        SharedPreferences.Editor editor=sp.edit();
        editor.putString("titles",titles);
        editor.commit();

    }
//    public void setSpData(String s,ArrayList<String> set) {
//        SharedPreferences.Editor editor=sp.edit();
//        editor.putStringSet(s,set);
//        editor.commit();
//
//    }


    public String getSpData() {

       return sp.getString("titles","");
    }

//    public String getSpData() {
//
//        return sp.getStringSet("","");
//    }
}
