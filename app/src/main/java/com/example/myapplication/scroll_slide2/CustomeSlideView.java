package com.example.myapplication.scroll_slide2;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by hulifei on 2019/5/10.
 */

public class CustomeSlideView extends LinearLayout implements SlideListener {

    private TextView recentView;

    private final int MAX_HEIGHT = 100 * 3;
    private final static float OFFSET_RADIO = 2.8f;

    public CustomeSlideView(Context context) {
        super(context);
        initView(context);
    }

    public CustomeSlideView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public CustomeSlideView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        setOrientation(VERTICAL);

        recentView = new TextView(context);
        LayoutParams params2 = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0);
        recentView.setLayoutParams(params2);
        recentView.setText("最近使用");
        recentView.setGravity(Gravity.CENTER);
        recentView.setBackgroundColor(0xff6f5553);
        addView(recentView);

        TextView textView = new TextView(context);
        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 400 * 3);
        textView.setLayoutParams(params);
        textView.setText("我的应用");
        textView.setGravity(Gravity.CENTER);
        textView.setBackgroundColor(0xffc14089);
        addView(textView);
    }

    @Override
    public void resetHeaderHeight() {
        if (recentView.getLayoutParams().height < MAX_HEIGHT / 2) {
            recentView.getLayoutParams().height = 0;
        } else {
            recentView.getLayoutParams().height = MAX_HEIGHT;
        }
        recentView.setLayoutParams(recentView.getLayoutParams());
    }

    @Override
    public boolean updateHeaderHeight(float deltaY) {
        int height = recentView.getLayoutParams().height;

      /*  if (height >= MAX_HEIGHT && deltaY > 0) {
            return false;
        }*/

        if (height <= 0 && deltaY < 0) {
            return false;
        }


        if (height > 0 || deltaY > 0) {

            height += (int) (deltaY / OFFSET_RADIO);
            if (height < 0) {
                height = 0;
            }
            if (height > MAX_HEIGHT) {
                height = MAX_HEIGHT;
            }
            recentView.getLayoutParams().height = height;
            recentView.setLayoutParams(recentView.getLayoutParams());
        }

        return true;

    }




}
