package com.example.myapplication.list_control2;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.helper.ItemTouchHelper;

import java.util.Collections;
import java.util.List;

/**
 * Created by hulifei on 2019/5/5.
 */

public class MyItemTouchHelper extends ItemTouchHelper.Callback {
    private List list;
    private RecyclerView.Adapter adapter;

    public MyItemTouchHelper(RecyclerView.Adapter adapter, List list) {
        this.adapter = adapter;
        this.list = list;
    }


    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        int dragFlags = 0;
        int swipeFlags = 0;
        if (recyclerView.getLayoutManager() instanceof GridLayoutManager
                || recyclerView.getLayoutManager() instanceof StaggeredGridLayoutManager) {
            dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN
                    | ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
        } else {
            dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
//            swipeFlags = ItemTouchHelper.START | ItemTouchHelper.END;
        }
        return makeMovementFlags(dragFlags, swipeFlags);
    }


    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        int fromPosition = viewHolder.getAdapterPosition();
        int toPosition = target.getAdapterPosition();

        if (fromPosition < toPosition)
            //向下拖动
            for (int i = fromPosition; i < toPosition; i++) {
                Collections.swap(list, i, i + 1);
            }
        else {
            //向上拖动
            for (int i = fromPosition; i > toPosition; i--) {
                Collections.swap(list, i, i - 1);
            }
        }
        recyclerView.getAdapter().notifyItemMoved(fromPosition, toPosition);

        return true;
    }


    /**
     * 谷歌官方文档说明如下：
     * 这个看了一下主要是做左右拖动的回调
     * When a View is swiped, ItemTouchHelper animates it until it goes out of bounds, then calls onSwiped(ViewHolder, int).
     * At this point, you should update your adapter (e.g. remove the item) and call related Adapter#notify event.
     *
     * @param viewHolder
     * @param direction
     */
    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        int positon = viewHolder.getAdapterPosition();
//        adapter.notifyItemRemoved(positon);
//        list.remove(positon);

    }

    /**
     * 官方文档如下：返回true 当前tiem可以被拖动到目标位置后，直接”落“在target上，其他的上面的tiem跟着“落”，
     * 所以要重写这个方法，不然只是拖动的tiem在动，target tiem不动，静止的
     * Return true if the current ViewHolder can be dropped over the the target ViewHolder.
     *
     * @param recyclerView
     * @param current
     * @param target
     * @return
     */
    @Override
    public boolean canDropOver(RecyclerView recyclerView, RecyclerView.ViewHolder current, RecyclerView.ViewHolder target) {
        return true;
    }


    /**
     * 官方文档说明如下：
     * Returns whether ItemTouchHelper should start a drag and drop operation if an item is long pressed.
     * 是否开启长按 拖动
     *
     * @return
     */
    @Override
    public boolean isLongPressDragEnabled() {
        //return true后，可以实现长按拖动排序和拖动动画了
        return true;
    }
};
