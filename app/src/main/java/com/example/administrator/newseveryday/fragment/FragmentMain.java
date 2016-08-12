package com.example.administrator.newseveryday.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import com.example.administrator.newseveryday.R;

/**
 * Created by Administrator on 2016/7/27.
 */
public class FragmentMain extends AppCompatActivity implements View.OnClickListener {
    FragmentManager fm;//碎片管理器
    FragmentTransaction ft;//碎片操作器
    Fragment_two fragmenttwo;
    Fragment_one fragment_one;
    Fragment_three fragment_three;

    private ImageButton title_bar;
    RelativeLayout iv_one, iv_two, iv_three;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        setContentView(R.layout.main_fragment);
        fragmenttwo = new Fragment_two();
        fragment_one = new Fragment_one();
        fragment_three = new Fragment_three();


//        Bundle bundle=new Bundle();
//        bundle.putString("name",name);
//        fragment_one.setArguments(bundle);
//        fragment_one.setonclicklistenner(new Fragment_one.onclick() {
//            @Override
//            public void setOnclic(String name) {
//                textView.setText(name);
//            }
//        });


        ft.add(R.id.main, fragment_one).add(R.id.main, fragmenttwo).add(R.id.main, fragment_three);
        ft.commit();

        navigationView = (NavigationView) findViewById(R.id.nav_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        title_bar = (ImageButton) findViewById(R.id.title_bar);
        iv_one = (RelativeLayout) findViewById(R.id.iv_one);
        iv_two = (RelativeLayout) findViewById(R.id.iv_two);
        iv_three = (RelativeLayout) findViewById(R.id.iv_three);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_main);
        iv_one.setOnClickListener(this);
        iv_two.setOnClickListener(this);
        iv_three.setOnClickListener(this);
        //toolbar
        toolbar.setTitle("新闻资讯");
        setSupportActionBar(toolbar);
        //参数一 当前activity   参数二 drawerlayout
        //参数三toobar          参数四，五     划出和划入的文本说明
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.app_name, R.string.app_name);
        toggle.syncState();//将开关同步设置
        drawerLayout.addDrawerListener(toggle);


        //侧滑菜单部分
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                String str = "";
                switch (item.getItemId()) {
                    case  R.id.wdsc:
                        Intent intent=new Intent(FragmentMain.this,Activity_love.class);
                        startActivity(intent);
                        str="请输入密码，亲";
                        break;
                    case R.id.gywm:
                        str="我们是hhhhhhhhh";
                        break;
                    case R.id.sz:
                        str="待开发";
                        break;
                }
                Snackbar.make(navigationView, str, Snackbar.LENGTH_SHORT).setAction("myaction", null).show();
                drawerLayout.closeDrawer(GravityCompat.START);

                return true;
            }
        });
    }


    @Override
    public void onClick(View view) {
        ft = fm.beginTransaction();
        switch (view.getId()) {
            case R.id.iv_one:

                ft.hide(fragmenttwo).hide(fragment_one).hide(fragment_three);
                ft.show(fragment_one);
                break;
            case R.id.iv_two:

                ft.hide(fragmenttwo).hide(fragment_one).hide(fragment_three);
                ft.show(fragment_three);
                break;
            case R.id.iv_three:

                ft.hide(fragmenttwo).hide(fragment_one).hide(fragment_three);
                ft.show(fragmenttwo);
                break;
        }
        ft.commit();
    }


    @Override
protected void onActivityResult(int requestCode, int resultCode, Intent data) {

//    String title = data.getStringExtra("title");
//    fragment_one.updateTitle(title);
        fragment_one.updateTitle();
}
}
