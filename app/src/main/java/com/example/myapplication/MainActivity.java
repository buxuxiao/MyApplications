package com.example.myapplication;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toolbar;

import com.example.myapplication.aspect.AspectTestActivity;
import com.example.myapplication.cardview.CardSlipeActivity;
import com.example.myapplication.fingerprint.FingerPrintTestActivity;
import com.example.myapplication.font_control.FontTestActivity;
import com.example.myapplication.list.ListActivity;
import com.example.myapplication.list_control.ListControlActivity;
import com.example.myapplication.list_control2.ListControl2Activity;
import com.example.myapplication.scroll.ScrollTestActivity;
import com.example.myapplication.scroll_slide.ScrollSlideActivity;
import com.example.myapplication.scroll_slide2.SlideListViewActivity;
import com.example.myapplication.toolbar.ToolBarActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayAdapter<String> adapter;
    private List<String> list = new ArrayList<>();

    private Class[] clazz = {
            AspectTestActivity.class,
            ListActivity.class,
            ToolBarActivity.class,
            NFCActivity.class,
            VoiceRecordActivity.class,
            ListControlActivity.class,
            FontTestActivity.class,
            ListControl2Activity.class,
            ScrollTestActivity.class,
            ScrollSlideActivity.class,
            SlideListViewActivity.class,
            FingerPrintTestActivity.class,
            CardSlipeActivity.class,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initData();
    }

    private void initView() {
        listView = (ListView) findViewById(R.id.listView);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this,clazz[position]);
                startActivity(intent);
            }
        });
    }

    private void initData() {

        for (Class clz : clazz) {
            list.add(clz.getSimpleName());
        }
        adapter.notifyDataSetChanged();
    }
}
