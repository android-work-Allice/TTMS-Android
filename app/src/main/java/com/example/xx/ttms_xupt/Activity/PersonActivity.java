package com.example.xx.ttms_xupt.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xx.ttms_xupt.Http.UserHttp;
import com.example.xx.ttms_xupt.R;

/**
 * Created by xx on 2017/5/31.
 */

public class PersonActivity  extends AppCompatActivity {

    private TextView tv_no, tv_id, tv_name, tv_tel, tv_email, tv_addr;
    private String name;
    private ImageView img;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);

        img = (ImageView)findViewById(R.id.person_zuo) ;
        img.setOnClickListener(new PersonActivity.MyClickListener());
        tv_id=(TextView) findViewById(R.id.tv_id);
        tv_no=(TextView) findViewById(R.id.tv_no);
        tv_name=(TextView) findViewById(R.id.tv_name);
        tv_tel=(TextView) findViewById(R.id.tv_tel);
        tv_email=(TextView) findViewById(R.id.tv_email);
        tv_addr=(TextView) findViewById(R.id.tv_addr);

        intent=getIntent();
        tv_id.setText(intent.getStringExtra("id"));
        tv_no.setText(intent.getStringExtra("no"));
        tv_name.setText(intent.getStringExtra("name"));
        tv_tel.setText(intent.getStringExtra("tel"));
        tv_email.setText(intent.getStringExtra("email"));
        tv_addr.setText(intent.getStringExtra("addr"));
    }

    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            if (msg.what == 1) {

            } else if (msg.what == 2) {
                if ("1".equals((String) msg.obj)) {
                    Toast.makeText(getApplicationContext(),"delete 1 line",Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(),"delete failure ",Toast.LENGTH_LONG).show();
                }
            }
        }
    };

    public void personclear(View view)
    {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String delete = UserHttp.clearUser(intent.getStringExtra("id"));
                Message message = new Message();
                message.obj = delete;
                message.what = 2;
                handler.sendMessage(message);
            }
        }).start();
    }

    public void personupdate(View view)
    {
        Intent intent = new Intent(PersonActivity.this, PersonEditActivity.class);
        intent.putExtra("id", tv_id.getText());
        intent.putExtra("no", tv_no.getText());
        intent.putExtra("name",tv_name.getText());
        intent.putExtra("tel", tv_tel.getText());
        intent.putExtra("email", tv_email.getText());
        intent.putExtra("addr",tv_addr.getText());
        startActivity(intent);
    }

    private class MyClickListener implements View.OnClickListener{

        //当按钮被点击的时候调用
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(PersonActivity.this,MainActivity.class );
            intent.putExtra("userloginflag", 2);
            startActivity(intent);
        }
    }

}
