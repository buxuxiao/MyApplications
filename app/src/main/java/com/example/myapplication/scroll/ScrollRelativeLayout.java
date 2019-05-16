package com.example.myapplication.scroll;

import android.content.Context;
import android.support.v4.view.NestedScrollingChild;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Scroller;

/**
 * Created by hulifei on 2019/5/8.
 */

public class ScrollRelativeLayout extends RelativeLayout {
    private Scroller scroller;
    private int lastX = 0;
    private int lastY = 0;

    public ScrollRelativeLayout(Context context) {
        super(context);
          scroller=new Scroller(context);
    }

    public ScrollRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        scroller=new Scroller(context);
    }

    public ScrollRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        scroller=new Scroller(context);
    }



    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();

        int action = event.getAction();

        switch (action) {
            case MotionEvent.ACTION_DOWN:
                if(!scroller.isFinished()){
                    scroller.abortAnimation();
                }
                lastX = x;
                lastY = y;
                break;

            case MotionEvent.ACTION_MOVE:
                int dx = x - lastX;
                int dy = y - lastY;
                Log.d("abc", "dx=" + dx + " dy=" + dy);
//                scrollBy(-dx, -dy);
                scroller.startScroll(scroller.getFinalX(),scroller.getFinalY(),dx,0);
                invalidate();

                lastX = x;
                lastY = y;
                break;

        }

        return true;
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
       if(scroller.computeScrollOffset()){
           scrollTo(-scroller.getCurrX(),-scroller.getCurrY());
       }
    }


}
