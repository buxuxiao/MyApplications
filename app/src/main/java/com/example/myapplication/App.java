package com.example.myapplication;

import android.app.Application;

/**
 * Created by hulifei on 2019/4/30.
 */

public class App extends Application {
    public static App ctx;

    public int theme;

    @Override
    public void onCreate() {
        super.onCreate();
        ctx = this;
        theme=R.style.Theme_Medium;
    }
}
