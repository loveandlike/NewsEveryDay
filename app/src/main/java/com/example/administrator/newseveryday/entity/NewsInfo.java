package com.example.administrator.newseveryday.entity;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/7/19.
 */
public class NewsInfo {
    private String message;
    private int status;//状态
    private ArrayList<News> data;

    public NewsInfo(ArrayList<News> data, String message, int status) {
        this.data = data;
        this.message = message;
        this.status = status;
    }

    public NewsInfo() {
    }

    public ArrayList<News> getData() {
        return data;
    }

    public void setData(ArrayList<News> data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
