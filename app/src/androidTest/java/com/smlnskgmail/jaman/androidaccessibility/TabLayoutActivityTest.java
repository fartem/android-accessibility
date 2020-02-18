package com.smlnskgmail.jaman.androidaccessibility;

import android.annotation.SuppressLint;
import android.os.SystemClock;
import android.view.View;

import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import com.smlnskgmail.jaman.androidaccessibility.activities.TabLayoutActivity;

import org.hamcrest.Matcher;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.swipeLeft;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.Matchers.containsString;

@LargeTest
public class TabLayoutActivityTest {

    @Rule
    public ActivityTestRule<TabLayoutActivity> mActivityRule =
            new ActivityTestRule<>(TabLayoutActivity.class);

    @Test
    public void checkViewPagerSwipe() {
        onView(withId(R.id.tab_layout_view_pager))
                .check(matches(isDisplayed()));
        onView(withId(R.id.tab_layout_view_pager))
                .perform(swipeLeft());
    }

    @Test
    public void checkTabClickAndContentDisplay() {
        Matcher<View> matcher = allOf(withText("Learn"),
                isDescendantOfA(withId(R.id.tab_layout_tabs)));
        onView(matcher).perform(click());
        SystemClock.sleep(800); // Wait a little until the content is loaded
        onView(allOf(withId(R.id.tab_layout_textview), isCompletelyDisplayed()))
                .check(matches(withText(containsString("Learn more"))));
    }

}
