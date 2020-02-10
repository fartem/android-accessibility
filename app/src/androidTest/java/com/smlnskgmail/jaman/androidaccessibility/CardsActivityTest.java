package com.smlnskgmail.jaman.androidaccessibility;

import androidx.test.filters.LargeTest;
import androidx.test.runner.AndroidJUnit4;

import com.smlnskgmail.jaman.androidaccessibility.activities.CardsActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
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
