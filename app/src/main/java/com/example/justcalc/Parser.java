package com.example.justcalc;

import androidx.core.content.ContextCompat;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;


public class Parser implements Runnable {
    private Character[] textByCharacters;
    private LinkedList<String> answer = new LinkedList<>();
    private WeakReference<MainActivity> mainActivityWeakReference;
    private Evaluating Evaluator = new JustEvaluator();
    private PolishTranslating Translator = new ShuntingYard();

    enum Operator {
        ADD(1),SUB(2),MULT(3),DIV(4);
        final int precedence;
        Operator(int p) {precedence=p;}
    }

    static Map<String, Operator> ops = new HashMap<String, Operator>()
    {{
        put("+", Operator.ADD);
        put("-", Operator.SUB);
        put("*", Operator.MULT);
        put("/", Operator.DIV);
    }};

    Parser(MainActivity mainActivity, String text) {
        mainActivityWeakReference = new WeakReference<>(mainActivity);
        textByCharacters = new Character[text.length()];
        char[] chars = text.toCharArray();
        for(int i=0;i<text.length();i++)
            textByCharacters[i]=(Character) chars[i]; //так ли "redurant" явное преобразование, если я хочу оставить его более явным для читающего?
    }

    @Override
    public void run() {
        String element = "";
        int braceCount = 0;
        boolean wasDot = false;

        for(Character i:textByCharacters) {
            //operation
            if(ops.containsKey(i.toString())) {

                if(!element.isEmpty()) {
                    answer.add(element); //если мы считывали число, то запишем его.
                    wasDot=false;
                    element=""; //и обнулим.
                    answer.add(i.toString());
                } else {
                    setAnswerColor(R.color.answerWrongExpressionTextColor);
                    return;
                }

            //parenthesis
            } else if(i.equals('(') || i.equals(')')) {
                if(!element.isEmpty()) {
                    setAnswerColor(R.color.answerWrongExpressionTextColor);
                    return;
                }
                if(i.equals('('))
                    braceCount++;
                else
                    braceCount--;
                answer.add(i.toString());

            //digit or dot
            } else {
                if(i.equals('.')) {
                    if(wasDot || element.isEmpty()) {//is the dot rightful here?
                        setAnswerColor(R.color.answerWrongExpressionTextColor);
                        return;
                    } else
                        wasDot=true;//no more points in this number.
                }
                element+=i.toString();
            }
        }

        if(!element.isEmpty())//если выражение кончается скобкой?
            answer.add(element);
        else {
            setAnswerColor(R.color.answerWrongExpressionTextColor);
            return;
        }

        if(braceCount!=0) {
            setAnswerColor(R.color.answerWrongExpressionTextColor);
        } else {
            setAnswerColor(R.color.answerTextColor);
            sendAnswer(Evaluator.Evaluate(Translator.translate(answer)).toString());
        }

    }

    private void sendAnswer(String answer) {
        final MainActivity activity = mainActivityWeakReference.get();
        activity.setAnswer(answer);
    }

    private void setAnswerColor(int color) {
        final MainActivity activity = mainActivityWeakReference.get();
        activity.setAnswerColor(ContextCompat.getColor(activity, color));//ContextCompact - deprecated
    }
}
