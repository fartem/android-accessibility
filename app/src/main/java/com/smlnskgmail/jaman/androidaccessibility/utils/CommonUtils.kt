package com.smlnskgmail.jaman.androidaccessibility.utils

import java.util.*
import kotlin.math.roundToInt

@SuppressWarnings("MagicNumber")
object CommonUtils {

    fun getNearDate(): Date {
        val calendar = Calendar.getInstance()
        calendar.add(
            Calendar.HOUR_OF_DAY,
            randomBetween(-48, -1)
        )
        return calendar.time
    }

    private fun randomBetween(
        start: Int,
        end: Int
    ): Int {
        return start + (Math.random() * (end - start)).roundToInt()
    }

}
