package com.smlnskgmail.jaman.androidaccessibility

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItem
import androidx.test.espresso.contrib.RecyclerViewActions.scrollTo
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.smlnskgmail.jaman.androidaccessibility.activities.SingleActivity
import org.junit.Rule
import org.junit.Test

@LargeTest
class SingleActivityTest {

    @JvmField
    @Rule
    var mActivityRule = ActivityTestRule(
        SingleActivity::class.java
    )

    @Test
    fun checkDetailIsDisplayedOnItemClicked() {
        onView(withId(R.id.single_list_recyclerview))
            .perform(
                scrollTo<RecyclerView.ViewHolder>(
                    hasDescendant(
                        withText(
                            "Option 15"
                        )
                    )
                )
            )
            .perform(
                actionOnItem<RecyclerView.ViewHolder>(
                    hasDescendant(
                        withText(
                            "Option 15"
                        )
                    ),
                    click()
                )
            )
        onView(withText("Detail fragment"))
            .check(matches(isCompletelyDisplayed()))
    }
    
    
}
