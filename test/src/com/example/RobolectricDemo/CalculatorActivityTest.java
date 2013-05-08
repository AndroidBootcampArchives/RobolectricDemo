package com.example.RobolectricDemo;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;
import android.widget.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.shadows.ShadowActivity;
import org.robolectric.shadows.ShadowAlertDialog;
import org.robolectric.shadows.ShadowIntent;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
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
        addRadioButton = (RadioButton) calculatorActivity.findViewById(R.id.radio_add);
        factorialRadioButton = (RadioButton) calculatorActivity.findViewById(R.id.radio_factorial);
        resultBtnView = (Button) calculatorActivity.findViewById(R.id.btn_result);
        resultTextView = (TextView) calculatorActivity.findViewById(R.id.text_result);
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
        assertTrue(addRadioButton.isChecked());
    }

    @Test
    public void shouldHaveOnlyFirstOperandForFactorialOperation() {
        factorialRadioButton.setChecked(true);
        assertThat(firstOperandTextView.getVisibility(), equalTo(View.VISIBLE));
        assertThat(secondOperandTextView.getVisibility(), equalTo(View.INVISIBLE));
    }

    @Test
    public void shouldHaveBothOperandVisibleForAddOperation() {
        factorialRadioButton.setChecked(true);
        addRadioButton.setChecked(true);
        assertThat(firstOperandTextView.getVisibility(), equalTo(View.VISIBLE));
        assertThat(secondOperandTextView.getVisibility(), equalTo(View.VISIBLE));
    }

    @Test
    public void shouldDisplayAdditionResultOnClickOfResultButton() {
        firstOperandTextView.setText("5");
        secondOperandTextView.setText("12");
        resultBtnView.performClick();
        assertThat(resultTextView.getText().toString(), equalTo("17"));
    }

    @Test
    public void shouldDisplayFactorialResultOnClickOfResultButton() {
        factorialRadioButton.setChecked(true);
        firstOperandTextView.setText("4");
        resultBtnView.performClick();
        assertThat(resultTextView.getText().toString(), equalTo("24"));
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
        addRadioButton.setChecked(true);
        resultBtnView.performClick();
        AlertDialog latestAlertDialog = ShadowAlertDialog.getLatestAlertDialog();
        ShadowAlertDialog shadowAlertDialog = shadowOf(latestAlertDialog);
        assertThat(shadowAlertDialog.getTitle().toString(), equalTo("Error"));
        assertThat(shadowAlertDialog.getMessage(), equalTo("Operand Empty"));
        assertThat(shadowAlertDialog.getButton(DialogInterface.BUTTON_POSITIVE).getText().toString(), equalTo("OK"));
    }

}
