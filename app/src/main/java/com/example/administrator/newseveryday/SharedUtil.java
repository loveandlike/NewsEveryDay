package com.example.administrator.newseveryday;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.administrator.newseveryday.common.CommonUtil;

/**
 * Created by Administrator on 2016/7/19.
 */
public class SharedUtil {
    private static SharedUtil sInstance;
    private Context context;
    private SharedPreferences preferences;

    public SharedUtil(Context context) {
        this.context = context;
        preferences = context.getSharedPreferences(CommonUtil.SHARED_IS_FIRST_RUN,Context.MODE_PRIVATE);

    }

    public static SharedUtil getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new SharedUtil(context);
        }
        return sInstance;
    }
    public void putFirstRun(){
        //获取编译器，放入布尔值，再提交
        preferences.edit().putBoolean("isFirstRun",true).commit();
    }
    public boolean getFirstRun(){
        return preferences.getBoolean("isFirstRun",false);
    }

}
