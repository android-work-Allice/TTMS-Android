package com.example.xx.ttms_xupt.Activity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.xx.ttms_xupt.R;
import com.example.xx.ttms_xupt.util.MyOpenHelper;

/**
 * Created by xx on 2017/5/31.
 */

public class PersonEditActivity extends AppCompatActivity {

    private String et_id, et_no,et_name,et_phone,et_email,et_address;
    MyOpenHelper myOpenHelper;
    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personedit);
        img = (ImageView)findViewById(R.id.personEdit_zuo);
        img.setOnClickListener(new MyClickListener());
        myOpenHelper = new MyOpenHelper(getApplication());
    }

    public void edit(View view) {
        et_id = ((EditText)findViewById(R.id.et_id)).getText().toString();
        et_no = ((EditText)findViewById(R.id.et_no)).getText().toString();
        et_name = ((EditText)findViewById(R.id.et_name)).getText().toString();
        et_phone = ((EditText)findViewById(R.id.et_phone)).getText().toString();
        et_email = ((EditText)findViewById(R.id.et_email)).getText().toString();
        et_address = ((EditText)findViewById(R.id.et_address)).getText().toString();
        System.out.println("-------------------"+et_address);
        SQLiteDatabase db = myOpenHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("no",et_no);
        values.put("name",et_name);
        values.put("phone",et_phone);
        values.put("email",et_email);
        values.put("address",et_address);
        long insert = db.insert("info",null,values);
        db.close();

        if(insert > 0) {
            Toast.makeText(getApplicationContext(),"add success ",Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getApplicationContext(),"add faiure ",Toast.LENGTH_LONG).show();
        }
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

