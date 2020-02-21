package com.smlnskgmail.jaman.androidaccessibility

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.smlnskgmail.jaman.androidaccessibility.activities.ChartsActivity
import org.junit.Rule
import org.junit.Test

@LargeTest
class ChartsActivityTest {

    @JvmField
    @Rule
    var mActivityRule = ActivityTestRule(
        ChartsActivity::class.java
    )

    @Test
    fun checkChartsAreVisible() {
        onView(withId(R.id.charts_line_chart))
            .check(matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.charts_bar_chart))
            .check(matches(ViewMatchers.isDisplayed()))
    }

}
