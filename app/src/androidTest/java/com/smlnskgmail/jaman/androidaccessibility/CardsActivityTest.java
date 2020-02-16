package com.smlnskgmail.jaman.androidaccessibility;

import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import com.smlnskgmail.jaman.androidaccessibility.activities.CardsActivity;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static com.smlnskgmail.jaman.androidaccessibility.MatcherUtils.atPosition;

@LargeTest
public class CardsActivityTest {

    @Rule
    public ActivityTestRule<CardsActivity> mActivityRule =
            new ActivityTestRule<>(CardsActivity.class);

    @Test
    public void checkIfCardsHaveImageButtons() {
        onView(withId(R.id.cards_recyclerview))
                .check(matches(atPosition(0, hasDescendant(withId(R.id.cards_card_more_options)))));
        onView(withId(R.id.cards_recyclerview))
                .check(matches(atPosition(0, hasDescendant(withId(R.id.cards_card_like)))));
        onView(withId(R.id.cards_recyclerview))
                .check(matches(atPosition(0, hasDescendant(withId(R.id.cards_card_comment)))));
        onView(withId(R.id.cards_recyclerview))
                .check(matches(atPosition(0, hasDescendant(withId(R.id.cards_card_favorite)))));
        onView(withId(R.id.cards_recyclerview))
                .check(matches(atPosition(0, hasDescendant(withId(R.id.cards_card_share)))));
    }

}
