package com.example.myapplication.toolbar;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by hulifei on 2019/5/7.
 */

public class FABehavior extends CoordinatorLayout.Behavior<FloatingActionButton> {

    public FABehavior(Context context, AttributeSet attrs) {
        super(context,attrs);
    }

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, FloatingActionButton child, View directTargetChild, View target, int nestedScrollAxes) {
        if(nestedScrollAxes== ViewCompat.SCROLL_AXIS_VERTICAL){
            child.hide();
            return true;
        }

        return super.onStartNestedScroll(coordinatorLayout, child, directTargetChild, target, nestedScrollAxes);
    }

    @Override
    public void onStopNestedScroll(CoordinatorLayout coordinatorLayout, FloatingActionButton child, View target) {
        super.onStopNestedScroll(coordinatorLayout, child, target);
        if(!child.isShown()){
            child.show();
        }
    }
}
