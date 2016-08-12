package com.example.administrator.newseveryday;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/7/21.
 */
public class RefreshActivity extends Activity {
    SwipeRefreshLayout swipe_Refresh;
    ListView lv_fresh;
    ArrayList<String> datas = new ArrayList<>();
    MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refresh);
        swipe_Refresh = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh);
        lv_fresh = (ListView) findViewById(R.id.lv_fresh);

        adapter = new MyAdapter();
        lv_fresh.setAdapter(adapter);
        swipe_Refresh.setColorSchemeColors(0xffff8c00);//最多四种颜色
        swipe_Refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Snackbar.make(swipe_Refresh, "刷新中", Snackbar.LENGTH_SHORT).show();
                for (int i = 0; i < 10; i++) {
                    datas.add("这是第" + i + "个");
                }
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

    class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return datas.size();
        }

        @Override
        public Object getItem(int i) {
            return datas.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            TextView textView = new TextView(getBaseContext());
            textView.setText(datas.get(i));
            textView.setTextColor(0xff000000);
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 30);

            return textView;
        }
    }
}
