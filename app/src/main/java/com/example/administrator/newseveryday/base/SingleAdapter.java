package com.example.administrator.newseveryday.base;

import android.content.Context;
import android.content.SyncRequest;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.newseveryday.R;
import com.example.administrator.newseveryday.RecyclerActivity;
import com.example.administrator.newseveryday.lianxi;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/7/25.
 */
public class SingleAdapter extends RecyclerView.Adapter<SingleAdapter.ItemViewHolder> {
    private List<Integer> heights;
    private View view;
    ArrayList<String> data;
    RecyclerView recyclerView;
    public SingleAdapter(ArrayList<String> data,RecyclerView recyclerView) {
        this.data = data;
        this.recyclerView=recyclerView;

    }


    //    private void getRandomHeight(List<String> lists) {//得到随机item的高度
//        heights = new ArrayList<>();
//        for (int i = 0; i < lists.size(); i++) {
//            heights.add((int) (200 + Math.random() * 400));
//        }
//    }
    public class ItemViewHolder extends RecyclerView.ViewHolder {
        private TextView tv;
        public ItemViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.tv_lianxi);
        }
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_iten_lianxi, null);
        return new ItemViewHolder(view);
    }

    //往VIewholder添加数据
    @Override
    public void onBindViewHolder(final ItemViewHolder holder, final int position) {

        holder.tv.setText(data.get(position));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.setOnItemClick(position);
//                    listener.setOnItemClick();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public interface ItemClick {
        //        void setOnItemClick();
        void setOnItemClick(int position);
    }

    private ItemClick listener;

    public void setOnItemClickListener(ItemClick listener1) {
        this.listener = listener1;
    }
}
