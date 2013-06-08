package com.example.RobolectricDemo;

import android.app.Activity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(RobolectricTestRunner.class)
public class ViewIssuesAdapterTest {
    ViewIssuesAdapter viewIssuesAdapter;

    @Before
    public void setUp(){
        List<Issue> issues = new ArrayList<Issue>();
        issues.add(new Issue("title1", "desc1"));
        issues.add(new Issue("title2", "desc2"));
        issues.add(new Issue("title3", "desc3"));
        issues.add(new Issue("title4", "desc4"));
        viewIssuesAdapter = new ViewIssuesAdapter(new Activity(), R.layout.issue_item, R.id.title, issues);
    }

    @Test
    public void shouldReturnCorrectView() {
        View view = viewIssuesAdapter.getView(1, new View(null), new LinearLayout(new Activity()));
        assertThat(((TextView)view).getText().toString(), equalTo("title2"));

    }
}
