package com.example.mushafconsolidated.model


import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText


/**
 * Original:
 * An EditText, which notifies when something was cut/copied/pasted inside it.
 *
 * @author Lukas Knuth
 * @author Guillermo Muntaner on 14/01/16.
 *
 *
 * Source & discussion:
 * https://stackoverflow.com/questions/14980227/android-intercept-paste-copy-cut-on-edittext
 * @version 1.0
 *
 *
 * Update:
 * Added a OnCutCopyPasteListener so this class can be used as a plug&play component
 */

class CutCopyPasteEditText : AppCompatEditText {
    private var mOnCutCopyPasteListener: OnCutCopyPasteListener? = null

    /*
        Just the constructors to create a new EditText...
     */
    constructor(context: Context?) : super(context!!)
    constructor(context: Context?, attrs: AttributeSet?) : super(
        context!!, attrs
    )

    constructor(context: Context?, attrs: AttributeSet?, defStyle: Int) : super(
        context!!, attrs, defStyle
    )

    /**
     * Set a OnCutCopyPasteListener.
     *
     * @param listener
     */
    fun setOnCutCopyPasteListener(listener: OnCutCopyPasteListener?) {
        mOnCutCopyPasteListener = listener
    }

    /**
     *
     * This is where the "magic" happens.
     *
     * The menu used to cut/copy/paste is a normal ContextMenu, which allows us to
     * overwrite the consuming method and react on the different events.
     *
     * @see [Original Implementation](http://grepcode.com/file/repository.grepcode.com/java/ext/com.google.android/android/2.3_r1/android/widget/TextView.java.TextView.onTextContextMenuItem%28int%29)
     */
    override fun onTextContextMenuItem(id: Int): Boolean {
        // Do your thing:
        val consumed = super.onTextContextMenuItem(id)
        when (id) {
            android.R.id.cut -> onCut()
            android.R.id.copy -> onCopy()
            android.R.id.paste -> onPaste()
        }
        return consumed
    }

    /**
     * Text was cut from this EditText.
     */
    private fun onCut() {
        if (mOnCutCopyPasteListener != null) mOnCutCopyPasteListener!!.onCut()
    }

    /**
     * Text was copied from this EditText.
     */
    private fun onCopy() {
        if (mOnCutCopyPasteListener != null) mOnCutCopyPasteListener!!.onCopy()
    }

    /**
     * Text was pasted into the EditText.
     */
    private fun onPaste() {
        if (mOnCutCopyPasteListener != null) mOnCutCopyPasteListener!!.onPaste()
    }

    interface OnCutCopyPasteListener {
        fun onCut()
        fun onCopy()
        fun onPaste()
    }
}