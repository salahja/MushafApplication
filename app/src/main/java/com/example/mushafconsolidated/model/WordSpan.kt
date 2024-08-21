package com.example.mushafconsolidated.model

import android.text.SpannableString

class WordSpan {
    var id: Long = 0
    var surahId: Long = 0
    var verseId: Long = 0
    var wordsId: Long = 0
    var wordcount = 0
    var wordno = 0
    var wordsAr: SpannableString? = null
    var translate: String? = null
    var translateEn: String? = null
    var translateBn: String? = null
    var translateIndo: String? = null
    var quranErab: String? = null
}