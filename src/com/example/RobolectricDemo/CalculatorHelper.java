package com.example.RobolectricDemo;

public class CalculatorHelper {
    public static int calculateFactorial(int number) {
        int resultValue = 1;
        for (int i = 1; i <= number; i++) {
            resultValue *= i;
        }
        return resultValue;
    }

    public static int add(int number1, int number2) {
        return number1 + number2;
    }

}

