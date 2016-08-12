package com.example.administrator.newseveryday.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.administrator.newseveryday.Activity_Guide;
import com.example.administrator.newseveryday.R;
import com.example.administrator.newseveryday.base.LoveActivityAdapter;
import com.example.administrator.newseveryday.entity.News;
import com.example.administrator.newseveryday.model.dao.NewsDBUtil1;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/8/5.
 */
public class Activity_love extends Activity {
    private ArrayList<News> likeList ;
    private RecyclerView recyclerView;
    Context context;
    RequestQueue mRequestQueue;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_love);
        likeList= NewsDBUtil1.getsInstance(getBaseContext()).getNewsList();
        mRequestQueue = Volley.newRequestQueue(getBaseContext());
        recyclerView = (RecyclerView) findViewById(R.id.love);
        getStringResult();
    }
    public void getStringResult() {

        String url = "http://118.244.212.82:9092/newsClient/news_list?ver=1&subid=1&dir=20&nid=1&stamp=20140321&cnt=20";
        //1请求方式    2网址    3联网成功响应的接口
        StringRequest request = new StringRequest(url, new Response.Listener<String>() {
            @Override
            //  3联网成功响应,接收到的数据s
            public void onResponse(String s) {
                LoveActivityAdapter adapter = new LoveActivityAdapter(context, likeList, mRequestQueue, recyclerView);
                recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));
                recyclerView.setAdapter(adapter);
                adapter.setOnItemClickListener(new LoveActivityAdapter.ItemClick() {
                    @Override
                    public void setOnItemClick(int position) {
                        Intent intent = new Intent(Activity_love.this, Activity_Guide.activity_detail.class);
                        intent.putExtra("url",likeList.get(position).getLink());
                        startActivity(intent);
                    }
                });

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                LoveActivityAdapter adapter = new LoveActivityAdapter(context, likeList, mRequestQueue, recyclerView);
                recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));
                recyclerView.setAdapter(adapter);
            }
        });
        mRequestQueue.add(request);//将请求放入请求队列
    }


}

