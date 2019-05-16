package com.example.myapplication.list_control;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class ListControlActivity extends AppCompatActivity {

    private ListView listView;
    private List<String> modules = new ArrayList<>();
    private ListControlAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_control);

        initView();
        initData();
    }

    private void initView() {
        listView = (ListView) findViewById(R.id.listView);
        adapter = new ListControlAdapter(this, modules);
        listView.setAdapter(adapter);
    }

    private void initData() {
        modules.add("我的应用");
        modules.add("新闻中心");
        modules.add("管理看板一");
        modules.add("管理看板二");
        modules.add("管理看板三");
    }
}
