package com.example.justcalc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    //digits
    private View one;
    private View two;
    private View three;
    private View four;
    private View five;
    private View six;
    private View seven;
    private View eight;
    private View nine;
    private View zero;
    //operations
    private View add;
    private View sub;
    private View mul;
    private View div;
    //symbols
    private View braces;
    private View dot;
    //movements
    private View left;
    private View right;
    //deleting
    private View del;
    private View clear;
    //fields
    private EditText expression;
    private TextView answer;

    private final Handler mHandler = new Handler();
    private final ButtonsRespond BR = new ButtonsRespond(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
        Trying to block soft keyboard. Tested on Sony XZ1 compact and Nexus 5x.

        android:inputType="none" - just not work
        android:editable="false" - deprecated, block copy/paste
        android:focusable="false" - block copy
        android:clickable="false" - not work

        EditText.setRawInputType(InputType.TYPE_CLASS_TEXT);
        EditText.setTextIsSelectable(true); - block copy/paste

        android:windowSoftInputMode="stateAlwaysHidden|adjustPan" - not work

        @TargetApi(21)
        EditText.setShowSoftInputOnFocus(false); - work with underline, but API must be equal or higher then 21

        Next work properly:
         */
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);

        one = findViewById(R.id.btnOne);
        two = findViewById(R.id.btnTwo);
        three = findViewById(R.id.btnThree);
        four = findViewById(R.id.btnFour);
        five = findViewById(R.id.btnFive);
        six = findViewById(R.id.btnSix);
        seven = findViewById(R.id.btnSeven);
        eight = findViewById(R.id.btnEight);
        nine = findViewById(R.id.btnNine);
        zero = findViewById(R.id.btnZero);

        add = findViewById(R.id.btnAdd);
        sub = findViewById(R.id.btnSub);
        mul = findViewById(R.id.btnMult);
        div = findViewById(R.id.btnDiv);

        braces = findViewById(R.id.btnBraces);
        dot = findViewById(R.id.btnDot);

        left = findViewById(R.id.btnLeft);
        right = findViewById(R.id.btnRight);

        del = findViewById(R.id.btnDel);
        clear = findViewById(R.id.btnClear);

        expression = findViewById(R.id.Expression);
        answer = findViewById(R.id.Answer);

        add.setOnClickListener(BR);
        sub.setOnClickListener(BR);
        div.setOnClickListener(BR);
        mul.setOnClickListener(BR);

        one.setOnClickListener(BR);
        two.setOnClickListener(BR);
        three.setOnClickListener(BR);
        four.setOnClickListener(BR);
        five.setOnClickListener(BR);
        six.setOnClickListener(BR);
        seven.setOnClickListener(BR);
        eight.setOnClickListener(BR);
        nine.setOnClickListener(BR);
        zero.setOnClickListener(BR);

        braces.setOnClickListener(BR);
        dot.setOnClickListener(BR);

        del.setOnClickListener(BR);
        clear.setOnClickListener(BR);
        left.setOnClickListener(BR);
        right.setOnClickListener(BR);

        expression.addTextChangedListener(calcOnChange);

        OnSwipeTouchListener listener = new OnSwipeTouchListener(this);

        braces.setOnTouchListener(listener);
        zero.setOnTouchListener(listener);

        expression.requestFocus();

    }

    private final TextWatcher calcOnChange = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            //do nothing
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            //This should left a less footprint, and android monitor confirmed it. but i not sure about my skills in Handlers.
            mHandler.post(new Parser(getMainActivity(), getExpressionText()));
            //new Thread(new Parser(getMainActivity(), getExpressionText())).start();
        }

        @Override
        public void afterTextChanged(Editable s) {
            //do nothing
        }
    };

    MainActivity getMainActivity() {
        return this;
    }

    protected String getExpressionText() {
        return expression.getText().toString();
    }

    void insertExpressionSymbols(String symbols) {
        expression.getText().insert(expression.getSelectionStart(),symbols);
    }

    void setAnswer(final String newAnswer) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                answer.setText(newAnswer);
            }
        });
    }

    void setAnswerColor(final int color) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                answer.setTextColor(color);
            }
        });
    }
}
