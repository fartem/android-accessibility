package com.smlnskgmail.jaman.androidaccessibility

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.smlnskgmail.jaman.androidaccessibility.activities.MainActivity
import org.junit.Rule
import org.junit.Test

@LargeTest
class MainActivityTest {

    @JvmField
    @Rule
    var mActivityRule = ActivityTestRule(
        MainActivity::class.java
    )

    @Test
    fun checkIfButtonsAreClickableAndFocusable() {
        onView(withId(R.id.main_login_button))
            .check(matches(isFocusable()))
            .check(matches(isClickable()))
        onView(withId(R.id.main_settings_button))
            .check(matches(isFocusable()))
            .check(matches(isClickable()))
        onView(withId(R.id.main_tablayout_button))
            .check(matches(isFocusable()))
            .check(matches(isClickable()))
        onView(withId(R.id.main_single_button))
            .check(matches(isFocusable()))
            .check(matches(isClickable()))
        onView(withId(R.id.main_cards_button))
            .check(matches(isFocusable()))
            .check(matches(isClickable()))
        onView(withId(R.id.main_custom_button))
            .check(matches(isFocusable()))
            .check(matches(isClickable()))
        onView(withId(R.id.main_charts_button))
            .check(matches(isFocusable()))
            .check(matches(isClickable()))
    }
    
}
