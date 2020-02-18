package com.smlnskgmail.jaman.androidaccessibility;

import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import com.smlnskgmail.jaman.androidaccessibility.activities.MainActivity;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.isFocusable;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@LargeTest
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule =
            new ActivityTestRule<>(MainActivity.class);

    @Test
    public void checkIfButtonsAreClickableAndFocusable() {
        onView(withId(R.id.main_login_button))
                .check(matches(isFocusable()))
                .check(matches(isClickable()));
        onView(withId(R.id.main_settings_button))
                .check(matches(isFocusable()))
                .check(matches(isClickable()));
        onView(withId(R.id.main_tablayout_button))
                .check(matches(isFocusable()))
                .check(matches(isClickable()));
        onView(withId(R.id.main_single_button))
                .check(matches(isFocusable()))
                .check(matches(isClickable()));
        onView(withId(R.id.main_cards_button))
                .check(matches(isFocusable()))
                .check(matches(isClickable()));
        onView(withId(R.id.main_custom_button))
                .check(matches(isFocusable()))
                .check(matches(isClickable()));
        onView(withId(R.id.main_charts_button))
                .check(matches(isFocusable()))
                .check(matches(isClickable()));
    }

}
