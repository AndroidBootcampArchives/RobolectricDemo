package com.example.RobolectricDemo;

import android.content.Intent;
import android.widget.TextView;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.robolectric.Robolectric.buildActivity;

@RunWith(RobolectricTestRunner.class)
public class ViewResultActivitytest {
    TextView resultTextView;

    @Before
    public void setUp(){
        Intent intent = new Intent(Robolectric.application, ViewResultActivity.class).putExtra(CalculatorActivity.RESULT_EXTRAS, "25");
        ViewResultActivity viewResultActivity = buildActivity(ViewResultActivity.class).withIntent(intent).create().get();
        resultTextView = (TextView) viewResultActivity.findViewById(R.id.view_result);
    }
    @Test
    public void shouldDisplayCorrectResultOnTextBox() {
        assertThat(resultTextView.getText().toString(), equalTo("25"));
    }
}
