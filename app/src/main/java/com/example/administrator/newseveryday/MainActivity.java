package com.example.administrator.newseveryday;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

//import com.example.administrator.newseveryday.base.MainListViewAdapter;
import com.example.administrator.newseveryday.base.MainAdapter;
import com.example.administrator.newseveryday.entity.News;
import com.example.administrator.newseveryday.entity.NewsInfo;
import com.google.gson.Gson;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private ArrayList<News> newsList;
    //    private ListView lv_main;
//    private MainListViewAdapter adapter;
    MainAdapter adapter;
    RequestQueue mRequestQueue;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private ImageButton title_bar;
    private RecyclerView recyclerView;
    SwipeRefreshLayout swipe_Refresh;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hjc);
//        lv_main = (ListView) findViewById(R.id.listview);
        recyclerView = (RecyclerView) findViewById(R.id.main_recyclerview);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.tb_main);
//        toolbar.setTitle("新闻资讯");
//        setSupportActionBar(toolbar);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_main);
        navigationView = (NavigationView) findViewById(R.id.nav_main);
        swipe_Refresh = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh);
        title_bar = (ImageButton) findViewById(R.id.title_bar);

        navigationView.setNavigationItemSelectedListener(this);
        //参数一 当前activity   参数二 drawerlayout
        //参数三toobar          参数四，五     划出和划入的文本说明
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.app_name, R.string.app_name);
//        toggle.syncState();//将开关同步设置
//        drawerLayout.addDrawerListener(toggle);

        //获取数据
        mRequestQueue = Volley.newRequestQueue(getBaseContext());
        getStringResult();
        title_bar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), RecyclerActivity.class);
                startActivity(intent);
            }
        });
//        adapter.setOnItemClickListener(new MainAdapter.ItemClick() {
//            @Override
//            public void setOnItemClick() {
//                Intent intent = new Intent(getBaseContext(), activity_detail.class);
//                intent.putExtra("url", newsList.get(i).getLink());
//                startActivity(intent);
//            }
//        });


    }


    int i = 1;

    public void getStringResult() {
        String url = "http://118.244.212.82:9092/newsClient/news_list?ver=1&subid=1&dir=" + i + "&nid=1&stamp=20140321&cnt=20";
        //1请求方式    2网址    3联网成功响应的接口
        StringRequest request = new StringRequest(url, new Response.Listener<String>() {
            @Override
            //  3联网成功响应,接收到的数据s
            public void onResponse(String s) {
                final NewsInfo info = new Gson().fromJson(s, NewsInfo.class);

//                adapter = new MainListViewAdapter(getBaseContext(), info.getData(), mRequestQueue);
                adapter = new MainAdapter(getBaseContext(), info.getData(), mRequestQueue, recyclerView);

                recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

                recyclerView.setAdapter(adapter);

//                lv_main.setAdapter(adapter);

                newsList = info.getData();
                //刷新界面
                swipe_Refresh.setColorSchemeResources(R.color.se, R.color.se1, R.color.colorAccent);
                swipe_Refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        Snackbar.make(swipe_Refresh, "刷新中", Snackbar.LENGTH_SHORT).show();

                          getStringResult();//获取数据

                        handler.sendEmptyMessageDelayed(0, 3000);

                    }
                });
            }

            Handler handler = new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    super.handleMessage(msg);
                    adapter.notifyDataSetChanged();
                    swipe_Refresh.setRefreshing(false);
                }
            };

            //当发生错误的时候
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.d("debug", "报告陛下，有异常发生");

            }
        });
        mRequestQueue.add(request);//将请求放入请求队列
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        String str = "";
        switch (item.getItemId()) {
            case R.id.menu_first:
                str = "今晚去三宫看看";
                break;
            case R.id.menu_second:
                str = "今晚去六院找乐子";
                break;
            case R.id.menu_third:
                str = "今晚去找七十二妃玩玩";
                break;
            case R.id.other_first:
                str = "今晚去找嫔妃赏月";
                break;
            case R.id.other_second:
                str = "今晚去找才人吟诗";
                break;
        }
        Snackbar.make(navigationView, str, Snackbar.LENGTH_SHORT).setAction("myaction", null).show();

        drawerLayout.closeDrawer(GravityCompat.START);

        return true;
    }

    @Override
    public void onBackPressed() {
        //如果菜单是打开状态，按下返回键 关闭菜单
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.isDrawerOpen(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
