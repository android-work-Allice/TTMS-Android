package com.example.xx.ttms_xupt.Activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.xx.ttms_xupt.Adapter.MyViewPagerAdapter;
import com.example.xx.ttms_xupt.R;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener,
        ViewPager.OnPageChangeListener {

    //UI Objects
    private RadioGroup rg_tab_bar;
    private RadioButton rb_home;
    private RadioButton rb_news;
    private RadioButton rb_resource;
    private RadioButton rb_service;
    private ViewPager vpager;

    private MyViewPagerAdapter mAdapter;

    //几个代表页面的常量
    public static final int PAGE_ONE = 0;
    public static final int PAGE_TWO = 1;
    public static final int PAGE_THREE = 2;
    public static final int PAGE_FOUR = 3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAdapter = new MyViewPagerAdapter(getSupportFragmentManager());
        bindViews();
        rb_home.setChecked(true);
    }

    @Override
    protected void onResume() {
        int id = getIntent().getIntExtra("userloginflag", 0);
        Log.e("onResume","-----"+id);
        vpager.setCurrentItem(id);
        super.onResume();
    }

    private void bindViews() {
        rg_tab_bar = (RadioGroup) findViewById(R.id.rg_tab_bar);
        rb_home = (RadioButton) findViewById(R.id.rb_home);
        rb_news = (RadioButton) findViewById(R.id.rb_news);
        rb_resource = (RadioButton) findViewById(R.id.rb_resource);
        rb_service = (RadioButton) findViewById(R.id.rb_service);
        rg_tab_bar.setOnCheckedChangeListener(this);

        vpager = (ViewPager) findViewById(R.id.vpager);
        vpager.setAdapter(mAdapter);
        vpager.setCurrentItem(0);
        vpager.addOnPageChangeListener(this);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rb_home:
                vpager.setCurrentItem(PAGE_ONE);
                break;
            case R.id.rb_news:
                vpager.setCurrentItem(PAGE_TWO);
                break;
            case R.id.rb_resource:
                vpager.setCurrentItem(PAGE_THREE);
                break;
            case R.id.rb_service:
                vpager.setCurrentItem(PAGE_FOUR);
        }
    }


    //重写ViewPager页面切换的处理方法
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        //state的状态有三个，0表示什么都没做，1正在滑动，2滑动完毕
        if (state == 2) {
            switch (vpager.getCurrentItem()) {
                case PAGE_ONE:
                    rb_home.setChecked(true);
                    break;
                case PAGE_TWO:
                    rb_news.setChecked(true);
                    break;
                case PAGE_THREE:
                    rb_resource.setChecked(true);
                    break;
                case PAGE_FOUR:
                    rb_service.setChecked(true);
                    break;
            }
        }
    }
}

