package com.example.administrator.newseveryday.base;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.util.LruCache;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.example.administrator.newseveryday.R;
import com.example.administrator.newseveryday.entity.News;

import java.util.ArrayList;
/**
 * Created by Administrator on 2016/8/4.
 */
public class MainAdapter_three extends RecyclerView.Adapter<MainAdapter_three.ItemView>  {
    private Context context;
    private ArrayList<News> datas;
    private RequestQueue mRequestQueue;
    RecyclerView recyclerView;

    public MainAdapter_three(Context context, ArrayList<News> datas, RequestQueue requestQueue, RecyclerView recyclerView) {
        this.context = context;
        this.recyclerView = recyclerView;
        this.datas = datas;
        this.mRequestQueue = requestQueue;
        //获取当前应用总内存的1/8作为缓存空间
        long l = Runtime.getRuntime().totalMemory() / 8;
        mLruCache = new LruCache<>((int) l);

    }

    @Override
    public ItemView onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_news_three, parent, false);
        return new ItemView(view);
    }

    @Override
    public void onBindViewHolder(ItemView holder, final int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.setOnItemClick(position);

            }
        });
        holder.tv_title.setText(datas.get(position).getTitle());
        holder.tv_text.setText(datas.get(position).getSummary());
        setImage(holder.icon, datas.get(position).getIcon());
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }


    public class ItemView extends RecyclerView.ViewHolder {
        TextView tv_title;
        TextView tv_text;
        ImageView icon;

        public ItemView(View itemView) {
            super(itemView);
            tv_text = (TextView) itemView.findViewById(R.id.textView2);
            tv_title = (TextView) itemView.findViewById(R.id.textView1);
            icon = (ImageView) itemView.findViewById(R.id.imageView1);

        }
    }

    public interface ItemClick {
        void setOnItemClick(int position);
    }

    private ItemClick listener;


    public void setOnItemClickListener(ItemClick listener) {
        this.listener = listener;
    }

    //和上面不同是，做了缓存
    public void setImage(final ImageView imageView, final String url) {
        imageView.setTag(url);
        //1， 请求队列    2，缓存
        ImageLoader imageLoader = new ImageLoader(mRequestQueue, new ImageLoader.ImageCache() {
            @Override//取出图片，s是网址
            public Bitmap getBitmap(String s) {
                return mLruCache.get(s);
            }

            @Override//缓存图片，s是网址
            public void putBitmap(String s, Bitmap bitmap) {
                mLruCache.put(s, bitmap);
            }
        });

        //联网获取图片
        imageLoader.get(url, new ImageLoader.ImageListener() {
            @Override//成功的方法
            public void onResponse(ImageLoader.ImageContainer imageContainer, boolean b) {
                if (imageView.getTag().toString().equals(url)) {
                    Bitmap bitmap = imageContainer.getBitmap();//获取
                    imageView.setImageBitmap(bitmap);
                }  //设置
            }

            @Override//失败的方法
            public void onErrorResponse(VolleyError volleyError) {
                imageView.setImageResource(R.mipmap.ic_launcher);
            }
        });
    }

    private LruCache<String, Bitmap> mLruCache;

}
