package com.example.xx.ttms_xupt.MyFragment;

import android.content.Intent;
import android.os.Bundle;
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

import com.example.xx.ttms_xupt.Activity.PlayActivity;
import com.example.xx.ttms_xupt.Http.PlayHttp;
import com.example.xx.ttms_xupt.R;
import com.example.xx.ttms_xupt.model.PlayInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xx on 2017/5/31.
 *
 */

public class MyFragment2   extends Fragment {

    private ListView lv;
    private ImageView img;
    String readResult;
    static List<PlayInfo> plays;

    public MyFragment2() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fg_content2,container,false);
        GetplayThread t = new GetplayThread(plays);
        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        plays = t.getPlays();
        Log.e("plays  -----",plays+"");
        lv = (ListView) view.findViewById(R.id.lv_playlist);
        lv.setAdapter(new MyListAdapter(plays));

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
                Intent intent=new Intent(getActivity(), PlayActivity.class);
                intent.putExtra("detailPlay_id", plays.get(pos).getPlayId()+"");
                intent.putExtra("detailPlay_name", plays.get(pos).getPlayName()+"");
                intent.putExtra("detailPlay_type", plays.get(pos).getPlayType());
                intent.putExtra("detailPlay_lang", plays.get(pos).getPlayLanguage());
                intent.putExtra("detailPlay_profile", plays.get(pos).getPlayDesc());
                startActivity(intent);
            }
        });

        Log.e("HEHE", "2日狗");
        return view;
    }

    private class MyListAdapter extends BaseAdapter {

        List<PlayInfo> plays;

        public MyListAdapter (List<PlayInfo> plays){
            this.plays = plays;
        }

        @Override
        public int getCount() {
            return plays.size();
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
                view = LayoutInflater.from(getContext()).inflate(R.layout.item2,null);
            } else {
                view = converView;
            }
            TextView tv_name = (TextView)view.findViewById(R.id.play_title);
            TextView tv_leixing = (TextView)view.findViewById(R.id.play_leixing);
            TextView tv_message = (TextView)view.findViewById(R.id.tv_message);
//            TextView tv_message = (TextView)view.findViewById(R.id.tv_message);
//            ImageView tv_img = (ImageView)view.findViewById(R.id.iv_icon);
//            tv_img.setImageResource(img[i]);
            tv_name.setText(plays.get(i).getPlayName());
            tv_leixing.setText(plays.get(i).getPlayType());
            tv_message.setText(plays.get(i).getPlayDesc());
            return view;
        }
    }
}

class GetplayThread extends Thread {

    List<PlayInfo> plays;
    String readResult;
    JSONObject jsonObject;

    public GetplayThread(List<PlayInfo> plays){
        this.plays = plays;
    }

    public List<PlayInfo> getPlays() {
        return plays;
    }

    public void setPlays(List<PlayInfo> plays) {
        this.plays = plays;
    }

    @Override
    public void run() {
        readResult = PlayHttp.userList();
        PlayInfo playInfo = null;
        JSONArray jsonArray = null;
        try {
            jsonArray = new JSONArray(readResult);
            plays = new ArrayList<PlayInfo>(jsonArray.length()+1);
            for(int i=0; i<jsonArray.length(); i++) {
                playInfo= new PlayInfo();
                jsonObject = jsonArray.getJSONObject(i);
                playInfo.setPlayId(Integer.parseInt(jsonObject.getString("playId")));
                playInfo.setPlayName(jsonObject.getString("playName"));
                playInfo.setPlayType(jsonObject.getString("playType"));
                playInfo.setPlayDesc(jsonObject.getString("playDesc"));
                playInfo.setPlayLanguage(jsonObject.getString("playLanguage"));
                plays.add(playInfo);
            }
            Log.e(" run plays ---------  ",""+plays);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
