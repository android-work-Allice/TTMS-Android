package com.example.xx.ttms_xupt.Http;

import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by xx on 2017/6/4.
 *
 */

public class PlayHttp {

    private static final String SERVER_URL="http://192.168.25.1:8080/ttms_ssm/playclient/play";
    private static String sessionid="";

    @SuppressWarnings("finally")
    public static String userList() {
        String result = "0";// 默认为0，表示验证失败
        try {
            String urlString = SERVER_URL;
            URL myUrl = new URL(urlString);
            // 使用HttpURLConnection打开连接
            HttpURLConnection urlConn = (HttpURLConnection) myUrl.openConnection();

            // set-cookie: JSESSIONID=E575C722CF20783370B51597B944010B; Path=/ttmsall; HttpOnly
            String cookieval = urlConn.getHeaderField("set-cookie");
//            Log.i("--------->", cookieval);

            // 获取sessionid
            if (cookieval != null) sessionid = cookieval.substring(0, cookieval.indexOf(";"));
            Log.i("playhttp--------->", sessionid);
            urlConn.connect();
            urlConn.setConnectTimeout(3000);
            urlConn.setReadTimeout(3000);

            // 得到读取的内容(流)
            InputStreamReader in = new InputStreamReader(urlConn.getInputStream());
            // 为输出创建BufferedReader
            BufferedReader buffer = new BufferedReader(in);
            result = buffer.readLine();
            Log.e("playresult--------->", result);
            in.close();
            urlConn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return result;
        }
    }
}
