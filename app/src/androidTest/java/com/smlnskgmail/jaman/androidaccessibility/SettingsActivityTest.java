package com.smlnskgmail.jaman.androidaccessibility;

import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import com.smlnskgmail.jaman.androidaccessibility.activities.SettingsActivity;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isChecked;
import static androidx.test.espresso.matcher.ViewMatchers.isNotChecked;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withSpinnerText;
import static com.smlnskgmail.jaman.androidaccessibility.MatcherUtils.setProgress;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;

@LargeTest
public class SettingsActivityTest {
    @Rule
    public ActivityTestRule<SettingsActivity> mActivityRule =
            new ActivityTestRule<>(SettingsActivity.class);

    @Test
    public void checkInputControlsInputs() {
        // click on checkboxes, switch, and radio
        onView(withId(R.id.settings_checkbox))
                .perform(click())
                .check(matches(isChecked()));
        onView(withId(R.id.settings_switch))
                .perform(click())
                .check(matches(isChecked()));
        onView(withId(R.id.settings_radio_opt_1))
                .perform(click())
                .check(matches(isChecked()));
        onView(withId(R.id.settings_radio_opt_2))
                .check(matches(isNotChecked()));
        onView(withId(R.id.settings_radio_opt_2))
                .perform(click())
                .check(matches(isChecked()));
        onView(withId(R.id.settings_radio_opt_1))
                .check(matches(isNotChecked()));

        // spinner
        onView(withId(R.id.settings_spinner)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("Spinner Option 1"))).perform(click());
        onView(withId(R.id.settings_spinner)).check(matches(withSpinnerText(containsString("Spinner Option 1"))));

        // just perform an action on the seek bar
        onView(withId(R.id.settings_seekbar)).perform(setProgress(70));
    }

}