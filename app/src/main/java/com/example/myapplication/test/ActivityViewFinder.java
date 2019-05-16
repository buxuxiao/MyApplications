package com.example.myapplication.test;

import android.app.Activity;
import android.view.View;

/**
 * Created by hulifei on 2019/4/18.
 */

public class ActivityViewFinder implements ViewFinder {
    @Override
    public View findView(Object object, int id) {
        if (object != null && object instanceof Activity) {
            return ((Activity) object).findViewById(id);
        }
        throw new ClassCastException("not activity");
    }
}
