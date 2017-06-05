package com.example.xx.ttms_xupt.MyFragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.xx.ttms_xupt.Activity.LoginActivity;
import com.example.xx.ttms_xupt.R;

/**
 * Created by xx on 2017/5/31.
 */

public class MyFragment1  extends Fragment {

    private ImageView imgView;
    private String[][] strings = {
            {"巨幕影厅","环境优良"},
            {"儿童影厅","环境优良"},
            {"情感影厅","环境优良"},
            {"3D影厅","环境优良"},
            {"动感影厅","环境优良"},
            {"4D影厅","环境优良"}
    };

    private int[] img = {R.mipmap.ttyy,
            R.mipmap.yt1,
            R.mipmap.yt2,
            R.mipmap.yt3,
            R.mipmap.yt4,
            R.mipmap.yt5,
            R.mipmap.yt6};

    public MyFragment1() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fg_content1, container, false);
        // 1 找到我们关心的控件
        ListView lv = (ListView) view.findViewById(R.id.lv);
        imgView = (ImageView)view.findViewById(R.id.logout);
        imgView.setOnClickListener(new MyClickListener());
        // 2 显示数据和其他普通控件（textView)有点区别数据来源于数据适配器
        lv.setAdapter(new MyListAdapter());
        Log.e("HEHE", "1日狗");
        return view;
    }

    private class MyListAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return 6;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View converView, ViewGroup viewGroup) {
            // 1 想办法吧我们自己定义的布局转换成一个view对象就可以
            View view;
            if(converView == null) {
                //第二种获取打气筒服务
                view = LayoutInflater.from(getContext()).inflate(R.layout.item1,null);
            } else {
                view = converView;
            }
            TextView tv_name = (TextView)view.findViewById(R.id.tv_title);
            TextView tv_message = (TextView)view.findViewById(R.id.tv_message);
            ImageView tv_img = (ImageView)view.findViewById(R.id.iv_icon);
            tv_img.setImageResource(img[i]);
            tv_name.setText(strings[i][0]);
            tv_message.setText(strings[i][1]);
            return view;
        }
    }

    private class MyClickListener implements View.OnClickListener{

        //当按钮被点击的时候调用
        @Override
        public void onClick(View view) {
            SharedPreferences sp= getContext().getSharedPreferences("account", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor=sp.edit();
            editor.clear();
            editor.commit();
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            startActivity(intent);
        }
    }
}

