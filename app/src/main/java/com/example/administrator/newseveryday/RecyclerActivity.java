package com.example.administrator.newseveryday;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/** 回调接口，增加删除item
 * Created by Administrator on 2016/7/22.
 */
public class RecyclerActivity extends Activity {
    RecyclerView recyclerView;
    ArrayList<String> datas = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);
        recyclerView = (RecyclerView) findViewById(R.id.recyClear);
//        recyclerView.setLayoutManager(new GridLayoutManager(this,3, GridLayoutManager.VERTICAL,false));
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        for (int i = 0; i < 30; i++) {
            datas.add("李寡妇" + (i + 1));
        }
        final MyRecyclerAdapter adapter = new MyRecyclerAdapter();
        recyclerView.setAdapter(adapter);


        adapter.setListener(new ItemClick() {
            @Override
            public void onItemClick(int position, View view) {
                datas.add(position, "再加一个");
                adapter.notifyItemInserted(position);
            }
        });
        new ItemClick() {
            @Override
            public void onItemClick(int position, View view) {

            }
        };
    }





    //回调接口
    public interface ItemClick {
        void onItemClick(int position, View view);
    }

    class MyRecyclerAdapter extends RecyclerView.Adapter<MyViewHolder> {
        private ItemClick listener;

        public void setListener(ItemClick listener) {
            this.listener = listener;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(getBaseContext()).inflate(R.layout.item_recycler, parent, false);

            return new MyViewHolder(v);
        }

        @Override
        public void onBindViewHolder(final MyViewHolder holder, final int position) {


            holder.v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        listener.onItemClick(position, holder.v);
                    }
                }
            });

            holder.tv_item_recycler.setText(datas.get(position));
//                 holder.iv_item_recycler.setImageResource(R.drawable.wallpaper_5250676);
        }

        @Override
        public int getItemCount() {
            return datas.size();
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_item_recycler;
        TextView tv_item_recycler;
        View v;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.v = itemView;
            tv_item_recycler = (TextView) itemView.findViewById(R.id.tv_item_recycler);
            iv_item_recycler = (ImageView) itemView.findViewById(R.id.iv_item_recycler);

        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
