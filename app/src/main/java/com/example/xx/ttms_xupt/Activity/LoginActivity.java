package com.example.xx.ttms_xupt.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.xx.ttms_xupt.Http.UserHttp;
import com.example.xx.ttms_xupt.R;
import com.example.xx.ttms_xupt.util.DialogUtil;

/**
 * Created by xx on 2017/5/31.
 *
 */

public class LoginActivity extends AppCompatActivity {

    private EditText et_name, et_pass;
    private CheckBox cb_remeberpass;
    private String name, pass;
    private boolean MemoryAcount;
    private int loginState;
    Intent intent;
    private static final int FAIL = -1;
    private static final int MANAGER = 1;
    private static final int SALER = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch ((Integer) msg.obj) {
                case FAIL :
                    Toast.makeText(getApplicationContext(),"login faiure ",Toast.LENGTH_LONG).show();
                    break;
                case MANAGER :
                    intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    break;
                case SALER :
                    Toast.makeText(getApplicationContext(),"售票员",Toast.LENGTH_LONG).show();
                    break;
            }
        }
    };

    public void login(View view) {
        et_name=(EditText) findViewById(R.id.et_name);
        et_pass=(EditText) findViewById(R.id.et_pass);
        cb_remeberpass=(CheckBox) findViewById(R.id.cb_remeberpass);
        name=et_name.getText().toString();
        pass=et_pass.getText().toString();
        MemoryAcount = false;
        String strModel="^[a-zA-Z0-9]{5,20}+$";
        System.out.println("-------00000000--------"+name);
        if(name.equals("") || !name.matches(strModel)) {
            DialogUtil.showDialog(this, "请填写字母数字构成的用户id，长度6-20位!", false);
        } else if(pass.equals("") || !pass.matches(strModel)) {
            DialogUtil.showDialog(this, "请填写字母数字构成的密码，长度6-20位!", false);
        } else {
            if (cb_remeberpass.isChecked()) {
                MemoryAcount = true;
                sendRequestWithHttpURLConnection();
            }
        }
    }

    private void sendRequestWithHttpURLConnection() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                loginState = -1;
                loginState = Integer.parseInt(UserHttp.checkUser(name, pass));
                Log.i("loginState--------->", ""+loginState);
                if (loginState == 1 || loginState == 2) {
                    // 1.选中记住密码，保存用户名和密码到首选项中
                    if (MemoryAcount) {
                        SharedPreferences sp = getSharedPreferences("account", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sp.edit();
                        editor.putString("name", name);
                        editor.putString("pass", pass);
                        editor.commit();
                    }
                }
                Message message = new Message();
                message.obj = loginState;
                handler.sendMessage(message);
            }
        }).start();
    }

}
