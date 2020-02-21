package com.smlnskgmail.jaman.androidaccessibility

import android.view.View
import android.widget.EditText
import android.widget.SeekBar
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.core.internal.deps.guava.base.Preconditions
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA
import org.hamcrest.CoreMatchers
import org.hamcrest.Description
import org.hamcrest.Matcher

object MatcherUtils {

    fun getTextInputLayoutEditText(id: Int): Matcher<View> {
        return CoreMatchers.allOf(
            isDescendantOfA(ViewMatchers.withId(id)),
            isAssignableFrom(EditText::class.java)
        )
    }

    fun atPosition(
        position: Int,
        itemMatcher: Matcher<View>
    ): BoundedMatcher<View?, RecyclerView> {
        Preconditions.checkNotNull(itemMatcher)
        return object : BoundedMatcher<View?, RecyclerView>(RecyclerView::class.java) {
            override fun describeTo(description: Description) {
                description.appendText("has item at position $position: ")
                itemMatcher.describeTo(description)
            }

            override fun matchesSafely(view: RecyclerView): Boolean {
                val viewHolder =
                    view.findViewHolderForAdapterPosition(position)
                        ?: // has no item on such position
                        return false
                return itemMatcher.matches(viewHolder.itemView)
            }
        }
    }

    fun setProgress(progress: Int): ViewAction {
        return object : ViewAction {
            override fun perform(
                uiController: UiController,
                view: View
            ) {
                val seekBar = view as SeekBar
                seekBar.progress = progress
            }

            override fun getDescription(): String {
                return "Set a progress on a SeekBar"
            }

            override fun getConstraints(): Matcher<View> {
                return isAssignableFrom(SeekBar::class.java)
            }
        }
    }

}
