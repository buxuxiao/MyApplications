package com.example.myapplication.object_animator;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.Arrays;

public class Circle {

    private int radius = 30;

    private int x = 500;
    private int y = 600;

    protected void onDraw() {
        Log.d("abc", this.toString());
    }

    @Override
    public String toString() {
        return String.format("x=%d,y=%d,r=%d", x, y, radius);
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
        onDraw();
    }

    public int[] getAbc() {
        return abc;
    }

    public void setAbc(int[] abc) {
        Log.d("abc", "abc=" + Arrays.toString(abc));
    }

    public void setAbc(int abc, int abc2) {
        Log.d("abc", "abc=" + abc + ",abc1=" + abc2);
    }

    private int[] abc;

    private PointF pointF;

    public PointF getPointF() {
        return pointF;
    }

    public void setPointF(PointF pointF) {
        this.pointF = pointF;
        Log.d("abc", pointF.toString());
    }
}
