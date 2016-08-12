package com.example.administrator.newseveryday.base;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.newseveryday.R;
import com.example.administrator.newseveryday.RecyclerActivity;

import java.util.ArrayList;
import java.util.zip.Inflater;

/**
 * Created by Administrator on 2016/7/25.
 */
public class DoubleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
//
//    ArrayList<String> list=new ArrayList<String>();
//    ArrayList<String> datas=new ArrayList<>();
//    private ItemClick click;

//    public interface ItemClick {
//        void onItemClick(int position, View view);
//    }
//    private ItemClick listener;
//
//    public void setListener(ItemClick listener) {
//        this.listener = listener;
//    }


   class doubleAda  extends RecyclerView.ViewHolder{
          TextView textView;
       public doubleAda(View itemView) {
           super(itemView);
           textView= (TextView) itemView.findViewById(R.id.item_lianxi);
       }
   }

    class   doubleAda1 extends  RecyclerView.ViewHolder{
              TextView textView1;
        public doubleAda1(View itemView) {
            super(itemView);
            textView1= (TextView) itemView.findViewById(R.id.tv_lianxi);
        }
    }
//决定不同的
    private final int DAN_SHU = 1001;
    private final int SHUANG_SHU = 1002;

    @Override
    public int getItemViewType(int position) {
        if (position % 2== 0) {
            return SHUANG_SHU;
        }else {
            return DAN_SHU;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lianxi,parent,false);
        View view1=LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_iten_lianxi,parent,false);
         switch (viewType){
             case DAN_SHU :
                return new doubleAda(view);
             case SHUANG_SHU:
                 return new doubleAda1(view1);
             default:
                 return null;
         }
    }
    //往VIewholder添加数据
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof doubleAda){
            ((doubleAda) holder).textView.setText("李二汪说：");
        }else if (holder instanceof doubleAda1){
            ((doubleAda1) holder).textView1.setText("王大汪大骂：");
        }

    }

    @Override
    public int getItemCount() {
        return 30;
    }
}
