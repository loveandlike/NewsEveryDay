package com.example.administrator.newseveryday.common;

/**
 * Created by Administrator on 2016/7/12.
 */
public interface CommonUtil {
    //服务器网址
    String NET_PATH="http://118.244.212.82:9092/newsClient";
    //请求中的用户名
    String SHARED_USER_NAME="user_name";
    //请求中的密码
    String SHARED_USER_PWD="user_password";
    //保存在sharedprefence中是不是第一次登陆
    String SHARED_IS_FIRST_RUN="is_first_run";
    //sharedprefence的路径
    String SHARED_PATH="news_shared";
}
