package com.example.administrator.newseveryday.zidingyi;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Administrator on 2016/8/3.
 */
public class Mybutton extends View {
    Paint paint;
    public Mybutton(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint=new Paint();
        paint.setColor(0xffff0000);

    }



    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRect(0,0,getWidth(),getHeight(),paint);
    }

    boolean isDown=false;
    float x;
    float y;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                x=event.getX();
                y=event.getY();
                if (x > 0 && y > 0 && x < getWidth() && y < getHeight()) {
                    paint.setColor(0xff00ff00);
                    invalidate();
                    isDown=true;
                    return true;
                }
                else {
                    return super.onTouchEvent(event);
                }

            case MotionEvent.ACTION_UP:
                if(isDown) {
                    paint.setColor(0xffff0000);
                    invalidate();
                    isDown = false;
                    listener.myOnClick(this);
                }

                return true;
            case MotionEvent.ACTION_MOVE:
//                Log.e("msg",event.getX()+"x");
//                Log.e("msg",event.getY()+"y");
                x=event.getX();
                y=event.getY();
                if(isDown) {
                    if (x > 0 && y > 0 && x <getWidth() && y <getHeight()) {
                    }else{
                        paint.setColor(0xffff0000);
                        invalidate();
                        isDown=false;
                    }
                }
//                paint.setColor(0xffff0000);
//                invalidate();
                return true;
        }
        return super.onTouchEvent(event);
    }

    //下属：1.定义接口
    interface MyOnclickListener{
        void  myOnClick(View view);
    }
    //2.对外公开一个设置接口的方法
    private MyOnclickListener listener;
    public void setMyOnclickListener( MyOnclickListener listener){
        this.listener=listener;
    }
}
