package com.example.administrator.newseveryday.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.administrator.newseveryday.R;
import com.example.administrator.newseveryday.Util.NewShareUtil;
import com.example.administrator.newseveryday.base.TableAndPagerAdapter;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/7/27.
 */
public class Fragment_one extends Fragment {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ArrayList<Fragment> fragments;
    private ArrayList<String> titles;
    private Fragment_two fragment_two;
    Bundle bundle;
    TableAndPagerAdapter adapter;
    Button button;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_one, container, false);
        viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        tabLayout = (TabLayout) view.findViewById(R.id.table);
        button = (Button) view.findViewById(R.id.but_ta);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AddActivity.class);
                //    intent     请求码
                startActivityForResult(intent, 110);
            }
        });
        initData();
        updateTitle();

        //适配器设置
        viewPager.setAdapter(adapter);
        tabLayout.setTabsFromPagerAdapter(adapter);//共用适配器
        tabLayout.setupWithViewPager(viewPager);//一起滚动
        viewPager.setOffscreenPageLimit(4);//预加载
        return view;
    }


    public void initData() {
        titles = new ArrayList<>();
        fragments = new ArrayList<>();
        titles.add("互联网");
        titles.add("星座");
        titles.add("热点");
        for (int i = 0; i < titles.size(); i++) {
            fragment_two = new Fragment_two();
            bundle = new Bundle();
            bundle.putString("title", titles.get(i));
            fragment_two.setArguments(bundle);
            fragments.add(fragment_two);
        }
        adapter = new TableAndPagerAdapter(getActivity().getSupportFragmentManager(), fragments, titles);
    }

    //添加标签 ，集合改变
    public void updateTitle() {
        String title = NewShareUtil.getInstance(getContext()).getSpData();
            titles.add(title);
            fragment_two = new Fragment_two();
            bundle = new Bundle();
            bundle.putString("title", title);
            fragment_two.setArguments(bundle);
            fragments.add(fragment_two);
            adapter.notifyDataSetChanged();
    }

    //回调接口
    interface onclick {
        void setOnclic(String name);
    }

    private onclick lick;

    public void setonclicklistenner(onclick lick) {
        this.lick = lick;
    }
}
