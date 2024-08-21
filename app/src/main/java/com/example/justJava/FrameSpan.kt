package com.example.justJava

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Paint.FontMetricsInt
import android.graphics.RectF
import android.text.style.ReplacementSpan
import com.example.Constant

/**
 *
 */
class FrameSpan(private val color: Int, v: Float, shape: String) : ReplacementSpan() {
    private val mPaint: Paint
    private val shape: String
    private var mWidth = 0

    init {
        mPaint = Paint()
        mPaint.style = Paint.Style.STROKE
        //   mPaint.setColor(Color.BLUE);
        mPaint.color = color
        mPaint.strokeWidth = v
        mPaint.isAntiAlias = true
        mPaint.isDither = true
        this.shape = shape
    }

    override fun getSize(
        paint: Paint,
        text: CharSequence,
        start: Int,
        end: Int,
        fm: FontMetricsInt?
    ): Int {
        //return text with relative to the Paint
        mWidth = paint.measureText(text, start, end).toInt()
        return mWidth
    }

    override fun draw(
        canvas: Canvas,
        text: CharSequence,
        start: Int,
        end: Int,
        x: Float,
        top: Int,
        y: Int,
        bottom: Int,
        paint: Paint
    ) {
        //draw the frame with custom Paint
        //  paint.setStyle(Paint.Style.STROKE);
        canvas.density = 10
        //   paint.setLinearText(true);
        paint.color = Color.WHITE
        paint.isDither = true
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 0f
        if (shape == Constant.RECKT) {
            canvas.drawRect(x, top.toFloat(), x + mWidth, bottom.toFloat(), mPaint)
            canvas.drawText(text.toString(), start, end, x, y.toFloat(), paint)
        } else if (shape == "oval") {
            // Path path = new Path();
            //  path.moveTo(74, 268);
            //  path.cubicTo(start, end, x, top, 99, bottom);
            //    Paint mPaint = new Paint();
            //    mPaint.setColor(KASHMIRIGREEN);
            canvas.drawOval(x, top.toFloat(), x + mWidth, bottom.toFloat(), mPaint)
            //    mPaint.setColor(Color.BLUE);
            //   mPaint.setStrokeWidth(0);
            //     mPaint.setStyle(Paint.Style.FILL);
            //    mPaint.setTextSize(20);
            canvas.drawText(text, start, end, x, y.toFloat(), mPaint)
        } else {
            val rectf = RectF(x, top.toFloat(), x + mWidth, bottom.toFloat())
            canvas.drawArc(rectf, 0f, -180f, true, paint)
            canvas.drawText(text.toString(), start, end, x, y.toFloat(), paint)
        }
        // paint.setColor(Color.WHITE);
        //paint.setStyle(Paint.Style.FILL);
        //  canvas.drawPaint(paint);
        // paint.setColor(Color.BLACK);
        //  TextPaint textPaint = new TextPaint();
        // textPaint.setAntiAlias(true);
        //  textPaint.setTextSize(16 * getResources().getDisplayMetrics().density);
        // textPaint.setColor(0xFF000000);
        // int width = (int) textPaint.measureText(String.valueOf(text));
        // StaticLayout staticLayout = new StaticLayout(text, textPaint, (int) width, Layout.Alignment.ALIGN_NORMAL, 1.0f, 0, false);
        // canvas.save();
        //  staticLayout.draw(canvas);
        //  canvas.drawText(String.valueOf(text), start, end, x, y, paint);
    }
}