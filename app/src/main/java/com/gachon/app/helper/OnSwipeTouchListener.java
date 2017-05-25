package com.gachon.app.helper;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

/**
 * Created by garyNoh on 2017. 5. 25..
 */

public class OnSwipeTouchListener implements View.OnTouchListener {

    private final GestureDetector gestureDetector;
    Context context;

    public OnSwipeTouchListener(Context context) {
        gestureDetector = new GestureDetector(context, new GestureListener());
        this.context = context;
    }

    public void onSwipeLeft() {
        Toast.makeText(context, "swipe left", Toast.LENGTH_SHORT).show();
    }

    public void onSwipeRight() {
        Toast.makeText(context, "swipe right", Toast.LENGTH_SHORT).show();
    }

    public boolean onTouch(View v, MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }

    private final class GestureListener extends GestureDetector.SimpleOnGestureListener {

        private static final int SWIPE_DISTANCE_THRESHOLD = 100;
        private static final int SWIPE_VELOCITY_THRESHOLD = 100;

        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            float distanceX = e2.getX() - e1.getX();
            float distanceY = e2.getY() - e1.getY();
            if (Math.abs(distanceX) > Math.abs(distanceY) && Math.abs(distanceX) > SWIPE_DISTANCE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                if (distanceX > 0)
                    onSwipeRight();
                else
                    onSwipeLeft();
                return true;
            }
            return false;
        }
    }
}