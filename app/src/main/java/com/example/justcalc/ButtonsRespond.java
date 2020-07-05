package com.example.justcalc;

import android.view.View;

public class ButtonsRespond implements View.OnClickListener {

    private MainActivity mainActivity;
    private int pos;
    private String newSymbol;

    ButtonsRespond(MainActivity activity) {
        this.mainActivity=activity;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //отчистить всё
            case R.id.btnClear:
                mainActivity.expression.setText(null);
                mainActivity.answer.getText().clear();
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
                default:
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
                            newSymbol="";
                            break;
                    }
                    mainActivity.expression.getText().insert(mainActivity.expression.getSelectionStart(),newSymbol);
                    break;
        }
    }
}
