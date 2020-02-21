package com.smlnskgmail.jaman.androidaccessibility

import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.smlnskgmail.jaman.androidaccessibility.activities.SettingsActivity
import org.hamcrest.Matchers
import org.junit.Rule
import org.junit.Test

@LargeTest
class SettingsActivityTest {

    @JvmField
    @Rule
    var mActivityRule = ActivityTestRule(
        SettingsActivity::class.java
    )

    @Test
    fun checkInputControlsInputs() {
        onView(withId(R.id.settings_checkbox))
            .perform(ViewActions.click())
            .check(matches(isChecked()))
        onView(withId(R.id.settings_switch))
            .perform(ViewActions.click())
            .check(matches(isChecked()))
        onView(withId(R.id.settings_radio_opt_1))
            .perform(ViewActions.click())
            .check(matches(isChecked()))
        onView(withId(R.id.settings_radio_opt_2))
            .check(matches(isNotChecked()))
        onView(withId(R.id.settings_radio_opt_2))
            .perform(ViewActions.click())
            .check(matches(isChecked()))
        onView(withId(R.id.settings_radio_opt_1))
            .check(matches(isNotChecked()))
        // spinner
        onView(withId(R.id.settings_spinner))
            .perform(ViewActions.click())
        onData(
            Matchers.allOf(
                Matchers.`is`(
                    Matchers.instanceOf<Any>(
                        String::class.java
                    )
                ),
                Matchers.`is`(
                    "Spinner Option 1"
                )
            )
        ).perform(ViewActions.click())
        onView(withId(R.id.settings_spinner))
            .check(matches(withSpinnerText(
                Matchers.containsString(
                    "Spinner Option 1"
                )))
            )
        // just perform an action on the seek bar
        onView(withId(R.id.settings_seekbar))
            .perform(MatcherUtils.setProgress(70))
    }
    
}
