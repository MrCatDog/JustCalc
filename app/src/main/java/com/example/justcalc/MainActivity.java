package com.example.justcalc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
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

        add.setOnClickListener(new ClickRespond(this));
        sub.setOnClickListener(new ClickRespond(this));
        div.setOnClickListener(new ClickRespond(this));
        mul.setOnClickListener(new ClickRespond(this));

        one.setOnClickListener(new ClickRespond(this));
        two.setOnClickListener(new ClickRespond(this));
        three.setOnClickListener(new ClickRespond(this));
        four.setOnClickListener(new ClickRespond(this));
        five.setOnClickListener(new ClickRespond(this));
        six.setOnClickListener(new ClickRespond(this));
        seven.setOnClickListener(new ClickRespond(this));
        eight.setOnClickListener(new ClickRespond(this));
        nine.setOnClickListener(new ClickRespond(this));
        zero.setOnClickListener(new ClickRespond(this));

        braces.setOnClickListener(new ClickRespond(this));
        dot.setOnClickListener(new ClickRespond(this));
        del.setOnClickListener(new ClickRespond(this));
        clear.setOnClickListener(new ClickRespond(this));
        left.setOnClickListener(new ClickRespond(this));
        right.setOnClickListener(new ClickRespond(this));
    }

    protected void appendExpressionText(String appChar) {
        expression.append(appChar);
    }

    protected Editable getExpressionText() {
        return expression.getText();
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
