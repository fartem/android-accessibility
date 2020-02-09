package com.smlnskgmail.jaman.androidaccessibility.utils

import android.content.res.Resources
import android.util.TypedValue

object AccessibilityUtils {

    fun getScaleIndependentPixels(dp: Float): Float {
        val density = Resources.getSystem().displayMetrics.density
        val scaledDensity = Resources.getSystem().displayMetrics.scaledDensity
        return dp * (1 - (density - scaledDensity))
    }

    fun spToPx(sp: Float): Float {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_SP,
            sp,
            Resources.getSystem().displayMetrics
        )
    }

}