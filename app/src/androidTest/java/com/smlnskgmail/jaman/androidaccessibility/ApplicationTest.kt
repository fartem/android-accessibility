package com.smlnskgmail.jaman.androidaccessibility

import androidx.test.espresso.contrib.AccessibilityChecks
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import org.junit.Assert.assertEquals
import org.junit.BeforeClass
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ApplicationTest {

    @Test
    fun useAppContext() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals(
            "com.smlnskgmail.jaman.androidaccessibility",
            context.packageName
        )
    }

    companion object {

        @BeforeClass
        fun enableAccessibilityChecks() {
            AccessibilityChecks
                .enable()
                .setRunChecksFromRootView(true)
        }

    }

}
