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

import com.example.xx.ttms_xupt.Activity.PlanActivity;
import com.example.xx.ttms_xupt.R;

/**
 * Created by xx on 2017/5/31.
 *
 */

public class MyFragment4   extends Fragment {

    private ListView lv;
    private ImageView img;

    public MyFragment4() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fg_content4,container,false);
        lv = (ListView) view.findViewById(R.id.lv_yauchulist);
        lv.setAdapter(new MyListAdapter());

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
                Intent intent=new Intent(getActivity(), PlanActivity.class);
//                intent.putExtra("no", lists.get(pos).getNo()+"");
//                intent.putExtra("id", lists.get(pos).getId()+"");
//                intent.putExtra("name", lists.get(pos).getName());
//                intent.putExtra("tel", lists.get(pos).getPhone());
//                intent.putExtra("email", lists.get(pos).getEmail());
//                intent.putExtra("addr", lists.get(pos).getAddress());
                startActivity(intent);
            }
        });
        Log.e("HEHE", "2日狗");
        return view;
    }

    private class MyListAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return 10;
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
//            TextView tv_message = (TextView)view.findViewById(R.id.tv_message);
//            ImageView tv_img = (ImageView)view.findViewById(R.id.iv_icon);
//            tv_img.setImageResource(img[i]);
            tv_name.setText("永乐票务");
            tv_leixing.setText("3D影厅");
            return view;
        }
    }

}
