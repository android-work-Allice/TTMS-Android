package com.example.xx.ttms_xupt.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by xx on 2017/5/31.
 */

public class MyOpenHelper extends SQLiteOpenHelper {

    public MyOpenHelper(Context context) {
        super(context, "database.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table info(id integer primary key autoincrement," +
                "no integer ,name varchar(20),phone varchar(11)," +
                "email varchar(25),address varchar(50))");
        sqLiteDatabase.execSQL("insert into info ('name','phone')values('张三','15829202176')");
        sqLiteDatabase.execSQL("insert into info ('name','phone') values ('李四','13829202176')");
        sqLiteDatabase.execSQL("insert into info ('name','phone') values ('动车','13829202176')");
        sqLiteDatabase.execSQL("insert into info ('name','phone') values ('倒带','13829202176')");
        sqLiteDatabase.execSQL("insert into info ('name','phone') values ('各个','13829202176')");
        sqLiteDatabase.execSQL("insert into info ('name','phone') values ('额额','13829202176')");
        sqLiteDatabase.execSQL("insert into info ('name','phone') values ('拉拉','13829202176')");
        sqLiteDatabase.execSQL("insert into info ('name','phone') values ('波波','13829202176')");
        sqLiteDatabase.execSQL("insert into info ('name','phone') values ('各个','13829202176')");
        sqLiteDatabase.execSQL("insert into info ('name','phone') values ('当当','13829202176')");
        sqLiteDatabase.execSQL("insert into info ('name','phone') values ('蛋蛋','13829202176')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}

