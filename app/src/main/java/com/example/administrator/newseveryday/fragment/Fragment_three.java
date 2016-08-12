package com.example.administrator.newseveryday.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.administrator.newseveryday.Activity_Guide;
import com.example.administrator.newseveryday.R;
import com.example.administrator.newseveryday.base.MainAdapter1;
import com.example.administrator.newseveryday.base.MainAdapter_three;
import com.example.administrator.newseveryday.entity.News;
import com.example.administrator.newseveryday.entity.NewsInfo;
import com.example.administrator.newseveryday.model.dao.NewsDBUtil;
import com.google.gson.Gson;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/7/27.
 */
public class Fragment_three extends Fragment{
    private ArrayList<News> newsList;

    MainAdapter_three adapter;
    MainAdapter1 mainAdapter1;
    RequestQueue mRequestQueue;
    private ImageButton title_bar;
    private RecyclerView recyclerView;
    SwipeRefreshLayout swipe_Refresh;
    private Context context;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_three,container,false);
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = (RecyclerView) view.findViewById(R.id.main_recyclerview);
        swipe_Refresh = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh);
        title_bar = (ImageButton) view.findViewById(R.id.title_bar);
        //获取数据
        mRequestQueue = Volley.newRequestQueue(getContext());
        swipe_Refresh.setColorSchemeResources(R.color.se, R.color.se1, R.color.colorAccent);
        swipe_Refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Snackbar.make(swipe_Refresh, "刷新中", Snackbar.LENGTH_SHORT).show();
                getStringResult();//获取数据
                handler.sendEmptyMessageDelayed(0, 3000);
            }
        });
        getStringResult();
    }
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            adapter.notifyDataSetChanged();//刷新界面
            swipe_Refresh.setRefreshing(false);
        }
    };

    public void getStringResult() {

        String url = "http://118.244.212.82:9092/newsClient/news_list?ver=1&subid=1&dir=20&nid=1&stamp=20140321&cnt=20";

        StringRequest request = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                final NewsInfo info = new Gson().fromJson(s, NewsInfo.class);

                adapter=new MainAdapter_three(context,info.getData(),mRequestQueue,recyclerView);
                recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
                recyclerView.setAdapter(adapter);
                //.................超链接。跳转...................//
                adapter.setOnItemClickListener(new MainAdapter_three.ItemClick() {
                    @Override
                    public void setOnItemClick(int position) {
                        Intent intent = new Intent(getContext(), Activity_Guide.activity_detail.class);
                        intent.putExtra("url", newsList.get(position).getLink());
                        intent.putExtra("k",position);
                        intent.putExtra("icon",newsList.get(position).getIcon());
                        intent.putExtra("title",newsList.get(position).getTitle());
                        intent.putExtra("summary",newsList.get(position).getSummary());
                        startActivity(intent);
                    }
                });
                newsList = info.getData();
            }




            //...................当发生错误的时候....................//
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                NewsDBUtil dbUtil = NewsDBUtil.getsInstance(getContext());
                mainAdapter1 = new MainAdapter1(context, dbUtil,mRequestQueue, recyclerView);
                recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
                recyclerView.setAdapter(mainAdapter1);
            }
        });
        mRequestQueue.add(request);//将请求放入请求队列
    }

}
