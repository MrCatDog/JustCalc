package com.example.justcalc;

import android.view.View;

public class ButtonsRespond implements View.OnClickListener {

    private MainActivity mainActivity;
    private Shaker shaker;

    ButtonsRespond(MainActivity activity) {
        this.mainActivity=activity;
        this.shaker=new Shaker(mainActivity);
    }

    @Override
    public void onClick(View v) {
        int pos;
        shaker.shake();
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
                //операции
            case R.id.btnAdd:
                mainActivity.insertExpressionSymbols("+");
                break;
            case R.id.btnMult:
                mainActivity.insertExpressionSymbols("*");
                break;
            case R.id.btnSub:
                mainActivity.insertExpressionSymbols("-");
                break;
            case R.id.btnDiv:
                mainActivity.insertExpressionSymbols("/");
                break;
                //скобки и точка
            case R.id.btnBraces:
                mainActivity.insertExpressionSymbols("()");
                break;
            case R.id.btnDot:
                mainActivity.insertExpressionSymbols(".");
                break;
            //цифры
            case R.id.btnZero:
                mainActivity.insertExpressionSymbols("0");
                break;
            case R.id.btnOne:
                mainActivity.insertExpressionSymbols("1");
                break;
            case R.id.btnTwo:
                mainActivity.insertExpressionSymbols("2");
                break;
            case R.id.btnThree:
                mainActivity.insertExpressionSymbols("3");
                break;
            case R.id.btnFour:
                mainActivity.insertExpressionSymbols("4");
                break;
            case R.id.btnFive:
                mainActivity.insertExpressionSymbols("5");
                break;
            case R.id.btnSix:
                mainActivity.insertExpressionSymbols("6");
                break;
            case R.id.btnSeven:
                mainActivity.insertExpressionSymbols("7");
                break;
            case R.id.btnEight:
                mainActivity.insertExpressionSymbols("8");
                break;
            case R.id.btnNine:
                mainActivity.insertExpressionSymbols("9");
                break;
        }
    }
}
