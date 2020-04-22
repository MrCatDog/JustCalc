package com.example.justcalc;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

import static com.example.justcalc.Parser.ops;

public class JustEvaluator implements Evaluating {

      public Double Evaluate(LinkedList<String> expression) {

          Deque<Double> numbers = new ArrayDeque<>();
          double operand1, operand2;
          double result;

        for (String i: expression) {
            if(ops.containsKey(i)) {
                operand1=numbers.pop();
                operand2=numbers.pop();
                switch (ops.get(i)) {
                    case ADD:
                        result=operand1+operand2;
                        break;
                    case SUB:
                        result=operand2-operand1;
                        break;
                    case MULT:
                        result=operand1*operand2;
                        break;
                    case DIV:
                        result=operand2/operand1;
                        break;
                    default:
                        result=0;//???
                        break;
                }
                numbers.push(result);
            } else {
                numbers.push(Double.parseDouble(i));
            }
        }
        return numbers.pop();
    }
}
