package com.example.myapplication.test;

import android.app.Activity;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by hulifei on 2019/4/18.
 */

class MyViewBinder implements ViewBinder<Activity> {

    private static final ActivityViewFinder finder = new ActivityViewFinder();
    private static final Map<String, ViewBinder> map = new LinkedHashMap();

    public void bindView(Activity activity) {
        bindView(activity, activity, finder);
    }

    @Override
    public void bindView(Activity host, Object object, ViewFinder viewFinder) {
        String className = host.getClass().getName();
        ViewBinder viewBinder = map.get(className);
        try {
            if (viewBinder == null) {
                Class clazz = Class.forName(className + "$$ViewBinder");
                ViewBinder binder = (ViewBinder) clazz.newInstance();
                map.put(className, binder);
                if (binder != null) {
                    binder.bindView(host, object, viewFinder);
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void unbindView(Activity host) {
        String className = host.getClass().getName();
        ViewBinder binder = map.get(className);
        if (binder != null) {
            binder.unbindView(host);
        }
        map.remove(className);
    }
}
