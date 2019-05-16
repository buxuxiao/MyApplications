package com.example.myapplication.scroll_slide;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.scroll_slide2.CustomeSlideView;

import java.util.ArrayList;
import java.util.List;

public class ScrollSlideActivity extends AppCompatActivity {

    private int[] colors = {
            0xff2ec7c9, 0xffb6a2de, 0xff5ab1ef, 0xffffb980, 0xffd87a80,
            0xff8d98b3, 0xffe5cf0d, 0xff97b552, 0xff95706d, 0xffdc69aa,
            0xff07a2a4, 0xff9a7fd1, 0xff588dd5, 0xfff5994e, 0xffc05050,
            0xff59678c, 0xffc9ab00, 0xff7eb00a, 0xff6f5553, 0xffc14089,
    };

    private CustomeScrollView scrollView;
    private LinearLayout linearLayout;

    private List<String> modules = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_slide);

        initView();
        initData();
    }

    private void initView() {
        linearLayout = (LinearLayout) findViewById(R.id.linearLayout);
        scrollView= (CustomeScrollView) findViewById(R.id.scrollView);
    }

    private void initData() {
//        modules.add("我的应用");
        modules.add("新闻中心");
        modules.add("管理看板一");
        modules.add("管理看板二");
        modules.add("管理看板三");
        modules.add("管理看板4");
        modules.add("管理看板5");
        modules.add("管理看板6");
        modules.add("管理看板7");
        modules.add("管理看板8");
        modules.add("管理看板9");
        modules.add("管理看板10");

        for (int index = 0; index < modules.size(); index++) {
            linearLayout.addView(getView(modules.get(index), index));
        }

        CustomeSlideView view = new CustomeSlideView(this);
        scrollView.setSlideListener(view);
        linearLayout.addView(view, 1);

    }


    private TextView getView(String desc, int index) {
        TextView textView = new TextView(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 100 * 3);
        textView.setLayoutParams(params);
        textView.setText(desc);
        textView.setGravity(Gravity.CENTER);
        textView.setBackgroundColor(colors[index++]);
        return textView;
    }
}
