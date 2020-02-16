package com.smlnskgmail.jaman.androidaccessibility;

import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import com.smlnskgmail.jaman.androidaccessibility.activities.SingleActivity;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@LargeTest
public class SingleActivityTest {
    @Rule
    public ActivityTestRule<SingleActivity> mActivityRule =
            new ActivityTestRule<>(SingleActivity.class);

    @Test
    public void checkDetailIsDisplayedOnItemClicked() {
        onView(ViewMatchers.withId(R.id.single_list_recyclerview))
                .perform(RecyclerViewActions.scrollTo(hasDescendant(withText("Option 15"))))
                .perform(RecyclerViewActions.actionOnItem(hasDescendant(withText("Option 15")), click()));
        onView(withText("Detail fragment")).check(matches(isCompletelyDisplayed()));
    }

}
