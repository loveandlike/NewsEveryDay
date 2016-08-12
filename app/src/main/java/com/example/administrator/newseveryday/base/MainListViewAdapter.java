package com.example.administrator.newseveryday.base;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.support.v4.util.LruCache;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.example.administrator.newseveryday.R;
import com.example.administrator.newseveryday.entity.News;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/7/13.
 */
public class MainListViewAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<News> datas;
    private RequestQueue mRequestQueue;
    public void AddData(ArrayList<News> list){
        datas.addAll(list);
        notifyDataSetChanged();
    }

    public MainListViewAdapter(Context context, ArrayList<News> datas, RequestQueue requestQueue) {
        this.context = context;
        this.datas = datas;
        this.mRequestQueue = requestQueue;
        //获取当前应用总内存的1/8作为缓存空间
        long l = Runtime.getRuntime().totalMemory() / 8;
        mLruCache = new LruCache<>((int) l);

    }

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
        HoldView holdView;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_list_news, viewGroup, false);
            holdView = new HoldView(view);
            view.setTag(holdView);
        } else {
            holdView = (HoldView) view.getTag();
        }
        holdView.tv_title.setText(datas.get(i).getTitle());
        holdView.tv_title.setTextColor(0xf12334);
        holdView.tv_text.setText(datas.get(i).getSummary());
        setImage(holdView.icon, datas.get(i).getIcon());
        return view;
    }


    private class HoldView {
        TextView tv_title;
        TextView tv_text;
        ImageView icon;

        public HoldView(View view) {
            tv_text = (TextView) view.findViewById(R.id.textView1);
            tv_title = (TextView) view.findViewById(R.id.textView2);
            icon = (ImageView) view.findViewById(R.id.imageView1);
        }
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
    //制作圆形
    public Bitmap createCirclePicture(Bitmap bm){
        Paint paint=new Paint();//画笔
        paint.setAntiAlias(true);//抗锯齿
        int min=Math.min(bm.getWidth(),bm.getHeight());
        //bitmap的宽高   颜色制式
        Bitmap target=Bitmap.createBitmap(min,min, Bitmap.Config.ARGB_8888);
        Canvas canvas=new Canvas(target);//画布
        //圆心xy坐标  半径  画笔
        canvas.drawCircle(min/2,min/2,min/2,paint);//在画布上划出一个圆
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));//设置画笔取交集
        //1，原图片     2,3   起始xy     4,画笔
        canvas.drawBitmap(bm,0,0,paint);//开始在画布上绘制
        return target;
    }

}
