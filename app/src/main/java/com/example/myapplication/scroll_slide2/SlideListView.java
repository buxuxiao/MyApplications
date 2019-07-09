package com.example.myapplication.scroll_slide2;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ListView;

import java.util.List;

/**
 * Created by hulifei on 2019/5/14.
 */

public class SlideListView extends ListView {

    private float mLastY = -1; // save event y
    private SlideListener slideListener = new DefaultSlideListener();

    public SlideListView(Context context) {
        super(context);
    }

    public SlideListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SlideListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setSlideListener(SlideListener listener) {
        this.slideListener = listener;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        slideListener.dispatchTouchEvents(MotionEvent.obtain(ev));

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
                if (getFirstVisiblePosition() == 0 && getChildAt(0).getTop() == 0) {
                    if (slideListener.updateHeaderHeight(deltaY)) {
//                        return true;
                        ev.setAction(MotionEvent.ACTION_DOWN);
                    }
                }
                break;
            default:
                mLastY = -1; // reset
                if (getFirstVisiblePosition() == 0) {
                    slideListener.resetHeaderHeight();
                }

                break;
        }
        return super.onTouchEvent(ev);
    }
}
