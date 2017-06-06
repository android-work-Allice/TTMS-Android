package com.example.xx.ttms_xupt.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.xx.ttms_xupt.Http.UserHttp;
import com.example.xx.ttms_xupt.R;
import com.example.xx.ttms_xupt.util.MyOpenHelper;

/**
 * Created by xx on 2017/5/31.
 */

public class PersonEditActivity extends AppCompatActivity {

    private EditText et_id, et_no,et_name,et_phone,et_email,et_address;
    private String set_id, set_no,set_name,set_phone,set_email,set_address;
    MyOpenHelper myOpenHelper;
    ImageView img;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personedit);
        img = (ImageView)findViewById(R.id.personEdit_zuo);
        img.setOnClickListener(new MyClickListener());
        et_id=(EditText) findViewById(R.id.et_id);
        et_no=(EditText) findViewById(R.id.et_no);
        et_name=(EditText) findViewById(R.id.et_name);
        et_phone=(EditText) findViewById(R.id.et_phone);
        et_email=(EditText) findViewById(R.id.et_email);
        et_address=(EditText) findViewById(R.id.et_address);

//        myOpenHelper = new MyOpenHelper(getApplication());
        intent=getIntent();
        et_id.setText(intent.getStringExtra("id"));
        et_no.setText(intent.getStringExtra("no"));
        et_name.setText(intent.getStringExtra("name"));
        et_phone.setText(intent.getStringExtra("tel"));
        et_email.setText(intent.getStringExtra("email"));
        et_address.setText(intent.getStringExtra("addr"));
//        Log.e("editperson------>",intent.getStringExtra("addr")+"");
    }

    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            if (msg.what == 1) {

            } else if (msg.what == 2) {
                if ("1".equals((String) msg.obj)) {
                    Toast.makeText(getApplicationContext(),"update 1 line",Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(),"update failure ",Toast.LENGTH_LONG).show();
                }
            }
        }
    };

    public void edit(View view) {
        set_id = ((EditText)findViewById(R.id.et_id)).getText().toString();
        set_no = ((EditText)findViewById(R.id.et_no)).getText().toString();
        set_name = ((EditText)findViewById(R.id.et_name)).getText().toString();
        set_phone = ((EditText)findViewById(R.id.et_phone)).getText().toString();
        set_email = ((EditText)findViewById(R.id.et_email)).getText().toString();
        set_address = ((EditText)findViewById(R.id.et_address)).getText().toString();
        System.out.println("-------------------"+et_address);
//        SQLiteDatabase db = myOpenHelper.getWritableDatabase();
//        ContentValues values = new ContentValues();
//        values.put("no",set_no);
//        values.put("name",set_name);
//        values.put("phone",set_phone);
//        values.put("email",set_email);
//        values.put("address",set_address);
//        long insert = db.insert("info",null,values);
//        db.close();

//        if(insert > 0) {
//            Toast.makeText(getApplicationContext(),"add success ",Toast.LENGTH_LONG).show();
//        } else {
//            Toast.makeText(getApplicationContext(),"add faiure ",Toast.LENGTH_LONG).show();
//        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                String update = UserHttp.updateUser(set_id, set_no,set_name,set_phone,set_email,set_address);
                Message message = new Message();
                message.obj = update;
                message.what = 2;
                handler.sendMessage(message);
            }
        }).start();

    }

    private class MyClickListener implements View.OnClickListener{

        //当按钮被点击的时候调用
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(PersonEditActivity.this,MainActivity.class );
            intent.putExtra("userloginflag", 2);
            startActivity(intent);
        }
    }
}

