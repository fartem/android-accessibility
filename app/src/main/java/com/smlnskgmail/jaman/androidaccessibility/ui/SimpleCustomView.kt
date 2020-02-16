package com.smlnskgmail.jaman.androidaccessibility.ui

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View
import android.view.accessibility.AccessibilityNodeInfo
import com.smlnskgmail.jaman.androidaccessibility.utils.AccessibilityUtils

class SimpleCustomView : View {

    private val textSize: Float = AccessibilityUtils.spToPx(48f)
    private val text = "Custom view!"

    private var textPaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var bounds: Rect = Rect()

    constructor(
        context: Context?
    ) : super(context)

    constructor(
        context: Context?,
        attrs: AttributeSet?
    ) : super(context, attrs)

    constructor(
        context: Context?,
        attrs: AttributeSet?,
        defStyleAttr: Int
    ) : super(
        context,
        attrs,
        defStyleAttr
    )

    init {
        textPaint.textSize = textSize
        textPaint.getTextBounds(
            text,
            0,
            text.length,
            bounds
        )
        bounds.offset(0, -bounds.top)

        textPaint.color = Color.BLACK
        textPaint.textSize = textSize
        importantForAccessibility = IMPORTANT_FOR_ACCESSIBILITY_YES
    }

    override fun onInitializeAccessibilityNodeInfo(
        info: AccessibilityNodeInfo?
    ) {
        super.onInitializeAccessibilityNodeInfo(info)
        info!!.text = text
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas!!.drawText(
            text,
            0f,
            bounds.bottom.toFloat(),
            textPaint
        )
    }

}
