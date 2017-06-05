package com.example.xx.ttms_xupt.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.xx.ttms_xupt.R;

/**
 * Created by xx on 2017/6/1.
 */

public class PlayActivity extends AppCompatActivity {

    private TextView detailPlay_id, detailPlay_name, detailPlay_lang, detailPlay_profile, detailPlay_type;
    private String name;
    private ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        img = (ImageView)findViewById(R.id.play_zuo) ;
        img.setOnClickListener(new PlayActivity.MyClickListener());
        detailPlay_id=(TextView) findViewById(R.id.detailPlay_id);
        detailPlay_name=(TextView) findViewById(R.id.detailPlay_name);
        detailPlay_lang=(TextView) findViewById(R.id.detailPlay_lang);
        detailPlay_profile=(TextView) findViewById(R.id.detailPlay_profile);
        detailPlay_type=(TextView) findViewById(R.id.detailPlay_type);

        Intent intent=getIntent();
        detailPlay_id.setText(intent.getStringExtra("detailPlay_id"));
        detailPlay_name.setText(intent.getStringExtra("detailPlay_name"));
        detailPlay_type.setText(intent.getStringExtra("detailPlay_type"));
        detailPlay_lang.setText(intent.getStringExtra("detailPlay_lang"));
        detailPlay_profile.setText(intent.getStringExtra("detailPlay_profile"));
    }

    private class MyClickListener implements View.OnClickListener{

        //当按钮被点击的时候调用
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(PlayActivity.this,MainActivity.class );
            intent.putExtra("userloginflag", 1);
            startActivity(intent);
        }
    }

}

