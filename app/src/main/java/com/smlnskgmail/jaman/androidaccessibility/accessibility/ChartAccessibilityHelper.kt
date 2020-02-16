package com.smlnskgmail.jaman.androidaccessibility.accessibility

import android.content.res.Resources
import android.graphics.Rect
import android.os.Bundle
import android.util.TypedValue
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat
import androidx.customview.widget.ExploreByTouchHelper
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.charts.BarLineChartBase
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.Entry

class ChartAccessibilityHelper(
    private val chart: BarLineChartBase<*>,
    private val entries: List<Entry>
) : ExploreByTouchHelper(chart) {

    private val tempRect = Rect()

    private var dataFormatter: DataFormatter? = null

    fun dataFormatter(
        dataFormatter: DataFormatter
    ) {
        this.dataFormatter = dataFormatter
    }

    override fun getVirtualViewAt(
        x: Float,
        y: Float
    ): Int {
        val entry = chart.getEntryByTouchPoint(x, y)
        val index = entries.indexOf(entry)
        return if (index == -1) {
            INVALID_ID
        } else {
            index
        }
    }

    override fun getVisibleVirtualViews(
        virtualViewIds: MutableList<Int>?
    ) {
        entries.indices.forEach {
            virtualViewIds!!.add(it)
        }
    }

    override fun onPerformActionForVirtualView(
        virtualViewId: Int,
        action: Int,
        arguments: Bundle?
    ): Boolean {
        return false
    }

    override fun onPopulateNodeForVirtualView(
        virtualViewId: Int,
        node: AccessibilityNodeInfoCompat
    ) {
        val entry = entries[virtualViewId]
        if (dataFormatter != null) {
            node.text = dataFormatter!!.description(
                chart,
                entries,
                entry
            )
        }

        val bounds = tempRect

        if (chart is BarChart) {
            val barBounds = chart.getBarBounds(entry as BarEntry)
            barBounds.round(bounds)
            node.getBoundsInScreen(bounds)
        } else {
            val position = chart.getPosition(
                entry,
                YAxis.AxisDependency.LEFT
            )
            val displayMetrics = Resources.getSystem().displayMetrics
            val pointArea = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                48f,
                displayMetrics
            ) / 2

            tempRect.top = (position.y - pointArea).toInt()
            tempRect.left = (position.x - pointArea).toInt()
            tempRect.right = (position.x + pointArea).toInt()
            tempRect.bottom = (position.y + pointArea).toInt()

            node.getBoundsInScreen(bounds)
        }
    }

    interface DataFormatter {

        fun description(
            chart: BarLineChartBase<*>,
            entries: List<Entry>,
            entry: Entry
        ): String

    }

}