package com.example.RobolectricDemo;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.*;

public class CalculatorActivity extends Activity {
    private RadioGroup radioGroupOperations = null;
    private EditText firstOperandTextView = null;
    private EditText secondOperandTextView = null;
    public static final String RESULT_EXTRAS = "result";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        firstOperandTextView = (EditText) findViewById(R.id.first_operand);
        secondOperandTextView = (EditText) findViewById(R.id.second_operand);
        radioGroupOperations = (RadioGroup) findViewById(R.id.radio_group_operations);
        radioGroupOperations.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                switch (checkedId) {
                    case R.id.radio_add: makeBothOperandsVisible();
                        break;
                    case R.id.radio_factorial: hideSecondOperand();
                        break;

                }
            }
        });
    }

    private void makeBothOperandsVisible() {
        secondOperandTextView.setVisibility(View.VISIBLE);
    }

    private void hideSecondOperand() {
        secondOperandTextView.setVisibility(View.INVISIBLE);
    }

    public void performOperation(View view){
        TextView resultTextView = (TextView) findViewById(R.id.text_result);
        int resultValue;
        RadioButton factorialButton = (RadioButton) findViewById(R.id.radio_factorial);
        boolean isValid = factorialButton.isChecked() ?!isEmpty(firstOperandTextView) : !isEmpty(firstOperandTextView) && !isEmpty(secondOperandTextView) ;
        if(!isValid){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Error")
                   .setMessage("Operand Empty")
                   .setPositiveButton("OK", null)
                   .create().show();
            return;
        }
        if (factorialButton.isChecked()) {
            resultValue = 1;
            for (int i = 1; i <= integerValueFromView(firstOperandTextView); i++) {resultValue *= i;}
        } else {
            resultValue = integerValueFromView(firstOperandTextView) + integerValueFromView(secondOperandTextView);
        }
        String resultText = String.valueOf(resultValue);
        resultTextView.setText(resultText);
        Intent intent = new Intent(this, ViewResultActivity.class);
        intent.putExtra(CalculatorActivity.RESULT_EXTRAS, resultText);
        startActivity(intent);

    }

    private boolean isEmpty(EditText textView) {
        String text = textView.getText().toString();
        return text == null || TextUtils.isEmpty(text);
    }

    private int integerValueFromView(TextView view) {
        return Integer.parseInt(view.getText().toString());
    }

}
