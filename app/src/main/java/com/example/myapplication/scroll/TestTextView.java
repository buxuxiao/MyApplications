package com.example.myapplication.scroll;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by hulifei on 2019/5/9.
 */

@SuppressLint("AppCompatCustomView")
public class TestTextView  extends TextView{
    public TestTextView(Context context) {
        super(context);
    }

    public TestTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TestTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width=getMeasuredWidth();
        int height=getMeasuredHeight();
        if(width>height){
            height=width;
        }else{
            width=height;
        }
        setMeasuredDimension(width,height);
    }
}
