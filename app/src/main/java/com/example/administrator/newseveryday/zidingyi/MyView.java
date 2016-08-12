package com.example.administrator.newseveryday.zidingyi;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

import com.example.administrator.newseveryday.R;

/**
 * Created by Administrator on 2016/8/3.
 */
public class MyView extends View {
    private Bitmap bitmap;
    private int y;
    private int x;

    //使用了两个参数的构造，为了能在xml布局文件中加载这个控件
    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        y = 0;
        x = 0;
    }

    //1.测量，测量这个空间的宽和高（会和布局里面的模式挂钩）
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    //2.布局 （决定他在布局的位置）
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    //3.绘制（长什么样子）
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(bitmap, x, y, null);

    }

    public void changeY() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (y < getHeight() - 50) {
                    try {
                        Thread.sleep(100);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    y += 10;
                    //invalidate();属于修改UI的行为，不能写在子线程中（两个方法均会调用ondraw进行重绘）
                    postInvalidate();//带handler的重绘
                }
            }
        }).start();
    }

    public void changeX() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (x < getWidth() - 100) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    x += 10;
                    //invalidate();属于修改UI的行为，不能写在子线程中（两个方法均会调用ondraw进行重绘）
                    postInvalidate();//带handler的重绘
                }
            }
        }).start();
    }

}
