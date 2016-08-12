package com.example.administrator.newseveryday.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.administrator.newseveryday.R;
import com.example.administrator.newseveryday.Util.NewShareUtil;

import java.util.ArrayList;
import java.util.Set;

/**
 * Created by Administrator on 2016/7/29.
 */
public class AddActivity extends AppCompatActivity {
    EditText editText;
    Button button;
    Set<String> sets;
    ArrayList<String> list;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_activity);
        editText= (EditText) findViewById(R.id.edtext);
        button= (Button) findViewById(R.id.bt_add);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (editText.getText()==null||editText.getText().toString().equals("")){
                    Toast.makeText(AddActivity.this,"金爷万岁",Toast.LENGTH_SHORT).show();
                }else {
                    String title=editText.getText().toString();
//                    list.add(title);
                    NewShareUtil.getInstance(AddActivity.this).setSpData(title);
                    //传回去
//                    Intent intent=new Intent();
//                    intent.putExtra("title",title);
                    setResult(210,getIntent());
                    finish();
                }
            }
        });
    }
}
