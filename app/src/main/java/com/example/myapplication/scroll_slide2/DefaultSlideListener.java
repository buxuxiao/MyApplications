package com.example.myapplication.scroll_slide2;

import android.view.MotionEvent;

/**
 * Created by hulifei on 2019/5/15.
 */

public class DefaultSlideListener implements SlideListener {
    @Override
    public void resetHeaderHeight() {

    }

    @Override
    public boolean updateHeaderHeight(float deltaY) {
        return false;
    }

    @Override
    public boolean dispatchTouchEvents(MotionEvent ev) {
        return false;
    }
}
