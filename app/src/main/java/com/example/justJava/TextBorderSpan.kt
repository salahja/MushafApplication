package com.example.justJava

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Paint.FontMetricsInt
import android.graphics.Path
import android.text.style.LineBackgroundSpan

class TextBorderSpan : LineBackgroundSpan {
    private var mPath: Path? = null
    private var mWidth = 0
    private fun getSize(
        paint: Paint,
        text: CharSequence,
        start: Int,
        end: Int,
        fm: FontMetricsInt
    ): Int {
        //return text with relative to the Paint
        mWidth = paint.measureText(text, start, end).toInt()
        return mWidth
    }

    override fun drawBackground(
        canvas: Canvas,
        paint: Paint,
        left: Int,
        right: Int,
        top: Int,
        baseline: Int,
        bottom: Int,
        charSequence: CharSequence,
        start: Int,
        end: Int,
        lnum: Int
    ) {
        if (mPath == null) {
            mPath = Path()
            mPath!!.setLastPoint(left.toFloat(), top.toFloat())
            mPath!!.lineTo(right.toFloat(), top.toFloat())
            mPath!!.lineTo(right.toFloat(), bottom.toFloat())
            mPath!!.lineTo(left.toFloat(), bottom.toFloat())
            mPath!!.lineTo(left.toFloat(), top.toFloat())
        }
        val oldStyle = paint.style
        val oldStroke = paint.strokeWidth
        paint.strokeWidth = 3f
        paint.color = Color.RED
        paint.style = Paint.Style.STROKE
        canvas.drawPath(mPath!!, paint)
        paint.strokeWidth = oldStroke
        paint.style = oldStyle
    }
}