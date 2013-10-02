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
import static org.robolectric.Robolectric.buildActivity;
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
        calculatorActivity = buildActivity(CalculatorActivity.class).create().get();
        firstOperandTextView = (EditText)calculatorActivity.findViewById(R.id.first_operand);
       //inflate other views taking the above one as example.
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
    public void shouldHaveAddRadioButtonCheckedByDefault() {
    }

    @Test
    public void shouldHaveOnlyFirstOperandForFactorialOperation() {
        //check the factorial radio button
        //assert on the visibility of the secondOperand
    }

    @Test
    public void shouldDisplayAdditionResultOnClickOfResultButton() {
        // set the values in for both the operands
        //click the result button
        //assert the result
    }

    @Test
    public void shouldDisplayFactorialResultOnClickOfResultButton() {
        firstOperandTextView.setText("5");
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

    @Test
    public void shouldPopAnAlertForNoOperandEntered() {
       // set the operands as blank
        //click the result button
        //assert on the alert dialog with a particular title and message.
    }

}
