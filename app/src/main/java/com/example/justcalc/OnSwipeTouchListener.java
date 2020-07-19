package com.example.justcalc;

import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

public class OnSwipeTouchListener implements View.OnTouchListener {

    private final GestureDetector gestureDetector;
    private final MainActivity mainActivity;
    private int whoTouched;

    OnSwipeTouchListener(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        gestureDetector = new GestureDetector(mainActivity, new GestureListener());
    }

    public boolean onTouch(View v, MotionEvent event) {
        whoTouched = v.getId();
        return gestureDetector.onTouchEvent(event);
    }

    private void onSwipeUp() {
        switch (whoTouched) {
            case R.id.btnBraces:
                mainActivity.insertExpressionSymbols("(");
                break;
            case R.id.btnZero:
                mainActivity.insertExpressionSymbols("000");
                break;
                default:
                    //chill
                    break;
        }
    }

    private void onSwipeDown() {
        switch (whoTouched) {
            case R.id.btnBraces:
                mainActivity.insertExpressionSymbols(")");
                break;
            case R.id.btnZero:
                mainActivity.insertExpressionSymbols("00");
                break;
            default:
                //chill
                break;
        }
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