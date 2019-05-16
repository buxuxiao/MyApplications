package com.example.myapplication.list_control2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class ListControl2Activity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<String> modules = new ArrayList<>();
    private ListControl2Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_control2);

        initView();
        initData();
    }

    private void initView() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        adapter = new ListControl2Adapter(modules);
//        recyclerView.setLayoutManager(new GridLayoutManager(this,4));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        //1.创建item helper
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new MyItemTouchHelper(adapter,modules));
        //2.绑定到recyclerview上面去
        itemTouchHelper.attachToRecyclerView(recyclerView);
        //3.在ItemHelper的接口回调中过滤开启长按拖动，拓展其他操作
    }

    private void initData() {
        modules.add("我的应用");
        modules.add("新闻中心");
        modules.add("管理看板一");
        modules.add("管理看板二");
        modules.add("管理看板三");
        adapter.notifyDataSetChanged();
    }


}
