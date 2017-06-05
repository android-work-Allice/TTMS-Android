package com.example.xx.ttms_xupt.util;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;

import com.example.xx.ttms_xupt.Activity.MainActivity;
/**
 * Created by xx on 2017/5/18.
 */

public class DialogUtil {

    public DialogUtil()
    {}

    // 显示消息的对话框
    public static void showDialog(final Context context, String msg, boolean goHome)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(context).setMessage(msg).setCancelable(false);
        if(goHome)
        {
            builder.setPositiveButton("确定", new DialogInterface.OnClickListener()
            {

                @Override
                public void onClick(DialogInterface dialog, int which)
                {
                    Intent intent=new Intent(context, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    context.startActivity(intent);
                }
            });
        }
        else
        {
            builder.setPositiveButton("确定", null);
        }
        builder.create().show();
    }

    // 显示指定组建的对话框
    public static void showDialog(Context context, View view)
    {
        new AlertDialog.Builder(context).setView(view).setCancelable(false).setPositiveButton("确定", null).create().show();
    }

}
