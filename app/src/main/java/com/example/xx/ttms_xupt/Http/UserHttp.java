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
//        String result="0";// 默认为0，表示验证失败
//        DataOutputStream out = null;
//        String param = null;
//        try
//        {
//            String urlString=SERVER_URL ;
//            URL myUrl=new URL(urlString);
//            // 使用HttpURLConnection打开连接
//            HttpURLConnection urlConn=(HttpURLConnection) myUrl.openConnection();
//            urlConn.setRequestMethod("POST");
//            urlConn.setDoOutput(true);
//            urlConn.setDoInput(true);
//            urlConn.setUseCaches(false);
//            urlConn.setConnectTimeout(3000);
//            urlConn.setReadTimeout(3000);
//            urlConn.connect();
//
//            OutputStream os = urlConn.getOutputStream();
//            OutputStreamWriter osw = new OutputStreamWriter(os);
//            BufferedWriter bw = new BufferedWriter(osw);
//            // set-cookie: JSESSIONID=E575C722CF20783370B51597B944010B; Path=/ttmsall; HttpOnly
//            String cookieval=urlConn.getHeaderField("set-cookie");
////            Log.i("--------->", cookieval);
//
//            // 获取sessionid
//            if(cookieval != null) sessionid=cookieval.substring(0, cookieval.indexOf(";"));
//            Log.i("--------->", sessionid);
//            JSONArray jsonArray = new JSONArray();
//            JSONObject jsonObject = new JSONObject();
//            jsonObject.put("username",account);
//            jsonObject.put("password",pwd);
//            jsonArray.put(jsonObject);
//            System.out.println( jsonArray.toString() );
//
//            bw.write(jsonArray.toString());
//            bw.flush();
//            bw.close();
//
//
//
//            // 获取URLConnection对象对应的输出流
//            // 得到读取的内容(流)
//            InputStream is = urlConn.getInputStream();
//            InputStreamReader isr = new InputStreamReader(is);
//            BufferedReader br = new BufferedReader(isr);
//            result = br.readLine();
//            Log.i("--------->", result);
//            br.close();
//            urlConn.disconnect();
//        }
//        catch(Exception e)
//        {
//            e.printStackTrace();
//        }
//        finally
//        {
//            return result;
//        }
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
            String urlString=SERVER_URL + "client/GetUser";
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

}
