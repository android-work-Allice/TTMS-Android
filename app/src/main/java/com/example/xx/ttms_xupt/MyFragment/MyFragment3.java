package com.example.xx.ttms_xupt.MyFragment;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
import com.example.xx.ttms_xupt.R;
import com.example.xx.ttms_xupt.model.Person;
import com.example.xx.ttms_xupt.util.MyOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xx on 2017/5/31.
 */

public class MyFragment3 extends Fragment {

    private MyOpenHelper myOpenHelper;
    private List<Person> lists;
    private ListView lv;
    private ImageView img;

    public MyFragment3() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.fg_content3, container, false);

        lv = (ListView) view.findViewById(R.id.lv_userlist);
        img = (ImageView)view.findViewById(R.id.addPerson) ;
        img.setOnClickListener(new MyClickListener());
        myOpenHelper = new MyOpenHelper(getContext());

        lists = new ArrayList<Person>();
        SQLiteDatabase db = myOpenHelper.getReadableDatabase();
        Cursor cursor = db.query("info",new String[]{"id","no","name","phone","email","address"},null,null,null,null,null);
        if(cursor != null && cursor.getCount()>0) {
            while(cursor.moveToNext()){
                Person person = new Person();
                person.setId(cursor.getInt(0));
                person.setNo(cursor.getInt(1));
                person.setName(cursor.getString(2));
                person.setPhone(cursor.getString(3));
                person.setEmail(cursor.getString(4));
                person.setAddress(cursor.getString(5));
                lists.add(person);
            }
        }
        lv.setAdapter(new MyAdapter());

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
                Intent intent=new Intent(getActivity(), PersonActivity.class);
                intent.putExtra("no", lists.get(pos).getNo()+"");
                intent.putExtra("id", lists.get(pos).getId()+"");
                intent.putExtra("name", lists.get(pos).getName());
                intent.putExtra("tel", lists.get(pos).getPhone());
                intent.putExtra("email", lists.get(pos).getEmail());
                intent.putExtra("addr", lists.get(pos).getAddress());
                startActivity(intent);
            }
        });
        return view;
    }

    private class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return lists.size();
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

            Person person = lists.get(position);
            tv_name.setText(person.getName());
            tv_phone.setText(person.getPhone());
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

}
