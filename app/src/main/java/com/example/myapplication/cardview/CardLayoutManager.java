package com.example.myapplication.cardview;

import android.support.v4.view.MotionEventCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

public class CardLayoutManager extends RecyclerView.LayoutManager {
    public final int SHOW_ITEM = 3;
    public final float SCALE = 0.05f;

    private RecyclerView mRecyclerView;
    private ItemTouchHelper mItemTouchHelper;

    public CardLayoutManager(RecyclerView recyclerView, ItemTouchHelper touchHelper) {
        this.mRecyclerView = recyclerView;
        this.mItemTouchHelper = touchHelper;
    }

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
            } else {
                view.setOnTouchListener(mOnTouchListener);
            }
        }

    }

    private View.OnTouchListener mOnTouchListener = new View.OnTouchListener() {

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            RecyclerView.ViewHolder childViewHolder = mRecyclerView.getChildViewHolder(v);
            // 把触摸事件交给 mItemTouchHelper，让其处理卡片滑动事件
            if (MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_DOWN) {
                mItemTouchHelper.startSwipe(childViewHolder);
            }
            return false;
        }
    };

}
