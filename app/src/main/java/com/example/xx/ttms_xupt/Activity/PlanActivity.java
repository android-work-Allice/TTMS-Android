package com.example.xx.ttms_xupt.Activity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xx.ttms_xupt.R;
import com.example.xx.ttms_xupt.util.MyOpenHelper;

/**
 * Created by xx on 2017/6/1.
 */

public class PlanActivity extends AppCompatActivity {

    private TextView tv_no, tv_id, tv_name, tv_tel, tv_email, tv_addr;
    private String name;
    private ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan);

        img = (ImageView)findViewById(R.id.plan_zuo) ;
        img.setOnClickListener(new PlanActivity.MyClickListener());
        tv_id=(TextView) findViewById(R.id.tv_id);
        tv_no=(TextView) findViewById(R.id.tv_no);
        tv_name=(TextView) findViewById(R.id.tv_name);
        tv_tel=(TextView) findViewById(R.id.tv_tel);
        tv_email=(TextView) findViewById(R.id.tv_email);
        tv_addr=(TextView) findViewById(R.id.tv_addr);

        Intent intent=getIntent();
        tv_id.setText(intent.getStringExtra("id"));
        tv_no.setText(intent.getStringExtra("no"));
        name = intent.getStringExtra("name");
        tv_name.setText(intent.getStringExtra("name"));
        tv_tel.setText(intent.getStringExtra("tel"));
        tv_email.setText(intent.getStringExtra("email"));
        tv_addr.setText(intent.getStringExtra("addr"));
    }

    public void clear(View view)
    {
        MyOpenHelper myOpenHelper = new MyOpenHelper(getApplication());
        SQLiteDatabase db = myOpenHelper.getWritableDatabase();
        //返回值代表影响的行数
        int delete = db.delete("info","name = ?",new String[]{name});
        db.close();

        if(delete > 0) {
            Toast.makeText(getApplicationContext(),"delete "+delete+" line",Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getApplicationContext(),"delete failure ",Toast.LENGTH_LONG).show();
        }
    }

    private class MyClickListener implements View.OnClickListener{

        //当按钮被点击的时候调用
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(PlanActivity.this,MainActivity.class );
            intent.putExtra("userloginflag", 3);
            startActivity(intent);
        }
    }

}
