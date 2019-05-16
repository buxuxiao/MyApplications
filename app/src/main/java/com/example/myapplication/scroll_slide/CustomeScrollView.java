package com.example.myapplication.scroll_slide;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

import com.example.myapplication.scroll_slide2.DefaultSlideListener;
import com.example.myapplication.scroll_slide2.SlideListener;

/**
 * Created by hulifei on 2019/5/10.
 */

public class CustomeScrollView extends ScrollView {
    private float mLastY = -1; // save event y
    private SlideListener slideListener = new DefaultSlideListener();

    public CustomeScrollView(Context context) {
        super(context);
    }

    public CustomeScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomeScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setSlideListener(SlideListener listener) {
        this.slideListener = listener;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (mLastY == -1) {
            mLastY = ev.getRawY();
        }

        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mLastY = ev.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                final float deltaY = ev.getRawY() - mLastY;
                mLastY = ev.getRawY();
                if (getChildCount() > 0 && getChildAt(0).getTop() == 0) {
                    if(slideListener.updateHeaderHeight(deltaY)){
//                       return true;
                        ev.setAction(MotionEvent.ACTION_DOWN);
                    }
                }
                break;
            default:
                mLastY = -1; // reset
                if (getChildCount() > 0 && getChildAt(0).getTop() == 0) {
                    slideListener.resetHeaderHeight();
                }

                break;
        }
        return super.onTouchEvent(ev);
    }
}
