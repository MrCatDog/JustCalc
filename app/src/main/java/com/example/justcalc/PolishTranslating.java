package com.example.justcalc;

import java.util.LinkedList;

@FunctionalInterface
public interface PolishTranslating {
    LinkedList<String> translate(LinkedList<String> exp);
}
