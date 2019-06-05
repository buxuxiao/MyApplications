package com.example.myapplication.cardview;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

public class CardLayoutManager extends RecyclerView.LayoutManager {
    public final int SHOW_ITEM = 3;
    public final float SCALE = 0.05f;

    @Override
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new RecyclerView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        removeAllViews();
        detachAndScrapAttachedViews(recycler);

        int itemCount = getItemCount();
        int max = SHOW_ITEM;
        if (itemCount <= SHOW_ITEM) {
            max = itemCount - 1;
        }
        for (int position = max; position >= 0; position--) {
            View view = recycler.getViewForPosition(position);
            addView(view);
            measureChild(view, 0, 0);
            int viewWidth = getDecoratedMeasuredWidth(view);
            int viewHeight = getDecoratedMeasuredHeight(view);
            layoutDecoratedWithMargins(view, (getWidth() - viewWidth) / 2, (getHeight() - viewHeight) / 2,
                    (getWidth() + viewWidth) / 2, (getHeight() + viewHeight) / 2);
            if (position > 0) {
                if (position == SHOW_ITEM) {
                    position -= 1;
                }
                view.setScaleX(1 - position * SCALE);
                view.setScaleY(1 - position * SCALE);
                view.setTranslationY(-position * view.getMeasuredHeight() / 35);
            }
        }

    }
}
