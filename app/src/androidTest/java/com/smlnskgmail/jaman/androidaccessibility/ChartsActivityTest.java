package com.smlnskgmail.jaman.androidaccessibility;

import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import com.smlnskgmail.jaman.androidaccessibility.activities.ChartsActivity;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@LargeTest
public class ChartsActivityTest {
    @Rule
    public ActivityTestRule<ChartsActivity> mActivityRule =
            new ActivityTestRule<>(ChartsActivity.class);

    @Test
    public void checkChartsAreVisible() {
        onView(withId(R.id.charts_line_chart)).check(matches(isDisplayed()));
        onView(withId(R.id.charts_bar_chart)).check(matches(isDisplayed()));
    }

}
