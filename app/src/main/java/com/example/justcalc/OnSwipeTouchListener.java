package com.example.justcalc;

import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

public class OnSwipeTouchListener implements View.OnTouchListener {

    private final GestureDetector gestureDetector;
    private final MainActivity mainActivity;

    public OnSwipeTouchListener(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        gestureDetector = new GestureDetector(mainActivity, new GestureListener());
    }

    private void onSwipeUp() {
        mainActivity.insertExpressionSymbols("(");
    }

    private void onSwipeDown() {
        mainActivity.insertExpressionSymbols(")");
    }

    public boolean onTouch(View v, MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }

    private final class GestureListener extends GestureDetector.SimpleOnGestureListener {

        private static final int SWIPE_DISTANCE_THRESHOLD = 100;
        private static final int SWIPE_VELOCITY_THRESHOLD = 100;

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            float distanceX = e2.getX() - e1.getX();
            float distanceY = e2.getY() - e1.getY();
            if (Math.abs(distanceY) > Math.abs(distanceX) && Math.abs(distanceY) > SWIPE_DISTANCE_THRESHOLD && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
                if (distanceY < 0)
                    onSwipeUp();
                else
                    onSwipeDown();
                return true;
            }
            return false;
        }
    }
}