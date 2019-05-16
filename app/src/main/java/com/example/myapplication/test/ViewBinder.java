package com.example.myapplication.test;

/**
 * Created by hulifei on 2019/4/18.
 */

public interface ViewBinder<T> {
    void bindView(T host,Object object,ViewFinder viewFinder);
    void unbindView(T host);
}
