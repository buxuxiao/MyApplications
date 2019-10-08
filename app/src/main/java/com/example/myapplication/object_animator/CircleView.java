package com.example.myapplication.object_animator;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.ColorRes;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class CircleView extends View {

    private int radius = 30;

    private int xpos = 0;
    private int ypos = 0;

    public CircleView(Context context) {
        super(context);
    }

    public CircleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        xpos = getWidth() / 2;
        ypos = getHeight() / 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStrokeWidth(3f);
        paint.setStyle(Paint.Style.STROKE);

        canvas.drawCircle(xpos, ypos, radius, paint);

        Log.d("abc", this.toString());
    }

    @Override
    public String toString() {
        return String.format("x=%d,y=%d,r=%d", xpos, ypos, radius);
    }

     public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
        invalidate();
    }

    public int getR() {
        return radius;
    }

    public void setR(int radius) {
        this.radius = radius;
        invalidate();
    }

    public int getXpos() {
        return xpos;
    }

    public void setXpos(int xpos) {
        this.xpos = xpos;
        invalidate();
    }

    public int getYpos() {
        return ypos;
    }

    public void setYpos(int ypos) {
        this.ypos = ypos;
    }
}
