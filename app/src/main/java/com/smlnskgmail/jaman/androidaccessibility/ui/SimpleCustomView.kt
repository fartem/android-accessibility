package com.smlnskgmail.jaman.androidaccessibility.ui

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View
import com.smlnskgmail.jaman.androidaccessibility.utils.AccessibilityUtils

class SimpleCustomView : View {

    private val TEXT_SIZE: Float = AccessibilityUtils.spToPx(48f)
    private val TEXT = "Custom view!"

    private var textPaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var bounds: Rect = Rect()

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    init {

        textPaint.textSize = TEXT_SIZE
        textPaint.getTextBounds(TEXT, 0, TEXT.length, bounds)
        bounds.offset(0, -bounds.top)

        textPaint.color = Color.BLACK
        textPaint.textSize = TEXT_SIZE
        importantForAccessibility = IMPORTANT_FOR_ACCESSIBILITY_YES
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas!!.drawText(TEXT, 0f, bounds.bottom.toFloat(), textPaint)
    }

}
