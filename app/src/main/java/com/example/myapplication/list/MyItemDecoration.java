package com.example.myapplication.list;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

/**
 * Created by hulifei on 2019/4/29.
 */

public class MyItemDecoration extends RecyclerView.ItemDecoration {

    private int mHeight = 55;
    private Paint mPaint = new Paint();

    private int mGroupHeight=55;
    private Paint mGroutPaint,mTextPaint;

    public MyItemDecoration() {
        mPaint.setColor(0xffff0000);
        mPaint.setTextSize(30);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(1);

        mGroutPaint=mPaint;
        mTextPaint=new Paint(mPaint);
        mTextPaint.setColor(0xff00ff00);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        int pos = parent.getChildAdapterPosition(view);
        if (pos % 10 == 0) {
            outRect.top = mHeight;
        }
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        final int left = parent.getLeft();
        final int right = parent.getRight();
        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View childView = parent.getChildAt(i);
            int pos = parent.getChildAdapterPosition(childView);
            if (pos % 10 == 0) {
                final int bottom = childView.getTop();
                final int top = bottom - mHeight;
               // c.drawText("index of " + pos / 10, left, bottom, mPaint);
            }
        }
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);


        final int itemCount = state.getItemCount();
        final int childCount = parent.getChildCount();
        final int left = parent.getLeft() + parent.getPaddingLeft();
        final int right = parent.getRight() - parent.getPaddingRight();
        String preGroupName;      //标记上一个item对应的Group
        String currentGroupName = null;       //当前item对应的Group
        for (int i = 0; i < childCount; i++) {
            View view = parent.getChildAt(i);
            int position = parent.getChildAdapterPosition(view);
            preGroupName = currentGroupName;
            currentGroupName = getGroupName(position);
            if (currentGroupName == null || TextUtils.equals(currentGroupName, preGroupName))
                continue;
            int viewBottom = view.getBottom();
            float top = Math.max(mGroupHeight, view.getTop());//top 决定当前顶部第一个悬浮Group的位置
           if (position + 1 < itemCount) {
                //获取下个GroupName
                String nextGroupName = getGroupName(position + 1);
                //下一组的第一个View接近头部
                if (!currentGroupName.equals(nextGroupName) && viewBottom < top) {
                    top = viewBottom;
                }
           }
            //根据top绘制group
            c.drawRect(left, top - mGroupHeight, right, top, mGroutPaint);
          /*Paint.FontMetrics fm = mTextPaint.getFontMetrics();
            //文字竖直居中显示
            float baseLine = top - (mGroupHeight - (fm.bottom - fm.top)) / 2 - fm.bottom;
            c.drawText(currentGroupName, left + 0, baseLine, mTextPaint);*/
        }

    }

    private String getGroupName(int pos) {
        return "index of " + pos / 10;
    }


}
