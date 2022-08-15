package com.bignerdranch.android.draganddraw;

import android.graphics.PointF;

public class Box {
    private PointF mOrigin;
    private PointF mCurrent;
    private Float degrees;

    public Box(PointF origin) {
        mOrigin = origin;
        mCurrent = origin;
    }

    public PointF getCurrent() {
        return mCurrent;
    }

    public void setCurrent(PointF current) {
        mCurrent = current;
    }

    public PointF getOrigin() {
        return mOrigin;
    }

    public Float getDegrees() {
        return degrees;
    }

    public void setDegrees(Float degrees) {
        this.degrees = degrees;
    }
}