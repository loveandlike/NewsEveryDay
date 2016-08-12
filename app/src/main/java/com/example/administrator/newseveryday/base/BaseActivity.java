package com.example.administrator.newseveryday.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

/**
 * Created by Administrator on 2016/7/12.
 */
public abstract class BaseActivity extends Activity {
    //屏幕的宽度和高度
    protected int screenW, screenH;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        screenH = getResources().getDisplayMetrics().heightPixels;//获取屏幕高度像素
        screenW = getResources().getDisplayMetrics().widthPixels;
    }


    //..........跳转页面的方法。。。。。。。。。。//
    public void jumpActivity(Class<?> tClass, Bundle bundle) {
        Intent intent = new Intent(this, tClass);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    public void jumpActivity(Class<?> tClass) {
        this.jumpActivity(tClass, null);
    }

    public void jumpActivity(String action, Bundle bundle) {
        Intent intent = new Intent(action);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    //。..........................toast............................、//
    private Toast toast;

    //显示指定的字符串
    public void showToast(String msg) {
        if (toast != null) {
            toast.cancel();
        }
        toast = Toast.makeText(this, msg, Toast.LENGTH_LONG);
        toast.show();
    }

    //显示资源id对应的字符串
    public void showToast(int resId) {
        if (toast != null) {
            toast.cancel();
        }
        toast = Toast.makeText(this, resId, Toast.LENGTH_LONG);
        toast.show();
    }
}
