package com.example.xx.ttms_xupt.MyFragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.xx.ttms_xupt.Http.PlanHttp;
import com.example.xx.ttms_xupt.R;
import com.example.xx.ttms_xupt.model.ScheduleInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xx on 2017/5/31.
 *
 */

public class MyFragment4   extends Fragment {

    private ListView lv;
    private ImageView img;
    private List<ScheduleInfo> plans;
    private View view;

    public MyFragment4() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fg_content4,container,false);
        GetplanThread getplanThread = new GetplanThread(plans);
        getplanThread.start();
        Log.e("HEHE", "4日狗");
        return view;
    }

    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            plans = (List<ScheduleInfo>)msg.obj;
            Log.e("fragment4 plans  -----",plans+"");
            lv = (ListView) view.findViewById(R.id.lv_yauchulist);
            lv.setAdapter(new MyFragment4.MyListAdapter(plans));
//            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                @Override
//                public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
//                    Intent intent=new Intent(getActivity(), PersonActivity.class);
//                    intent.putExtra("no", plans.get(pos).getScheId()+"");
//                    intent.putExtra("id", plans.get(pos).getStdioInfo().getStdioName());
//                    intent.putExtra("name", plans.get(pos).getPlayInfo().getPlayName());
//                    intent.putExtra("tel", plans.get(pos).getScheStartTime());
//                    intent.putExtra("email", plans.get(pos).getScheEndTime());
//                    startActivity(intent);
//                }
//            });
        }
    };

    private class MyListAdapter extends BaseAdapter {

        private List<ScheduleInfo> plans;

        public MyListAdapter(List<ScheduleInfo> plans) {
            this.plans = plans;
        }

        @Override
        public int getCount() {
            return plans.size();
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
        public View getView(int i, View converView, ViewGroup viewGroup) {
            // 1 想办法吧我们自己定义的布局转换成一个view对象就可以
            View view;
            if(converView == null) {
                //第二种获取打气筒服务
                view = LayoutInflater.from(getContext()).inflate(R.layout.item4,null);
            } else {
                view = converView;
            }
            TextView tv_name = (TextView)view.findViewById(R.id.plan_title);
            TextView tv_yingting = (TextView)view.findViewById(R.id.plan_yingting);
            TextView tv_time = (TextView)view.findViewById(R.id.tv_plantime);
//            TextView tv_message = (TextView)view.findViewById(R.id.tv_message);
//            ImageView tv_img = (ImageView)view.findViewById(R.id.iv_icon);
//            tv_img.setImageResource(img[i]);
            tv_name.setText(plans.get(i).getPlayName());
            tv_yingting.setText(plans.get(i).getStdioName());
            tv_time.setText(plans.get(i).getScheStartTime()+"--"+plans.get(i).getScheEndTime());
            return view;
        }
    }

    private class GetplanThread extends Thread{

        private List<ScheduleInfo> plans;
        private String readResult;
        private JSONObject jsonObject;

        public GetplanThread(List<ScheduleInfo> plans) {
            this.plans = plans;
        }

        public List<ScheduleInfo> getUsers() {
            return plans;
        }

        public void setUsers(List<ScheduleInfo> users) {
            this.plans = plans;
        }

        @Override
        public void run() {
            readResult = PlanHttp.planList();
            ScheduleInfo plan = null;
            JSONArray jsonArray = null;
            try {
                jsonArray = new JSONArray(readResult);
                plans = new ArrayList<ScheduleInfo>(jsonArray.length()+1);
                for(int i=0; i<jsonArray.length(); i++) {
                    plan= new ScheduleInfo();
                    jsonObject = jsonArray.getJSONObject(i);
                    plan.setScheId(Integer.parseInt(jsonObject.getString("ScheId")));
                    plan.setStdioName(jsonObject.getString("StdioName"));
                    plan.setPlayName(jsonObject.getString("PlayName"));
                    plan.setScheStartTime(jsonObject.getString("ScheStartTime"));
                    plan.setScheEndTime(jsonObject.getString("ScheEndTime"));
                    plans.add(plan);
                }
                Message message = new Message();
                message.obj = plans;
                handler.sendMessage(message);
                Log.e(" run plays ---------  ",""+plans);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }
}
