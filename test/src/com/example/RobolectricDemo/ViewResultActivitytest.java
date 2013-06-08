package com.example.RobolectricDemo;

import android.widget.TextView;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(RobolectricTestRunner.class)
public class ViewResultActivitytest {
    TextView resultTextView;

    @Before
    public void setUp(){
        //instantiate the necessary activity with a intent containing the result as an extra.
    }
    @Test
    public void shouldDisplayCorrectResultOnTextBox() {
        assertThat(resultTextView.getText().toString(), equalTo("25"));
    }
}
