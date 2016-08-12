package com.example.administrator.newseveryday;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.administrator.newseveryday.base.DoubleAdapter;
import com.example.administrator.newseveryday.base.SingleAdapter;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/7/25.
 */
public class lianxi extends Activity {
    RecyclerView recyclerView;
    ArrayList<String> data = new ArrayList<String>();
    Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lianxi);
        button= (Button) findViewById(R.id.bt_recyclerview);
        recyclerView = (RecyclerView) findViewById(R.id.recyClear);
        for (int i = 0; i < 20; i++) {
            data.add("李二狗" + i);
        }

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
//        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));
//       DoubleAdapter adapter=new DoubleAdapter();
        final SingleAdapter adapter = new SingleAdapter(data, recyclerView);
        recyclerView.setAdapter(adapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                data.add("添加的数据");
                adapter.notifyItemRangeChanged(0, data.size());//重新排序，否则越界

            }
        });
        adapter.setOnItemClickListener(new SingleAdapter.ItemClick() {
            @Override
            public void setOnItemClick(int position) {
                Snackbar.make(recyclerView, " 这是第" + position + "只二汪", Snackbar.LENGTH_SHORT).show();
//                Toast.makeText(lianxi.this, "这是第" + position + "只二汪", Toast.LENGTH_SHORT).show();
                data.remove(position);
//              adapter.notifyDataSetChanged();
                adapter.notifyItemRemoved(position);//动画效果
                adapter.notifyItemRangeChanged(position, data.size());//重新排序，否则越界
            }
        });
        getData();
    }

    public void getData() {
    }
}

