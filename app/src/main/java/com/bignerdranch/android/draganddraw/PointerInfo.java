package com.bignerdranch.android.draganddraw;

import android.view.MotionEvent;

public class PointerInfo {
    private Integer pointerCount;
    private Integer actionIndex;
    private Integer actionMasked;
    private Integer pointerId;

    public PointerInfo(MotionEvent event) {
        pointerCount = event.getPointerCount();
        actionIndex = event.getActionIndex();
        actionMasked = event.getActionMasked();
        pointerId = event.getPointerId(actionIndex);
    }

    @Override
    public String toString() {
        return "{" +
                "pointerCount=" + pointerCount +
                ", actionIndex=" + actionIndex +
                ", actionMasked=" + actionMasked +
                ", pointerId=" + pointerId +
                '}';
    }
}
