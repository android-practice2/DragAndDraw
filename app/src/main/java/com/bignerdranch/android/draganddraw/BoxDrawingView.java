package com.bignerdranch.android.draganddraw;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class BoxDrawingView  extends View {
    private static final String TAG = "BoxDrawingView";
    public static final String ARG_SUPER = "PARENT";
    public static final String ARG_BOXEN = "arg_mBoxen";
    private Box mCurrentBox;
    private ArrayList<Box> mBoxen = new ArrayList<>();

    private Paint mBackgroundPaint;
    private Paint mBoxPaint;

    public BoxDrawingView(Context context) {
        super(context);

    }

    public BoxDrawingView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        mBackgroundPaint = new Paint();
        mBackgroundPaint.setColor(0xfff8efe0);

        mBoxPaint = new Paint();
        mBoxPaint.setColor(0x22ff0000);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);

        PointF current = new PointF(event.getX(), event.getY());
        String action = "";
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                action = "ACTION_DOWN";
                mCurrentBox = new Box(current);
                mBoxen.add(mCurrentBox);
                break;

            case MotionEvent.ACTION_MOVE:
                action = "ACTION_MOVE";
                if (mCurrentBox != null) {
                    mCurrentBox.setCurrent(current);
                    invalidate();

                }

                break;
            case MotionEvent.ACTION_UP:
                action = "ACTION_UP";
                mCurrentBox=null;
                break;

            case MotionEvent.ACTION_CANCEL:
                action = "ACTION_CANCEL";
                mCurrentBox=null;

                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                action = "ACTION_POINTER_DOWN";

                break;

            case  MotionEvent.ACTION_POINTER_UP:
                action = "ACTION_POINTER_UP";

                break;
            default:
                action = event.getActionMasked() + "";
                break;

        }

        PointerInfo pointerInfo = new PointerInfo(event);

        Log.i(TAG, action + " at x=" + current.x
                + ", y=" + current.y
                +" "+pointerInfo.toString()
        );

        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPaint(mBackgroundPaint);
        for (Box box : mBoxen) {
            PointF current = box.getCurrent();
            PointF origin = box.getOrigin();
            float left=Math.min(origin.x,current.x);
            float right=Math.max(origin.x,current.x);
            float top=Math.min(origin.y,current.y);
            float bottom=Math.max(origin.y,current.y);

            canvas.save();
            if (box.getDegrees() != null) {
                canvas.rotate(box.getDegrees());
            }

            canvas.drawRect(left, top, right, bottom, mBoxPaint);
            canvas.restore();
        }


    }

    @Nullable
    @Override
    protected Parcelable onSaveInstanceState() {//must have view id ,i.e. in xml
        Parcelable superParcelable = super.onSaveInstanceState();

        Bundle bundle = new Bundle();
        bundle.putParcelable(ARG_SUPER, superParcelable);
        bundle.putSerializable(ARG_BOXEN, mBoxen);

        return bundle;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        Bundle bundle=(Bundle)state;
        super.onRestoreInstanceState(bundle.getParcelable(ARG_SUPER));
        mBoxen = (ArrayList<Box>) bundle.getSerializable(ARG_BOXEN);
    }
}
