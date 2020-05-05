package com.example.justcalc;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.ListIterator;

import static com.example.justcalc.Parser.ops;

class ShuntingYard implements PolishTranslating{

    private static boolean isHigher(String first, String second) {
        return (ops.containsKey(first) && ops.containsKey(second) && ops.get(second).precedence >= ops.get(first).precedence);
    }

    public LinkedList<String> translate(LinkedList<String> exp) {
        ListIterator<String> iterator = exp.listIterator();
        Deque<String> operators = new ArrayDeque<>();
        LinkedList<String> answer = new LinkedList<>();
        String token;

        while (iterator.hasNext()) {
            token = iterator.next();
            if(ops.containsKey(token)) {
                while (!operators.isEmpty() && isHigher(token, operators.peek()))
                    answer.add(operators.pop());
                operators.push(token);
            }

            //left brace
            else if(token.equals("(")) {
                operators.push(token);
            }

            //right brace
            else if(token.equals(")")) {
                while(!"(".equals(operators.peek()))
                    answer.add(operators.pop());
                operators.pop();
            }

            //digit
            else answer.add(token);
        }
        while(!operators.isEmpty())
            answer.add(operators.pop());

        return answer;
    }
}
