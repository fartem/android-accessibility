package com.smlnskgmail.jaman.androidaccessibility.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.annotation.NonNull
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.charts.BarLineChartBase
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.formatter.IAxisValueFormatter
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import com.github.mikephil.charting.utils.ColorTemplate
import com.github.mikephil.charting.utils.EntryXComparator
import com.smlnskgmail.jaman.androidaccessibility.R
import com.smlnskgmail.jaman.androidaccessibility.utils.AccessibilityUtils
import java.util.*

class ChartsActivity : BaseActivity() {

    override fun getLayoutResId(): Int {
        return R.layout.activity_charts
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val lineChart: LineChart = findViewById(R.id.charts_line_chart)
        val barChart: BarChart = findViewById(R.id.charts_bar_chart)
        val xValueFormatter = valueFormatterForMonth
        configureChart(lineChart, xValueFormatter)
        configureChart(barChart, xValueFormatter)
        val data = getRandomFloats(6, 500f)
        setDataForChart(lineChart, data)
        setDataForChart(barChart, data)
    }

    companion object {

        fun newIntent(context: Context?): Intent {
            return Intent(context, ChartsActivity::class.java)
        }

        private val valueFormatterForMonth: IAxisValueFormatter
            get() = IAxisValueFormatter { value, axis ->
                val calendar = Calendar.getInstance()
                calendar[Calendar.MONTH] = Math.round(value) % 12
                calendar.getDisplayName(
                    Calendar.MONTH,
                    Calendar.SHORT,
                    Locale.US
                )
            }

        private fun getRandomFloats(count: Int, maxValue: Float): FloatArray {
            val floats = FloatArray(count)
            for (i in 0 until count) {
                val mult = maxValue + 1
                floats[i] =
                    Math.round(Math.random() * mult).toFloat()
            }
            return floats
        }

        private fun configureChart(
            @NonNull chart: BarLineChartBase<*>,
            @NonNull xAxisValueFormatter: IAxisValueFormatter
        ) {
            chart.setScaleEnabled(false)
            chart.setDrawGridBackground(false)
            val xAxis = chart.xAxis
            xAxis.position = XAxis.XAxisPosition.BOTTOM
            xAxis.valueFormatter = xAxisValueFormatter
            val leftAxis = chart.axisLeft
            leftAxis.isEnabled = false
            val rightAxis = chart.axisRight
            rightAxis.isEnabled = false
            val legend = chart.legend
            legend.isEnabled = false
        }

        private fun setDataForChart(
            @NonNull chart: BarLineChartBase<*>,
            @NonNull data: FloatArray
        ) { // Line Data
            if (chart is LineChart) {
                val lineEntries: MutableList<Entry> =
                    ArrayList()
                for (i in data.indices) {
                    lineEntries.add(Entry(i.toFloat(), data[i]))
                }
                Collections.sort(lineEntries, EntryXComparator())
                val lineDataSet = LineDataSet(lineEntries, "DataSet")
                lineDataSet.setColors(*ColorTemplate.MATERIAL_COLORS)
                val lineDataSets =
                    ArrayList<ILineDataSet>()
                lineDataSets.add(lineDataSet)
                val lineData = LineData(lineDataSets)
                lineData.setValueTextSize(AccessibilityUtils.getScaleIndependentPixels(13f))
                chart.data = lineData
            } else if (chart is BarChart) {
                val barEntries: MutableList<BarEntry> = ArrayList()
                for (i in data.indices) {
                    barEntries.add(BarEntry(i.toFloat(), data[i]))
                }
                val barDataSet = BarDataSet(barEntries, "DataSet")
                barDataSet.setColors(*ColorTemplate.MATERIAL_COLORS)
                val barDataSets =
                    ArrayList<IBarDataSet>()
                barDataSets.add(barDataSet)
                val barData = BarData(barDataSets)
                barData.setValueTextSize(AccessibilityUtils.getScaleIndependentPixels(13f))
                chart.data = barData
            }
        }

    }

}
