package com.smlnskgmail.jaman.androidaccessibility

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.smlnskgmail.jaman.androidaccessibility.MatcherUtils.getTextInputLayoutEditText
import com.smlnskgmail.jaman.androidaccessibility.activities.LoginActivity
import org.hamcrest.CoreMatchers.not
import org.junit.Rule
import org.junit.Test

@LargeTest
class LoginActivityTest {

    @JvmField
    @Rule
    var mActivityRule = ActivityTestRule(
        LoginActivity::class.java
    )

    @Test
    fun checkGlobalErrorMessageIsDisplayed() {
        onView(getTextInputLayoutEditText(R.id.login_email_address))
            .perform(typeText("john@example.com"))
        onView(getTextInputLayoutEditText(R.id.login_password))
            .perform(typeText("password"))
        onView(withId(R.id.login_login_button))
            .perform(ViewActions.click())
        onView(withId(R.id.login_error_message))
            .check(matches(not(withText(""))))
    }

}
