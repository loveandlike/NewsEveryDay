package com.example.administrator.newseveryday;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

/**
 * Created by Administrator on 2016/7/20.
 */
public class NetworkUtil {
    private RequestQueue requestQueue;

    public NetworkUtil(Context context) {
        //创建一个请求队列
        requestQueue = Volley.newRequestQueue(context);
    }


    public void getStringResult(String url, final TextView textView) {
        //1请求方式    2网址    3联网成功响应的接口
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            //  3联网成功响应,接收到的数据s
            public void onResponse(String s) {

            }
            //当发生错误的时候
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.d("debug", "报告陛下，有异常发生");
            }
        });
        requestQueue.add(request);//将请求放入请求队列
    }

//    图片加载   imageLoader
    public void setImagePic(final ImageView imageView, String url) {
        //1网址   2，成功回调接口  3图片最大宽度    4，5。代表最大宽和高0，math_parent
        ImageRequest imageRequest = new ImageRequest(url, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap bitmap) {
                imageView.setImageBitmap(bitmap);
            }
            //6.图片色彩   7，失败的接口
        }, 0, 0, Bitmap.Config.ARGB_8888, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                imageView.setImageResource(R.mipmap.chim);
            }
        });
        requestQueue.add(imageRequest);
    }

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
