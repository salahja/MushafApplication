package com.example.utility

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import com.example.mushafconsolidated.R
import kotlin.math.max

class FlowLayout : ViewGroup {
    private var horizontalSpacing = 0
    private var verticalSpacing = 0
    private var orientation = 0
    private var rtl = true
    var arrayList = ArrayList<String>()

    constructor(context: Context) : super(context) {
        readStyleParameters(context, null)
    }

    constructor(context: Context?, arrayList: ArrayList<String>) : super(context) {
        this.arrayList = arrayList
    }

    constructor(context: Context, attributeSet: AttributeSet?) : super(context, attributeSet) {
        readStyleParameters(context, attributeSet)
    }

    constructor(context: Context, attributeSet: AttributeSet?, defStyle: Int) : super(
        context,
        attributeSet,
        defStyle
    ) {
        readStyleParameters(context, attributeSet)
    }

    private fun realignBottom(start: Int, end: Int, height: Int) {
        val sb = StringBuilder()
        sb.append(start).append(end)
        arrayList.add(sb.toString())
        for (i in start until end) {
            val child = getChildAt(i)
            if (child.visibility == GONE) {
                continue
            }
            val lp = child.layoutParams as LayoutParams
            val childHeight = child.measuredHeight
            lp.y += height - childHeight
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val sizeWidth = MeasureSpec.getSize(widthMeasureSpec) - this.paddingRight - this.paddingLeft
        val sizeHeight =
            MeasureSpec.getSize(heightMeasureSpec) - this.paddingRight - this.paddingLeft
        val modeWidth = MeasureSpec.getMode(widthMeasureSpec)
        val modeHeight = MeasureSpec.getMode(heightMeasureSpec)
        val size: Int
        val mode: Int
        if (orientation == HORIZONTAL) {
            size = sizeWidth
            mode = modeWidth
        } else {
            size = sizeHeight
            mode = modeHeight
        }
        var lineThicknessWithSpacing = 0
        var lineThickness = 0
        var lineLengthWithSpacing = 0
        var lineLength: Int
        var prevLinePosition = 0
        var controlMaxLength = 0
        var controlMaxThickness = 0
        val count = childCount
        var start = 0
        for (i in 0 until count) {
            val child = getChildAt(i)
            if (child.visibility == GONE) {
                continue
            }
            child.measure(
                MeasureSpec.makeMeasureSpec(
                    sizeWidth,
                    if (modeWidth == MeasureSpec.EXACTLY) MeasureSpec.AT_MOST else modeWidth
                ),
                MeasureSpec.makeMeasureSpec(
                    sizeHeight,
                    if (modeHeight == MeasureSpec.EXACTLY) MeasureSpec.AT_MOST else modeHeight
                )
            )
            val lp = child.layoutParams as LayoutParams
            val hSpacing = getHorizontalSpacing(lp)
            val vSpacing = getVerticalSpacing(lp)
            val childWidth = child.measuredWidth
            val childHeight = child.measuredHeight
            var childLength: Int
            var childThickness: Int
            var spacingLength: Int
            var spacingThickness: Int
            if (orientation == HORIZONTAL) {
                childLength = childWidth
                childThickness = childHeight
                spacingLength = hSpacing
                spacingThickness = vSpacing
            } else {
                childLength = childHeight
                childThickness = childWidth
                spacingLength = vSpacing
                spacingThickness = hSpacing
            }
            lineLength = lineLengthWithSpacing + childLength
            lineLengthWithSpacing = lineLength + spacingLength
            val newLine = (lp.newLine || mode != MeasureSpec.UNSPECIFIED) && lineLength > size
            if (newLine) {
                if (orientation == HORIZONTAL) {
                    realignBottom(start, i, lineThickness)
                    start = i
                }
                prevLinePosition += lineThicknessWithSpacing
                lineThickness = childThickness
                lineLength = childLength
                lineThicknessWithSpacing = childThickness + spacingThickness
                lineLengthWithSpacing = lineLength + spacingLength
            }
            lineThicknessWithSpacing =
                max(lineThicknessWithSpacing, childThickness + spacingThickness)
            lineThickness = max(lineThickness, childThickness)
            var posX: Int
            var posY: Int
            if (orientation == HORIZONTAL) {
                posX = if (rtl) {
                    size - paddingRight - lineLength
                } else {
                    paddingLeft + lineLength - childLength
                }
                posY = paddingTop + prevLinePosition
            } else {
                posX = paddingLeft + prevLinePosition
                posY = paddingTop + lineLength - childHeight
            }
            lp.setPosition(posX, posY)
            controlMaxLength = max(controlMaxLength, lineLength)
            controlMaxThickness = prevLinePosition + lineThickness
        }
        if (orientation == HORIZONTAL) {
            realignBottom(start, count, lineThickness)
            setMeasuredDimension(
                resolveSize(controlMaxLength, widthMeasureSpec),
                resolveSize(controlMaxThickness, heightMeasureSpec)
            )
        } else {
            setMeasuredDimension(
                resolveSize(controlMaxThickness, widthMeasureSpec),
                resolveSize(controlMaxLength, heightMeasureSpec)
            )
        }
    }

    private fun getVerticalSpacing(lp: LayoutParams): Int {
        val vSpacing: Int
        vSpacing = if (lp.verticalSpacingSpecified()) {
            lp.verticalSpacing
        } else {
            verticalSpacing
        }
        return vSpacing
    }

    private fun getHorizontalSpacing(lp: LayoutParams): Int {
        val hSpacing: Int
        hSpacing = if (lp.horizontalSpacingSpecified()) {
            lp.horizontalSpacing
        } else {
            horizontalSpacing
        }
        return hSpacing
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        val count = childCount
        for (i in 0 until count) {
            val child = getChildAt(i)
            val lp = child.layoutParams as LayoutParams
            child.layout(lp.x, lp.y, lp.x + child.measuredWidth, lp.y + child.measuredHeight)
        }
    }

    override fun checkLayoutParams(p: ViewGroup.LayoutParams): Boolean {
        return p is LayoutParams
    }

    override fun generateDefaultLayoutParams(): LayoutParams {
        return LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
    }

    override fun generateLayoutParams(attributeSet: AttributeSet): LayoutParams {
        return LayoutParams(context, attributeSet)
    }

    override fun generateLayoutParams(p: ViewGroup.LayoutParams): LayoutParams {
        return LayoutParams(p)
    }

    private fun readStyleParameters(context: Context, attributeSet: AttributeSet?) {
        val a = context.obtainStyledAttributes(attributeSet, R.styleable.FlowLayout)
        try {
            horizontalSpacing = a.getDimensionPixelSize(R.styleable.FlowLayout_horizontalSpacing, 0)
            verticalSpacing = a.getDimensionPixelSize(R.styleable.FlowLayout_verticalSpacing, 0)
            orientation = a.getInteger(R.styleable.FlowLayout_orientation, HORIZONTAL)
            rtl = a.getBoolean(R.styleable.FlowLayout_rtl, true)
        } finally {
            a.recycle()
        }
    }

    class LayoutParams : ViewGroup.LayoutParams {
        var x = 0
        var y = 0
        var horizontalSpacing = NO_SPACING
        var verticalSpacing = NO_SPACING
        var newLine = false

        constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet) {
            readStyleParameters(context, attributeSet)
        }

        constructor(width: Int, height: Int) : super(width, height) {}
        constructor(layoutParams: ViewGroup.LayoutParams?) : super(layoutParams) {}

        fun horizontalSpacingSpecified(): Boolean {
            return horizontalSpacing != NO_SPACING
        }

        fun verticalSpacingSpecified(): Boolean {
            return verticalSpacing != NO_SPACING
        }

        fun setPosition(x: Int, y: Int) {
            this.x = x
            this.y = y
        }

        private fun readStyleParameters(context: Context, attributeSet: AttributeSet) {
            val a =
                context.obtainStyledAttributes(attributeSet, R.styleable.FlowLayout_LayoutParams)
            try {
                horizontalSpacing = a.getDimensionPixelSize(
                    R.styleable.FlowLayout_LayoutParams_layout_horizontalSpacing, NO_SPACING
                )
                verticalSpacing = a.getDimensionPixelSize(
                    R.styleable.FlowLayout_LayoutParams_layout_verticalSpacing, NO_SPACING
                )
                newLine = a.getBoolean(R.styleable.FlowLayout_LayoutParams_layout_newLine, false)
            } finally {
                a.recycle()
            }
        }

        companion object {
            private const val NO_SPACING = -1
        }
    }

    companion object {
        const val HORIZONTAL = 0
        const val VERTICAL = 1
    }
}