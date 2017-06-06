package com.example.xx.ttms_xupt.MyFragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.xx.ttms_xupt.Activity.PersonActivity;
import com.example.xx.ttms_xupt.Activity.PersonEditActivity;
import com.example.xx.ttms_xupt.Http.UserHttp;
import com.example.xx.ttms_xupt.R;
import com.example.xx.ttms_xupt.model.EmployeeInfo;
import com.example.xx.ttms_xupt.util.MyOpenHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xx on 2017/5/31.
 *
 */

public class MyFragment3 extends Fragment {

    private MyOpenHelper myOpenHelper;
    private List<EmployeeInfo> users;
    private ListView lv;
    private ImageView img;
    private String readResult;
    View view;

    public MyFragment3() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = inflater.inflate(R.layout.fg_content3, container, false);
        lv = (ListView) view.findViewById(R.id.lv_userlist);
        GetUserThread  getUserThread = new GetUserThread(users);
        getUserThread.start();
//        users = getUserThread.getUsers();

        return view;
    }


    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
                users = (List<EmployeeInfo>) msg.obj;
                Log.e("fragment3 users  -----", users + "");
                lv = (ListView) view.findViewById(R.id.lv_userlist);
                lv.setAdapter(new MyAdapter(users));
                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
                        Intent intent = new Intent(getActivity(), PersonActivity.class);
                        intent.putExtra("no", users.get(pos).getEmpNo() + "");
                        intent.putExtra("id", users.get(pos).getEmpId() + "");
                        intent.putExtra("name", users.get(pos).getEmpName());
                        intent.putExtra("tel", users.get(pos).getEmpTel());
                        intent.putExtra("email", users.get(pos).getEmpEmail());
                        intent.putExtra("addr", users.get(pos).getEmpAddress());
                        startActivity(intent);
                    }
                });
        }
    };


    private class MyAdapter extends BaseAdapter {

        List<EmployeeInfo> users;

        public MyAdapter(List<EmployeeInfo> users) {
            this.users = users;
        }

        @Override
        public int getCount() {
            return users.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup viewGroup) {
            View view = null;
            if(convertView == null) {
                view = View.inflate(getContext(),R.layout.item3,null);
            } else {
                view = convertView;
            }
            TextView tv_name = (TextView)view.findViewById(R.id.tv_title);
            TextView tv_phone = (TextView)view.findViewById(R.id.tv_tel);
            ImageView tv_img = (ImageView)view.findViewById(R.id.iv_head);

            tv_name.setText(users.get(position).getEmpName());
            tv_phone.setText(users.get(position).getEmpTel());

            if(users.get(position).getSex().equals("男"))
                tv_img.setImageResource(R.mipmap.boy);
            else if(users.get(position).getSex().equals("女"))
                tv_img.setImageResource(R.mipmap.girl);
            return view;
        }
    }

    private class MyClickListener implements View.OnClickListener{

        //当按钮被点击的时候调用
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(getActivity(), PersonEditActivity.class);
            startActivity(intent);
        }
    }

    class GetUserThread extends Thread{

        private List<EmployeeInfo> users;
        private String readResult;
        private JSONObject jsonObject;

        public GetUserThread(List<EmployeeInfo> users) {
            this.users = users;
        }

        public List<EmployeeInfo> getUsers() {
            return users;
        }

        public void setUsers(List<EmployeeInfo> users) {
            this.users = users;
        }

        @Override
        public void run() {
            readResult = UserHttp.getUser();
            EmployeeInfo employeeInfo = null;
            JSONArray jsonArray = null;
            try {
                jsonArray = new JSONArray(readResult);
                users = new ArrayList<EmployeeInfo>(jsonArray.length()+1);
                for(int i=0; i<jsonArray.length(); i++) {
                    employeeInfo= new EmployeeInfo();
                    jsonObject = jsonArray.getJSONObject(i);
                    employeeInfo.setEmpPosition(jsonObject.getString("empPosition"));
                    employeeInfo.setEmpId(Integer.parseInt(jsonObject.getString("employeeId")));
                    employeeInfo.setEmpName(jsonObject.getString("employeeName"));
                    employeeInfo.setEmpNo(jsonObject.getString("empNo"));
                    employeeInfo.setEmpTel(jsonObject.getString("empTel"));
                    employeeInfo.setEmpAddress(jsonObject.getString("empAddress"));
                    employeeInfo.setEmpEmail(jsonObject.getString("empEmail"));
                    employeeInfo.setSex(jsonObject.getString("empSex"));
                    users.add(employeeInfo);
                }
                Message message = new Message();
                message.obj = users;
                handler.sendMessage(message);
                Log.e(" run plays ---------  ",""+users);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }

}

