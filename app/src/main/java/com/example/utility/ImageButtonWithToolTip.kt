package com.example.utility

import android.content.Context
import android.graphics.Rect
import android.text.TextUtils
import android.util.AttributeSet
import android.view.Gravity
import android.widget.Toast
import androidx.appcompat.widget.AppCompatTextView

class ImageButtonWithToolTip : AppCompatTextView {
    constructor(context: Context?) : super(context!!) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(
        context!!, attrs
    ) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context!!, attrs, defStyleAttr
    ) {
        init()
    }

    private fun init() {
        setOnLongClickListener { view ->
            /**
             * You should set the android:contentDescription attribute in this view's XML layout file.
             */
            /**
             * You should set the android:contentDescription attribute in this view's XML layout file.
             */
            val contentDescription = contentDescription.toString()
            if (TextUtils.isEmpty(contentDescription)) {
                /**
                 * There's no content description, so do nothing.
                 */
                /**
                 * There's no content description, so do nothing.
                 */
                false // Not consumed
            } else {
                val screenPos = IntArray(2) // origin is device display
                val displayFrame = Rect() // includes decorations (e.g. status bar)
                view.getLocationOnScreen(screenPos)
                view.getWindowVisibleDisplayFrame(displayFrame)
                val context = view.context
                val viewWidth = view.width
                val viewHeight = view.height
                val viewCenterX = screenPos[0] + viewWidth / 2
                val screenWidth = context.resources.displayMetrics.widthPixels
                val estimatedToastHeight = (ESTIMATED_TOAST_HEIGHT_DIPS
                        * context.resources.displayMetrics.density).toInt()
                val toolTipToast = Toast.makeText(context, contentDescription, Toast.LENGTH_SHORT)
                val showBelow = screenPos[1] < estimatedToastHeight
                if (showBelow) {
                    // Show below
                    // Offsets are after decorations (e.g. status bar) are factored in
                    toolTipToast.setGravity(
                        Gravity.TOP or Gravity.CENTER_HORIZONTAL,
                        viewCenterX - screenWidth / 2,
                        screenPos[1] - displayFrame.top + viewHeight
                    )
                } else {
                    // Show above
                    // Offsets are after decorations (e.g. status bar) are factored in
                    // NOTE: We can't use Gravity.BOTTOM because when the keyboard is up
                    // its height isn't factored in.
                    toolTipToast.setGravity(
                        Gravity.TOP or Gravity.CENTER_HORIZONTAL,
                        viewCenterX - screenWidth / 2,
                        screenPos[1] - displayFrame.top - estimatedToastHeight
                    )
                }
                toolTipToast.show()
                true // Consumed
            }
        }
    }

    companion object {
        private const val ESTIMATED_TOAST_HEIGHT_DIPS = 48
    }
}