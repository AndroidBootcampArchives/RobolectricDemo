package com.example.RobolectricDemo;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CalculatorHelperTest {
    @Test
    public void shouldAddTwoIntegers() {
        int result = CalculatorHelper.add(5, 4);
        assertThat(result, is(9));

    }

    @Test
    public void shouldCalculateFactorialOfGivenNumber() {
        int result = CalculatorHelper.calculateFactorial(5);
        assertThat(result, is(120));

    }
}
