package com.example.justcalc;

import java.util.LinkedList;

public interface PolishTranslating {
    LinkedList<String> translate(LinkedList<String> exp);
}
