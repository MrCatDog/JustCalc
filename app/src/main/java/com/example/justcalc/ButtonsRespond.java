package com.example.justcalc;

import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import static android.content.Context.VIBRATOR_SERVICE;

public class ButtonsRespond implements View.OnClickListener {

    private MainActivity mainActivity;

    ButtonsRespond(MainActivity activity) {
        this.mainActivity=activity;
    }

    @Override
    public void onClick(View v) {
        int pos;
        shake();
        switch (v.getId()) {
            //отчистить всё
            case R.id.btnClear:
                mainActivity.expression.setText(null);
                mainActivity.answer.setText(null);
                break;

            //удалять символ перед курсором
            case R.id.btnDel:
                pos=mainActivity.expression.getSelectionStart();
                if(pos>0)
                    mainActivity.expression.getText().replace(pos-1,pos,"");
                break;

            //менять положение курсора в строке выражения
            case R.id.btnLeft:
                pos = mainActivity.expression.getSelectionStart();
                if (pos > 0)
                    mainActivity.expression.setSelection(pos - 1);
                break;

            case R.id.btnRight:
                pos = mainActivity.expression.getSelectionEnd();
                if (pos < mainActivity.expression.getText().length())
                    mainActivity.expression.setSelection(pos + 1);
                break;
            //добавить символы с кнопки
                /*
                Такая реализация привела к зависимости логики работы от дизайна (текст на кнопках).
                Однако, это пораждает ситуацию, когда именно то, что написанно на кнопке,
                попадает в исходное выражение, делая опыт использования максимально прозрачным,
                к тому же упрощая код.
                В случае, если потребуется разместить на кнопках иной текст, этот обработчик
                придётся переписать.
                */
            default:
                TextView tv = (TextView)((ViewGroup) v).getChildAt(1);
                mainActivity.insertExpressionSymbols(tv.getText().toString());
                break;
        }
    }

    // Vibrate
    private void shake() {
        if (Build.VERSION.SDK_INT >= 26) {
            ((Vibrator) mainActivity.getSystemService(VIBRATOR_SERVICE)).vibrate(VibrationEffect.createOneShot(70, VibrationEffect.DEFAULT_AMPLITUDE));
        } else {
            ((Vibrator) mainActivity.getSystemService(VIBRATOR_SERVICE)).vibrate(70);
        }
    }
}
