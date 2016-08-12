package com.example.administrator.newseveryday.zidingyi;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.administrator.newseveryday.R;

/**
 * Created by Administrator on 2016/8/3.
 */
public class OneActivity extends AppCompatActivity {
    private Button button1;
    private MyView myView;
    private Mybutton mybutton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.one_activity);
        button1 = (Button) findViewById(R.id.one_but1);
        myView = (MyView) findViewById(R.id.myview);
        mybutton= (Mybutton) findViewById(R.id.my_button);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myView.changeY();
                myView.changeX();
            }
        });
        mybutton.setMyOnclickListener(new Mybutton.MyOnclickListener() {
            @Override
            public void myOnClick(View view) {
                myView.changeY();
            }
        });
    }
}
