package com.example.myapplication.scroll;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Scroller;

import com.example.myapplication.R;

public class ScrollTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_test);

//        initView();
        initData();
    }

    private void initView(){
        View view=null;
        LinearLayout viewGroup=null;
        view.measure(0,0);
        viewGroup.measure(0,0);

        view.layout(0,0,0,0);
        viewGroup.layout(0,0,0,0);

        ListView listView;

    }

    private void initData(){

    }
}


