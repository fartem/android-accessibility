package com.smlnskgmail.jaman.androidaccessibility.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.annotation.NonNull
import androidx.core.view.ViewCompat
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.charts.BarLineChartBase
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.formatter.IAxisValueFormatter
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet
import com.github.mikephil.charting.interfaces.datasets.IBarLineScatterCandleBubbleDataSet
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import com.github.mikephil.charting.utils.ColorTemplate
import com.github.mikephil.charting.utils.EntryXComparator
import com.smlnskgmail.jaman.androidaccessibility.R
import com.smlnskgmail.jaman.androidaccessibility.accessibility.ChartAccessibilityHelper
import com.smlnskgmail.jaman.androidaccessibility.utils.AccessibilityUtils
import kotlinx.android.synthetic.main.activity_charts.*
import java.util.*
import kotlin.math.roundToInt

@SuppressWarnings("MagicNumber")
class ChartsActivity : BaseActivity(), ChartAccessibilityHelper.DataFormatter {

    private val lineEntries = mutableListOf<Entry>()
    private val barEntries = mutableListOf<BarEntry>()

    private lateinit var lineChartHelper: ChartAccessibilityHelper
    private lateinit var barChartHelper: ChartAccessibilityHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val xValueFormatter = valueFormatterForMonth
        configureChart(
            charts_line_chart,
            xValueFormatter
        )
        configureChart(
            charts_bar_chart,
            xValueFormatter
        )

        val data = getRandomFloats(6, 500f)
        setDataForChart(
            charts_line_chart,
            data
        )
        setDataForChart(
            charts_bar_chart,
            data
        )

        lineChartHelper = ChartAccessibilityHelper(
            charts_line_chart,
            lineEntries
        )
        lineChartHelper.dataFormatter(this)
        ViewCompat.setAccessibilityDelegate(
            charts_line_chart,
            lineChartHelper
        )

        barChartHelper = ChartAccessibilityHelper(
            charts_bar_chart,
            barEntries
        )
        barChartHelper.dataFormatter(this)
        ViewCompat.setAccessibilityDelegate(
            charts_bar_chart,
            barChartHelper
        )

        charts_line_chart.setOnHoverListener { _, event ->
            lineChartHelper.dispatchHoverEvent(event!!)
        }
        charts_bar_chart.setOnHoverListener { _, event ->
            barChartHelper.dispatchHoverEvent(event!!)
        }
    }

    @SuppressWarnings("SpreadOperator")
    private fun setDataForChart(
        @NonNull chart: BarLineChartBase<*>,
        @NonNull data: FloatArray
    ) {
        if (chart is LineChart) {
            for (i in data.indices) {
                lineEntries.add(
                    Entry(
                        i.toFloat(),
                        data[i]
                    )
                )
            }
            Collections.sort(
                lineEntries,
                EntryXComparator()
            )
            val lineDataSet = LineDataSet(lineEntries, "DataSet")
            lineDataSet.setColors(*ColorTemplate.MATERIAL_COLORS)

            val lineDataSets = mutableListOf<ILineDataSet>()
            lineDataSets.add(lineDataSet)

            val lineData = LineData(lineDataSets)
            lineData.setValueTextSize(
                AccessibilityUtils.getScaleIndependentPixels(13f)
            )
            chart.data = lineData
        } else if (chart is BarChart) {
            for (i in data.indices) {
                barEntries.add(
                    BarEntry(
                        i.toFloat(),
                        data[i]
                    )
                )
            }

            val barDataSet = BarDataSet(barEntries, "DataSet")
            barDataSet.setColors(*ColorTemplate.MATERIAL_COLORS)

            val barDataSets = mutableListOf<IBarDataSet>()
            barDataSets.add(barDataSet)

            val barData = BarData(barDataSets)
            barData.setValueTextSize(
                AccessibilityUtils.getScaleIndependentPixels(13f)
            )
            chart.data = barData
        }
    }

    override fun description(
        chart: BarLineChartBase<
                out BarLineScatterCandleBubbleData<
                        out IBarLineScatterCandleBubbleDataSet<out Entry>
                        >
                >,
        entries: List<Entry>,
        entry: Entry
    ): String {
        val xValue = chart.xAxis.getFormattedLabel(
            entries.indexOf(entry)
        )
        val yValue = entry.y.roundToInt()
        return resources.getQuantityString(
            R.plurals.charts_plurals_data_description,
            yValue,
            xValue,
            yValue
        )
    }

    override fun getLayoutResId(): Int {
        return R.layout.activity_charts
    }

    companion object {

        fun newIntent(context: Context?): Intent {
            return Intent(context, ChartsActivity::class.java)
        }

        private val valueFormatterForMonth: IAxisValueFormatter
            get() = IAxisValueFormatter { value, _ ->
                val calendar = Calendar.getInstance()
                calendar[Calendar.MONTH] = value.roundToInt() % 12
                calendar.getDisplayName(
                    Calendar.MONTH,
                    Calendar.SHORT,
                    Locale.US
                )
            }

        private fun getRandomFloats(
            count: Int,
            maxValue: Float
        ): FloatArray {
            val floats = FloatArray(count)
            for (i in 0 until count) {
                val mul = maxValue + 1
                floats[i] =
                    (Math.random() * mul).roundToInt().toFloat()
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

    }

}
