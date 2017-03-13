package com.baway.jingzeyang;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Item> list;
    private Item item;
    private MyBaseAdapter myBaseAdapter;
    private ListView listview;
    private EditText text1, text2;
    private Button but;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listview = (ListView) findViewById(R.id.listview);
        text1 = (EditText) findViewById(R.id.number);
        text2 = (EditText) findViewById(R.id.textaa);
        but = (Button) findViewById(R.id.but4);
        initData();
        myBaseAdapter = new MyBaseAdapter(this, list);
        listview.setAdapter(myBaseAdapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                if (view.getTag() != null) {
                    MyBaseAdapter.ViewHolder holder = (MyBaseAdapter.ViewHolder) view.getTag();
                    item = list.get(i);
                    item.flag = !item.flag;
                    holder.check.setChecked(item.flag);

                }

            }
        });
        but.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                int postion = Integer.parseInt(text1.getText().toString().trim());
                String changetext = text2.getText().toString().trim();
//                item = list.get(postion);
//                item.name = changetext;
                list.get(postion).name=changetext;
                int start=listview.getFirstVisiblePosition();
                int over=listview.getLastVisiblePosition();
                if(postion>=start&&postion<=over)
                {
                    View childAt = listview.getChildAt(postion-start);
                    if (childAt.getTag()!=null)
                    {
                        MyBaseAdapter.ViewHolder viewHolder= (MyBaseAdapter.ViewHolder) childAt.getTag();
                        viewHolder.textview.setText(list.get(postion).name);

                    }

                }

            }
        });

    }

    private void initData() {
        list = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            item = new Item();
            item.name = "我是测试数据" + i;
            item.flag = false;
            list.add(item);
        }

    }

}
