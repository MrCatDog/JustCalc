package com.example.justcalc;

import androidx.core.content.ContextCompat;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;


public class Parser implements Runnable {

    private final Character[] textByCharacters;
    private final LinkedList<String> answer = new LinkedList<>();
    private final WeakReference<MainActivity> mainActivityWeakReference;
    private final Evaluating Evaluator = new JustEvaluator();
    private final PolishTranslating Translator = new ShuntingYard();
    private String element;
    private int braceCount;
    private boolean wasDot;

    enum Operator {
        ADD(1), SUB(2), MULT(3), DIV(4);
        final int precedence;

        Operator(int p) {
            precedence = p;
        }
    }

    static Map<String, Operator> ops = new HashMap<String, Operator>() {{
        put("+", Operator.ADD);
        put("-", Operator.SUB);
        put("*", Operator.MULT);
        put("/", Operator.DIV);
    }};

    Parser(MainActivity mainActivity, String text) {
        element = "";
        braceCount = 0;
        wasDot = false;
        mainActivityWeakReference = new WeakReference<>(mainActivity);
        textByCharacters = new Character[text.length()];
        char[] chars = text.toCharArray();
        for (int i = 0; i < text.length(); i++)
            textByCharacters[i] = (Character) chars[i]; //так ли "redurant" явное преобразование, если я хочу оставить его более явным для читающего?
    }

    @Override
    public void run() {
        for (Character i : textByCharacters) {
            //operation
            if (ops.containsKey(i.toString())) {
                if (addElem() || (!answer.isEmpty() && answer.getLast().equals(")"))) {
                    answer.add(i.toString());
                } else {
                    if (ops.get(i.toString()).equals(Operator.SUB)) {
                        element = "-";
                        continue;
                    }
                    setAnswerColor(R.color.answerWrongExpressionTextColor);
                    return;
                }
            } else {
                switch (i) {
                    //parenthesis
                    case '(':
                        braceCount++;
                        if (addElem())
                            answer.add("*");
                        break;

                    case ')':
                        if (!addElem()) {
                            setAnswerColor(R.color.answerWrongExpressionTextColor);
                            return;
                        }
                        braceCount--;
                        break;

                    //dot
                    case '.':
                        if (wasDot || element.isEmpty()) {//is the dot rightful here?
                            setAnswerColor(R.color.answerWrongExpressionTextColor);
                            return;
                        } else
                            wasDot = true;//no more dots in this number.
                        //заметь, break тут нет, выполнение идёт дальше. Отлаживать, конечно, труднее, зато я сэкономил 2-3 строки кода. Meh.
                        //number
                    default:
                        element = element.concat(i.toString());
                        continue;
                }
                answer.add(i.toString());
            }
        }

        if (!addElem() && !answer.isEmpty())//check last symbol
            if (ops.containsKey(answer.getLast())) {//if it an operation
                setAnswerColor(R.color.answerWrongExpressionTextColor);//stop
                return;
            }

        //почему не сразу после цикла? хотелось оставить эту фишку, когда число сразу пишется в ответ
        if (answer.isEmpty())
            return;

        if (braceCount != 0) {
            setAnswerColor(R.color.answerWrongExpressionTextColor);
            return;
        }

        setAnswerColor(R.color.answerTextColor);
        sendAnswer(Evaluator.Evaluate(Translator.translate(answer)));
    }

    private boolean addElem() {
        if (!element.isEmpty() && !element.equals("-")) {
            answer.add(element); //если мы считывали число, то запишем его.
            wasDot = false;
            element = ""; //и обнулим.
            return true;
        }
        return false;
    }

    private void sendAnswer(Double answer) {
        final MainActivity activity = mainActivityWeakReference.get();
        if ((answer == Math.floor(answer)) && !Double.isInfinite(answer) && answer <= Long.MAX_VALUE && answer >= Long.MIN_VALUE)
            activity.setAnswer(Long.toString(answer.longValue()));// integer type
        else
            activity.setAnswer(answer.toString());
    }

    private void setAnswerColor(int color) {
        final MainActivity activity = mainActivityWeakReference.get();
        activity.setAnswerColor(ContextCompat.getColor(activity, color));
    }
}
