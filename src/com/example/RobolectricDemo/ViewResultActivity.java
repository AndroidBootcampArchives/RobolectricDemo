package com.example.RobolectricDemo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class ViewResultActivity extends Activity {

    TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_result);
        resultTextView = (TextView) findViewById(R.id.view_result);
        setResultFromIntent();
    }

    private void setResultFromIntent() {
        String result = getIntent().getStringExtra(CalculatorActivity.RESULT_EXTRAS);
        resultTextView.setText(result);
    }
}
