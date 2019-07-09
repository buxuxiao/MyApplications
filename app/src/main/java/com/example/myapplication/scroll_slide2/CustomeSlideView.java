package com.example.myapplication.scroll_slide2;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.myapplication.R;

/**
 * Created by hulifei on 2019/5/10.
 */

public class CustomeSlideView extends FrameLayout implements SlideListener {

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
        LayoutInflater.from(context).inflate(R.layout.layout_custome_slide, this, true);

        recentView = findViewById(R.id.recentView);
        recentView.getLayoutParams().height = 0;

        final WarpLinearLayout recyclerView = findViewById(R.id.recyclerView);
        recyclerView.postDelayed(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 12; i++) {
                    View view = LayoutInflater.from(getContext()).inflate(R.layout.layout_custome_slide_item, recyclerView, false);
                    view.getLayoutParams().width = recyclerView.getWidth() / 4;
                    recyclerView.addView(view);

                    view.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Log.d("abc", "onclick");
                        }
                    });

                }
            }
        }, 100);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return false;
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

        if (height >= MAX_HEIGHT && deltaY > 0) {
            return false;
        }

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

    @Override
    public boolean dispatchTouchEvents(MotionEvent ev) {
        for (int i = 0; i < getChildCount(); i++) {
            if (getChildAt(i).dispatchTouchEvent(ev)) {
                //break;
            }
        }
        return false;
    }


}
