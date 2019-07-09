package com.example.myapplication.scroll_slide2;

import android.view.MotionEvent;

/**
 * Created by hulifei on 2019/5/15.
 */

public interface SlideListener {
    void resetHeaderHeight();

    boolean updateHeaderHeight(float deltaY);

    boolean dispatchTouchEvents(MotionEvent ev);
}
