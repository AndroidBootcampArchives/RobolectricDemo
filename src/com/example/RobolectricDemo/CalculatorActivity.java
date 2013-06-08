package com.example.RobolectricDemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;

public class CalculatorActivity extends Activity {
    RadioGroup radioGroupOperations = null;
    EditText firstOperandTextView = null;
    EditText secondOperandTextView = null;
    public static final String RESULT_EXTRAS = "result";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
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
    }

    private void hideSecondOperand() {
    }

    public void performOperation(View view){

    }

}
