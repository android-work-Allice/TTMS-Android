package com.example.xx.ttms_xupt.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.xx.ttms_xupt.R;

/**
 * Created by xx on 2017/5/18.
 */

public class FlashActivity extends AppCompatActivity {

    private static Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash);
        init();
    }

    public void init(){
        // 创建一个新的线程来显示欢迎动画，指定时间后跳转至指定界面
        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                try
                {
                    // 放置登陆信息数据
                    final SharedPreferences sp=getSharedPreferences("account", Context.MODE_PRIVATE);

                    // 未设置记住密码，进入登陆页
                    if(sp.getString("name", "").equals("")) {
                        intent = new Intent(FlashActivity.this, LoginActivity.class);
                        System.out.println("1111111");
                    }
                        // 用首选项中的用户名,密码验证(为了获取sessionid)
                    else
                    {
                        // 这里应该从首选项中获取用户名和密码，调用远程接口验证
                        if( (sp.getString("name", "").equals("u12345") ) && ((sp.getString("pass", "")).equals("u12345")) )
                            intent=new Intent(FlashActivity.this, MainActivity.class);
                        else {
                            intent = new Intent(FlashActivity.this, LoginActivity.class);
                            System.out.println("2222222--------"+sp.getString("name", ""));
                        }
                    }
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    Thread.sleep(1000);
                    getApplicationContext().startActivity(intent);
                    finish();
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
