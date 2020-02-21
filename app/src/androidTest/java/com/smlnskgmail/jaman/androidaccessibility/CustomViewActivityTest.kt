package com.smlnskgmail.jaman.androidaccessibility

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.smlnskgmail.jaman.androidaccessibility.activities.CustomViewActivity
import org.junit.Rule
import org.junit.Test

@LargeTest
class CustomViewActivityTest {

    @JvmField
    @Rule
    var mActivityRule = ActivityTestRule(
        CustomViewActivity::class.java
    )

    @Test
    fun checkCustomViewIsVisible() {
        onView(withId(R.id.custom_view_simple_custom_view))
            .check(matches(isDisplayed()))
    }

}
