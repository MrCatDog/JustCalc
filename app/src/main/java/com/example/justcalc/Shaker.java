package com.example.justcalc;

import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;

import static android.content.Context.VIBRATOR_SERVICE;


class Shaker {

    private static final int defaultMillis = 70;
    private MainActivity mainActivity;

    Shaker(MainActivity mainActivity) {
        this.mainActivity=mainActivity;
    }

    // Vibrate
    void shake(int millis) {
        if (Build.VERSION.SDK_INT >= 26) {
            ((Vibrator) mainActivity.getSystemService(VIBRATOR_SERVICE)).vibrate(VibrationEffect.createOneShot(70, VibrationEffect.DEFAULT_AMPLITUDE));
        } else {
            ((Vibrator) mainActivity.getSystemService(VIBRATOR_SERVICE)).vibrate(70);
        }
    }

    void shake() {
        shake(defaultMillis);
    }
}
