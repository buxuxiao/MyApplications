package com.example.myapplication.cardview;

import android.support.v7.widget.RecyclerView;

import com.example.myapplication.cardview.helper.ItemTouchHelper;

public class CardItemTouchHelperCallback<T> extends ItemTouchHelper.Callback {

    private OnSwipeListener<T> mListener;

    public CardItemTouchHelperCallback(OnSwipeListener<T> listener) {
        this.mListener = listener;
    }

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        int dragFlags = 0;
        int swipeFlags = 0;
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof CardLayoutManager) {
            swipeFlags = ItemTouchHelper.DOWN | ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
        }
        return makeMovementFlags(dragFlags, swipeFlags);
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        if (mListener != null) {
            mListener.onSwiped(viewHolder, direction);
        }
    }

    @Override
    public boolean isItemViewSwipeEnabled() {
        return false;
    }

    @Override
    public boolean isLongPressDragEnabled() {
        return false;
    }
}
