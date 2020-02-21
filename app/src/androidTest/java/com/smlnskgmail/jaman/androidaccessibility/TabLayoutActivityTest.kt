package com.smlnskgmail.jaman.androidaccessibility

import android.os.SystemClock
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.smlnskgmail.jaman.androidaccessibility.activities.TabLayoutActivity
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.containsString
import org.junit.Rule
import org.junit.Test

@LargeTest
class TabLayoutActivityTest {

    @JvmField
    @Rule
    var mActivityRule = ActivityTestRule(
        TabLayoutActivity::class.java
    )

    @Test
    fun checkViewPagerSwipe() {
        onView(withId(R.id.tab_layout_view_pager))
            .check(matches(isDisplayed()))
        onView(withId(R.id.tab_layout_view_pager))
            .perform(ViewActions.swipeLeft())
    }

    @Test
    fun checkTabClickAndContentDisplay() {
        val matcher = allOf(
            withText("Learn"),
            isDescendantOfA(withId(R.id.tab_layout_tabs))
        )
        onView(matcher).perform(ViewActions.click())
        SystemClock.sleep(800) // Wait a little until the content is loaded
        onView(
            allOf(
                withId(R.id.tab_layout_textview),
                isCompletelyDisplayed()
            )
        ).check(
            matches(
                withText(
                    containsString("Learn more")
                )
            )
        )
    }
    
}
