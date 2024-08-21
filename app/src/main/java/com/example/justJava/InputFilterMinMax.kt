package com.example.justJava

import android.text.InputFilter
import android.text.Spanned

class InputFilterMinMax(private val minimumValue: Int, private val maximumValue: Int) :
    InputFilter {
    override fun filter(
        source: CharSequence,
        start: Int,
        end: Int,
        dest: Spanned,
        dstart: Int,
        dend: Int
    ): String? {
        try {
            val input = (dest.subSequence(0, dstart).toString() + source + dest.subSequence(
                dend,
                dest.length
            )).toInt()
            if (isInRange(minimumValue, maximumValue, input)) return null
        } catch (nfe: NumberFormatException) {
        }
        return ""
    }

    private fun isInRange(a: Int, b: Int, c: Int): Boolean {
        return if (b > a) c in a..b else c in b..a
    }
}