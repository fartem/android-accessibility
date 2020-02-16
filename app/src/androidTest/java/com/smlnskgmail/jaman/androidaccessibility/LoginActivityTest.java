package com.smlnskgmail.jaman.androidaccessibility;

import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import com.smlnskgmail.jaman.androidaccessibility.activities.LoginActivity;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static com.smlnskgmail.jaman.androidaccessibility.MatcherUtils.getTextInputLayoutEditText;
import static org.hamcrest.CoreMatchers.not;

@LargeTest
public class LoginActivityTest {

    @Rule
    public ActivityTestRule<LoginActivity> mActivityRule =
            new ActivityTestRule<>(LoginActivity.class);

    @Test
    public void checkGlobalErrorMessageIsDisplayed() {
        onView(getTextInputLayoutEditText(R.id.login_email_address))
                .perform(typeText("john@example.com"));
        onView(getTextInputLayoutEditText(R.id.login_password))
                .perform(typeText("password"));
        onView(withId(R.id.login_login_button))
                .perform(click());
        onView(withId(R.id.login_error_message))
                .check(matches(not(withText(""))));
    }
}
