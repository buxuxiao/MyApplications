package com.example.myapplication.scroll_slide2;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import java.util.List;

/**
 * Created by hulifei on 2019/5/15.
 */

public class SlideAdapter extends BaseAdapter {

    private List<View> views = null;

    public SlideAdapter(List<View> views) {
        this.views = views;
    }

    @Override
    public int getCount() {
        return views.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return views.get(position);
    }
}
