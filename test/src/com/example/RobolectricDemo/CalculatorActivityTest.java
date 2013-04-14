package com.example.RobolectricDemo;

import android.view.View;
import android.widget.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.shadows.ShadowActivity;
import org.robolectric.shadows.ShadowIntent;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;
import static org.robolectric.Robolectric.shadowOf;

@RunWith(RobolectricTestRunner.class)
public class CalculatorActivityTest {
    CalculatorActivity calculatorActivity;
    EditText firstOperandTextView;
    EditText secondOperandTextView;
    RadioGroup radioGroupOperations;
    RadioButton addRadioButton;
    RadioButton factorialRadioButton;
    TextView resultTextView;
    Button resultBtnView;

    @Before
    public void setUp(){
        calculatorActivity = new CalculatorActivity();
        calculatorActivity.onCreate(null);
        firstOperandTextView = (EditText)calculatorActivity.findViewById(R.id.first_operand);
        secondOperandTextView = (EditText)calculatorActivity.findViewById(R.id.second_operand);
        radioGroupOperations = (RadioGroup) calculatorActivity.findViewById(R.id.radio_group_operations);
        addRadioButton = (RadioButton) calculatorActivity.findViewById(R.id.radio_add);
        factorialRadioButton = (RadioButton) calculatorActivity.findViewById(R.id.radio_factorial);
        resultTextView = (TextView) calculatorActivity.findViewById(R.id.text_result);
        resultBtnView = (Button) calculatorActivity.findViewById(R.id.btn_result);
    }

    @Test
    public void shouldHaveCorrectAppName() {
        assertThat(calculatorActivity.getResources().getString(R.string.app_name), equalTo("RobolectricDemo"));
    }

    @Test
    public void shouldHaveTextboxForFirstOperand(){
        assertThat(firstOperandTextView.getVisibility(), equalTo(View.VISIBLE));
    }

    @Test
    public void shouldHaveTextboxForSecondOperand(){
        assertThat(secondOperandTextView.getVisibility(), equalTo(View.VISIBLE));
    }

    @Test
    public void shouldHaveResultTextBox() {
        assertThat(resultTextView.getVisibility(), equalTo(View.VISIBLE));
    }

    @Test
    public void shouldHaveResultButton() {
        assertThat(resultBtnView.getVisibility(), equalTo(View.VISIBLE));
    }

    @Test
    public void shouldHaveCorrectTextOnResultButton() {
        assertThat(resultBtnView.getText().toString(), equalTo("Result"));
    }
    @Test
    public void shouldHaveAddRadioButton() {
        assertThat(addRadioButton.getVisibility(), equalTo(View.VISIBLE));

    }

    @Test
    public void shouldHaveFactorialRadioButton() {
        assertThat(factorialRadioButton.getVisibility(), equalTo(View.VISIBLE));

    }

    @Test
    public void shouldHaveAddRadioButtonCheckedByDefault() {
        assertTrue(addRadioButton.isChecked());
        assertFalse(factorialRadioButton.isChecked());
    }

    @Test
    public void shouldHaveOnlyFirstOperandForFactorialOperation() {
        factorialRadioButton.setChecked(true);
        assertThat(secondOperandTextView.getVisibility(), equalTo(View.GONE));
    }

//    @Test
//    public void shouldTakeOnlyNumbersForFirstOperand() {
//        firstOperandTextView.setText("123");
//        assertThat(firstOperandTextView.getText().toString(), equalTo("123"));
//
//        View.OnKeyListener onKeyListener = new View.OnKeyListener() {
//            @Override
//            public boolean onKey(View view, int i, KeyEvent keyEvent) {
//                return true;
//            }
//        };
//        firstOperandTextView.setOnKeyListener(onKeyListener);
//        boolean b;
//        b = onKeyListener.onKey(firstOperandTextView, KeyEvent.KEYCODE_B, new KeyEvent(ACTION_UP));
//
////        firstOperandTextView.setText("abc");
//        assertThat(firstOperandTextView.getText().toString(), equalTo(""));
//    }

    @Test
    public void shouldDisplayAdditionResultOnClickOfResultButton() {
        firstOperandTextView.setText("12");
        secondOperandTextView.setText("21");
        addRadioButton.setChecked(true);
        resultBtnView.performClick();
        assertThat(resultTextView.getText().toString(), equalTo("33"));
    }

    @Test
    public void shouldDisplayFactorialResultOnClickOfResultButton() {
        firstOperandTextView.setText("5");
        secondOperandTextView.setText("21");
        factorialRadioButton.setChecked(true);
        resultBtnView.performClick();
        assertThat(resultTextView.getText().toString(), equalTo("120"));

    }

    @Test
    public void shouldStartViewResultActivityOnClickOfResultButton() {
        firstOperandTextView.setText("12");
        secondOperandTextView.setText("21");
        addRadioButton.setChecked(true);
        resultBtnView.performClick();
        ShadowActivity calculatorShadowActivity = shadowOf(calculatorActivity);
        ShadowIntent shadowIntent = shadowOf(calculatorShadowActivity.getNextStartedActivity());
        assertThat(shadowIntent.getComponent().getClassName(), equalTo(ViewResultActivity.class.getName()));
        assertThat(shadowIntent.getStringExtra(CalculatorActivity.RESULT_EXTRAS), equalTo("33"));
    }


}
