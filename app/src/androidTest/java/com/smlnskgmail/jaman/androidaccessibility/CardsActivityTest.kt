package com.smlnskgmail.jaman.androidaccessibility

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.hasDescendant
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.smlnskgmail.jaman.androidaccessibility.MatcherUtils.atPosition
import com.smlnskgmail.jaman.androidaccessibility.activities.CardsActivity
import org.junit.Rule
import org.junit.Test

@LargeTest
class CardsActivityTest {

    @JvmField
    @Rule
    var mActivityRule = ActivityTestRule(
        CardsActivity::class.java
    )

    @Test
    fun checkIfCardsHaveImageButtons() {
        onView(withId(R.id.cards_recyclerview)).check(
            matches(atPosition(
                0,
                hasDescendant(withId(R.id.cards_card_more_options))
            ))
        )
        onView(withId(R.id.cards_recyclerview)).check(
            matches(atPosition(
                0,
                hasDescendant(withId(R.id.cards_card_like))
            ))
        )
        onView(withId(R.id.cards_recyclerview)).check(
            matches(atPosition(
                0,
                hasDescendant(withId(R.id.cards_card_comment))
            ))
        )
        onView(withId(R.id.cards_recyclerview)).check(
            matches(atPosition(
                0,
                hasDescendant(withId(R.id.cards_card_favorite))
            ))
        )
        onView(withId(R.id.cards_recyclerview)).check(
            matches(atPosition(
                0,
                hasDescendant(withId(R.id.cards_card_share))
            ))
        )
    }

}
