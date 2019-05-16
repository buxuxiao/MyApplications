package com.example.myapplication.toolbar;

import android.support.design.widget.BaseTransientBottomBar;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.myapplication.R;
import com.example.myapplication.list_control2.ListControl2Adapter;

import java.util.Arrays;

public class ToolBarActivity extends AppCompatActivity {

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tool_bar);

        initView();
        initData();
    }

    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setLogo(R.mipmap.ic_launcher);
        toolbar.setTitle("hello");
        toolbar.setSubtitle("world");
        this.setSupportActionBar(toolbar);

       final FloatingActionButton fab= (FloatingActionButton) findViewById(R.id.FAB);
       fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(fab, "hello", BaseTransientBottomBar.LENGTH_SHORT).show();
            }
        });

        RecyclerView recyclerView= (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setAdapter(new ListControl2Adapter(Arrays.asList("hello","workd","pikapika","wanmgwang","abc","6666","hello","workd","pikapika","wanmgwang","abc","6666","hello","workd","pikapika","wanmgwang","abc","6666")));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void initData() {

    }
}
