package com.example.administrator.newseveryday;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.newseveryday.base.BaseActivity;
import com.example.administrator.newseveryday.base.GuideViewPagerAdapter;
import com.example.administrator.newseveryday.entity.News;
import com.example.administrator.newseveryday.fragment.FragmentMain;
import com.example.administrator.newseveryday.model.dao.NewsDBUtil1;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/7/19.
 */
public class Activity_Guide extends BaseActivity {
    private SharedUtil util;
    private ViewPager vp_guide;
    private ArrayList<ImageView> imgList = new ArrayList<>();
    private int[] imgId = {R.mipmap.hjc_c, R.mipmap.wow1, R.mipmap.zhise1, R.mipmap.hjc_z};
    private GuideViewPagerAdapter adapter;
    private int[] pointerIds = {R.id.indicator1, R.id.indicator2, R.id.indicator3, R.id.indicator4};
    private ImageView[] pointers = new ImageView[4];
    private int time;
    private TextView tv_guide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        util = SharedUtil.getInstance(this);
        if (util.getFirstRun()) {
            jumpActivity(FragmentMain.class);
            finish();
        }
        setContentView(R.layout.activity_guide);

        vp_guide = (ViewPager) findViewById(R.id.vp_guide);
        initData();
    }

    private void initData() {
        for (int i = 0; i < imgId.length; i++) {
            ImageView iv = new ImageView(getBaseContext());
            iv.setImageResource(imgId[i]);//设置图片资源id
            pointers[i] = (ImageView) findViewById(pointerIds[i]);
            tv_guide = (TextView) findViewById(R.id.tv_guide);
//            iv.setScaleType(ImageView.ScaleType.FIT_XY);//设置图片显示位拉伸
            imgList.add(iv);
        }
        setPointers(0);
        adapter = new GuideViewPagerAdapter(imgList);
        vp_guide.setAdapter(adapter);
        vp_guide.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                setPointers(position);
                if (position == 3) {
                    time = 6;
                    handler.sendEmptyMessage(0);
                    tv_guide.setVisibility(View.VISIBLE);
                } else {
                    tv_guide.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void setPointers(int position) {
        for (int i = 0; i < pointers.length; i++) {
            pointers[i].setAlpha(i == position ? 1 : 0.3f);
        }
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            tv_guide.setText(time + "秒后跳转页面");
            time--;

            if (time != 0) {
                handler.sendEmptyMessageDelayed(0, 1000);
            } else {
                util.putFirstRun();//记录
                jumpActivity(FragmentMain.class);
                finish();
            }
        }
    };


    /**
     * Created by Administrator on 2016/7/18.
     */
    public static class activity_detail extends Activity {
        private WebView detail;
        private ProgressBar pb_detail;
        private Button button;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_detail);
            detail = (WebView) findViewById(R.id.detail);
            pb_detail = (ProgressBar) findViewById(R.id.pb_detail);
            button = (Button) findViewById(R.id.djsc);
//            String url =getIntent().getStringExtra("link");

            String icon = getIntent().getStringExtra("icon");
            String url = getIntent().getStringExtra("url");
            final String summary = getIntent().getStringExtra("summary");
            String title = getIntent().getStringExtra("title");
            final News news = new News();
            news.setTitle(title);
            news.setIcon(icon);
            news.setLink(url);
            news.setSummary(summary);

            detail.setWebViewClient(new MyClient());//设置连接监听
            detail.setWebChromeClient(new MyChrome());//设置进度监听
            WebSettings settings = detail.getSettings();
            settings.supportZoom();
            settings.setBuiltInZoomControls(true);
            settings.setDisplayZoomControls(true);
            if (url != null) {
                detail.loadUrl(url);
            }
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    button.setText("收藏");
                    if (summary != null) {
                        NewsDBUtil1.getsInstance(getBaseContext()).insertNews(news);
                        Toast.makeText(getBaseContext(), "已收藏，请前往收藏列表察看", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

        private class MyClient extends WebViewClient {
            @Override//超链接
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        }

        private class MyChrome extends WebChromeClient {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                if (newProgress < 100) {
                    pb_detail.setVisibility(newProgress);
                    pb_detail.setVisibility(View.VISIBLE);
                } else {
                    pb_detail.setVisibility(View.GONE);
                }
            }
        }

        //返回键的处理   1。 重写onbackpressed;
        @Override
        public void onBackPressed() {
            if (detail.canGoBack()) {
                detail.goBack();
            } else {
                super.onBackPressed();
            }
        }
        //    方法二   重写 onkeydown

        //    @Override
        //    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //        //如果按下了返回键
        //        if (keyCode == KeyEvent.KEYCODE_BACK) {
        //            if (detail.canGoBack()) {
        //                detail.goBack();//返回上一页
        //            } else {
        //                finish();//关闭
        //            }
        //        }
        //        return super.onKeyDown(keyCode, event);
        //    }
    }
}
