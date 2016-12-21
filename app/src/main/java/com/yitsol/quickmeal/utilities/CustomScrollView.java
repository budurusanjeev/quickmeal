package com.yitsol.quickmeal.utilities;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

public class CustomScrollView extends ScrollView {
    //private GestureDetector mGestureDetector;
    //View.OnTouchListener mGestureListener;
    private float xDistance, yDistance, lastX, lastY;

    public CustomScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
       /* mGestureDetector = new GestureDetector(context, new YScrollDetector());
        setFadingEdgeLength(0);*/
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                xDistance = yDistance = 0f;
                lastX = ev.getX();
                lastY = ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                final float curX = ev.getX();
                final float curY = ev.getY();
                xDistance += Math.abs(curX - lastX);
                yDistance += Math.abs(curY - lastY);
                lastX = curX;
                lastY = curY;
                if (xDistance > yDistance)
                    return false;
        }

        return super.onInterceptTouchEvent(ev);
    }
}