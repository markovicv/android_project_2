package com.example.rma_projekat.presentation.view.custom

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat
import com.example.rma_projekat.R
import com.example.rma_projekat.extensions.toPx

class GraphView : View {
    constructor(context: Context) : super (context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    var testValues: List<Int> = listOf(4, 11, 3, 5, 8)

    private var rect: Rect = Rect()
    private var paint: Paint = Paint()
    private val whiteStrokePaint: Paint = Paint().also {
        it.isAntiAlias = true
        it.color = ContextCompat.getColor(context, R.color.white)
        it.style = Paint.Style.STROKE
        it.strokeWidth = 4.toPx().toFloat()
    }
    private val blueFillPaint: Paint = Paint().also {
        it.isAntiAlias = true
        it.color = ContextCompat.getColor(context, R.color.blue)
        it.style = Paint.Style.FILL
        it.textSize = 36f
    }

    var heightDivider = 1

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
    }

    private fun getMaxValue(): Int {
        val x = testValues.max()
        return when (x) {
            null -> 0
            else -> x
        }
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        val step = when (val maxValue = getMaxValue()) {
            0 -> 0
            else -> if (height < 30) { 0 } else { (height - 30) / maxValue }
        }

        var left = 0
        var right = width / 5

        for (x in testValues) {
            drawRect(
                canvas,
                xTop = left,
                yTop = height - step * x,
                xBot = right,
                yBot = height - 30
            )
            canvas?.drawText(
                x.toString(),
                left.toFloat() + 20,
                height.toFloat(),
                blueFillPaint
            )
            left += width / 5
            right += width / 5
        }
    }

    private fun drawRect(canvas: Canvas?, xTop: Int, yTop: Int, xBot: Int, yBot: Int) {
        rect.set(xTop, yTop, xBot, yBot)
        canvas?.drawRect(rect, blueFillPaint)
        canvas?.drawRect(rect, whiteStrokePaint)
    }

}