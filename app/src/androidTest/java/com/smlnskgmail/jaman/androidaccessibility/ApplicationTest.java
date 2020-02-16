package com.smlnskgmail.jaman.androidaccessibility;

import android.content.Context;

import androidx.test.espresso.contrib.AccessibilityChecks;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.runner.AndroidJUnit4;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class ApplicationTest {

    @BeforeClass
    public static void enableAccessibilityChecks() {
        AccessibilityChecks
                .enable()
                .setRunChecksFromRootView(true);
    }

    @Test
    public void useAppContext() {
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals(
                "com.smlnskgmail.jaman.androidaccessibility",
                context.getPackageName()
        );
    }

}
