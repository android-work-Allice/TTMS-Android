package com.example.xx.ttms_xupt.Http;

import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by xx on 2017/6/2.
 *
 */

public class UserHttp  {

    private static final String SERVER_URL="http://192.168.25.1:8080/ttms_ssm/userclient/login";
    private static String sessionid="";

    @SuppressWarnings("finally")
    public static String checkUser(String account, String pwd)
    {
        String result="0";// 默认为0，表示验证失败
        try
        {
            String urlString=SERVER_URL + "?account=" + account + "&pwd=" + pwd;
            URL myUrl=new URL(urlString);
            // 使用HttpURLConnection打开连接
            HttpURLConnection urlConn=(HttpURLConnection) myUrl.openConnection();

            // set-cookie: JSESSIONID=E575C722CF20783370B51597B944010B; Path=/ttmsall; HttpOnly
            String cookieval=urlConn.getHeaderField("set-cookie");
//            Log.i("--------->", cookieval);

            // 获取sessionid
            if(cookieval != null) sessionid=cookieval.substring(0, cookieval.indexOf(";"));
            Log.i("--------->", sessionid);
            urlConn.connect();
            urlConn.setConnectTimeout(3000);
            urlConn.setReadTimeout(3000);

            // 得到读取的内容(流)
            InputStreamReader in=new InputStreamReader(urlConn.getInputStream());
            // 为输出创建BufferedReader
            BufferedReader buffer=new BufferedReader(in);
            result=buffer.readLine();
            Log.i("--------->", result);
            in.close();
            urlConn.disconnect();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            return result;
        }
    }

    @SuppressWarnings("finally")
    public static String getUser()
    {
        String result=null;
        try
        {
            String urlString="http://192.168.25.1:8080/ttms_ssm/userclient/userList";
            URL myUrl=new URL(urlString);
            // 使用HttpURLConnection打开连接
            HttpURLConnection urlConn=(HttpURLConnection) myUrl.openConnection();
            if(sessionid != null)
            {
                // 发送请求时带上cookie(包含sessionid)
                urlConn.setRequestProperty("cookie", sessionid);
            }
            urlConn.connect();
            // 设置为超时时间为3秒，如果3秒内不能连接就被认为是有错误发生
            urlConn.setConnectTimeout(3000);
            urlConn.setReadTimeout(3000);

            // 得到读取的内容(流)
            InputStreamReader in=new InputStreamReader(urlConn.getInputStream());
            // 为输出创建BufferedReader
            BufferedReader buffer=new BufferedReader(in);
            result=buffer.readLine();
            Log.e("users --------->", result);
            in.close();
            urlConn.disconnect();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            return result;
        }
    }

    public static String clearUser(String userid) {
        String url = "http://192.168.25.1:8080/ttms_ssm/userclient/clearuser?userid="+userid;
        String result = null;
        try {
            URL myUrl = new URL(url);
            // 使用HttpURLConnection打开连接
            HttpURLConnection urlConn = (HttpURLConnection) myUrl.openConnection();
            if (sessionid != null) {
                // 发送请求时带上cookie(包含sessionid)
                urlConn.setRequestProperty("cookie", sessionid);
            }
            urlConn.connect();
            // 设置为超时时间为3秒，如果3秒内不能连接就被认为是有错误发生
            urlConn.setConnectTimeout(3000);
            urlConn.setReadTimeout(3000);

            // 得到读取的内容(流)
            InputStreamReader in = new InputStreamReader(urlConn.getInputStream());
            // 为输出创建BufferedReader
            BufferedReader buffer = new BufferedReader(in);
            result = buffer.readLine();
            Log.e("user delete --------->", result);
            in.close();
            urlConn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return result;
        }
    }
    public static String updateUser(String set_id, String set_no,String set_name,String set_phone,String set_email,String set_address) {
        String url = "http://192.168.25.1:8080/ttms_ssm/userclient/updateuser"
                +"?set_id="+set_id
                +"&set_no="+set_no
                +"&set_name="+set_name
                +"&set_phone="+set_phone
                +"&set_email="+set_email
                +"&set_address="+set_address;
        String result = null;
        try {
            URL myUrl = new URL(url);
            // 使用HttpURLConnection打开连接
            HttpURLConnection urlConn = (HttpURLConnection) myUrl.openConnection();
            if (sessionid != null) {
                // 发送请求时带上cookie(包含sessionid)
                urlConn.setRequestProperty("cookie", sessionid);
            }
            urlConn.connect();
            // 设置为超时时间为3秒，如果3秒内不能连接就被认为是有错误发生
            urlConn.setConnectTimeout(3000);
            urlConn.setReadTimeout(3000);

            // 得到读取的内容(流)
            InputStreamReader in = new InputStreamReader(urlConn.getInputStream());
            // 为输出创建BufferedReader
            BufferedReader buffer = new BufferedReader(in);
            result = buffer.readLine();
            Log.e("user update --------->", result);
            in.close();
            urlConn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return result;
        }
    }

}
