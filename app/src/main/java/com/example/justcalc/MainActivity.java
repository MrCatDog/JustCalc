package com.example.justcalc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    //digits
    Button one;
    Button two;
    Button three;
    Button four;
    Button five;
    Button six;
    Button seven;
    Button eight;
    Button nine;
    Button zero;
    //operations
    Button add;
    Button sub;
    Button mul;
    Button div;
    //symbols
    Button braces;
    Button dot;
    //movements
    Button left;
    Button right;
    //deleting
    Button del;
    Button clear;
    //fields
    EditText expression;
    EditText answer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
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

        expression = findViewById(R.id.etExpression);
        answer = findViewById(R.id.etAnswer);

        ButtonsRespond CBR = new ButtonsRespond(this);

        add.setOnClickListener(CBR);
        sub.setOnClickListener(CBR);
        div.setOnClickListener(CBR);
        mul.setOnClickListener(CBR);

        one.setOnClickListener(CBR);
        two.setOnClickListener(CBR);
        three.setOnClickListener(CBR);
        four.setOnClickListener(CBR);
        five.setOnClickListener(CBR);
        six.setOnClickListener(CBR);
        seven.setOnClickListener(CBR);
        eight.setOnClickListener(CBR);
        nine.setOnClickListener(CBR);
        zero.setOnClickListener(CBR);

        braces.setOnClickListener(CBR);
        dot.setOnClickListener(CBR);

        del.setOnClickListener(CBR);
        clear.setOnClickListener(CBR);
        left.setOnClickListener(CBR);
        right.setOnClickListener(CBR);

        expression.addTextChangedListener(calcOnChange);
    }

    private final TextWatcher calcOnChange = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            //do nothing
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            new Thread(new Parser(getMainActivity(), getExpressionText())).start();
        }

        @Override
        public void afterTextChanged(Editable s) {
            //do nothing
        }
    };

    MainActivity getMainActivity() {
        return this;
    }

    protected void appendExpressionText(String appChar) {
        expression.append(appChar);
    }

    protected String getExpressionText() {
        return expression.getText().toString();
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
                answer.setTextColor(color);//ЭТА ЕБУЧАЯ ХУЙНЯ ВСЁ ЛОМАЕТ!!! строго заданный цвет работает.
            }
        });
    }
}
