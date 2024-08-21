package com.example.utility

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import kotlin.math.max

class MyFlowLayout : ViewGroup {
    private val rtl = true

    constructor(context: Context?) : super(context) {}

    @JvmOverloads
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int = 0) : super(
        context,
        attrs,
        defStyleAttr
    ) {
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val realWidth = MeasureSpec.getSize(widthMeasureSpec)
        var currentHeight = 0
        var currentWidth = 0
        var currentChildHookPointx = 0
        var currentChildHookPointy = 0
        val childCount = this.childCount
        for (i in 0 until childCount) {
            val child = getChildAt(i)
            measureChild(child, widthMeasureSpec, heightMeasureSpec)
            val childWidth = child.measuredWidth
            val childHeight = child.measuredHeight

            //check if child can be placed in the current row, else go to next line
            if (currentChildHookPointx + childWidth > realWidth) {
                //new line
                currentWidth = max(currentWidth, currentChildHookPointx)

                //reset for new line
                currentChildHookPointx = 0
                currentChildHookPointy += childHeight
            }
            var nextChildHookPointx: Int
            var nextChildHookPointy: Int
            nextChildHookPointx = currentChildHookPointx + childWidth
            nextChildHookPointy = currentChildHookPointy
            currentHeight = max(currentHeight, currentChildHookPointy + childHeight)
            val lp = child.layoutParams as LayoutParams
            lp.x = currentChildHookPointx
            lp.y = currentChildHookPointy
            currentChildHookPointx = nextChildHookPointx
            currentChildHookPointy = nextChildHookPointy
        }
        currentWidth = max(currentChildHookPointx, currentWidth)
        setMeasuredDimension(
            resolveSize(currentWidth, widthMeasureSpec),
            resolveSize(currentHeight, heightMeasureSpec)
        )
    }

    override fun onLayout(b: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        //call layout on children
        val childCount = this.childCount
        for (i in 0 until childCount) {
            val child = getChildAt(i)
            val lp = child.layoutParams as LayoutParams
            child.layout(lp.x, lp.y, lp.x + child.measuredWidth, lp.y + child.measuredHeight)
        }
    }

    override fun generateLayoutParams(attrs: AttributeSet): LayoutParams {
        return LayoutParams(context, attrs)
    }

    override fun generateDefaultLayoutParams(): LayoutParams {
        return LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
    }

    override fun generateLayoutParams(p: ViewGroup.LayoutParams): LayoutParams {
        return LayoutParams(p)
    }

    override fun checkLayoutParams(p: ViewGroup.LayoutParams): Boolean {
        return p is LayoutParams
    }

    class LayoutParams : MarginLayoutParams {
        private var spacing = -1
        var x = 0
        var y = 0

        internal constructor(c: Context?, attrs: AttributeSet?) : super(
            c,
            attrs
        ) {          //  TypedArray t = c.obtainStyledAttributes(attrs, R.styleable.FlowLayout_Layout);
            //  spacing = t.getDimensionPixelSize(R.styleable.FlowLayout_Layout_layout_space, 0);
            //   t.recycle();
        }

        internal constructor(width: Int, height: Int) : super(width, height) {
            spacing = 0
        }

        constructor(source: MarginLayoutParams?) : super(source) {}
        internal constructor(source: ViewGroup.LayoutParams?) : super(source) {}
    }
}