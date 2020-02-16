package com.smlnskgmail.jaman.androidaccessibility;

import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import com.smlnskgmail.jaman.androidaccessibility.activities.CustomViewActivity;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@LargeTest
public class CustomViewActivityTest {
    @Rule
    public ActivityTestRule<CustomViewActivity> mActivityRule =
            new ActivityTestRule<>(CustomViewActivity.class);

    @Test
    public void checkCustomViewIsVisible() {
        onView(withId(R.id.custom_view_simple_custom_view)).check(matches(isDisplayed()));
    }

}
