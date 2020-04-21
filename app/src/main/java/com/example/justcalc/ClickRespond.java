package com.example.justcalc;

import android.view.View;

public class ClickRespond implements View.OnClickListener {

    private MainActivity mainActivity;
    private String newSymbol;

    ClickRespond(MainActivity activity) {
        this.mainActivity=activity;
    }

    @Override //обработка сигналов с кнопок
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnAdd:
                newSymbol="+";
                break;
            case R.id.btnSub:
                newSymbol="-";
                break;
            case R.id.btnMult:
                newSymbol="*";
                break;
            case R.id.btnDiv:
                newSymbol="/";
                break;

            case R.id.btnBraces:
                newSymbol="()";
                break;
            case R.id.btnDot:
                newSymbol=".";
                break;

            case R.id.btnClear://отчистить всё
                mainActivity.expression.getText().clear();
                mainActivity.answer.getText().clear();
                return;
                
                //удалять символ перед курсором
            case R.id.btnDel:

                break;

                //менять положение курсора в строке выражения
            case R.id.btnLeft:

                break;
            case R.id.btnRight:

                break;

            case R.id.btnOne:
                newSymbol="1";
                break;
            case R.id.btnTwo:
                newSymbol="2";
                break;
            case R.id.btnThree:
                newSymbol="3";
                break;
            case R.id.btnFour:
                newSymbol="4";
                break;
            case R.id.btnFive:
                newSymbol="5";
                break;
            case R.id.btnSix:
                newSymbol="6";
                break;
            case R.id.btnSeven:
                newSymbol="7";
                break;
            case R.id.btnEight:
                newSymbol="8";
                break;
            case R.id.btnNine:
                newSymbol="9";
                break;
            case R.id.btnZero:
                newSymbol="0";
                break;

            default:
                break;
        }

        mainActivity.appendExpressionText(newSymbol);

        new Thread(new Parser(this.mainActivity, mainActivity.getExpressionText().toString())).start();
    }
}
