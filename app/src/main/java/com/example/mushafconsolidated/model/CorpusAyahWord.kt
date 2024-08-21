package com.example.mushafconsolidated.model

import android.text.SpannableString


class CorpusAyahWord {
    lateinit var word: ArrayList<CorpusWbwWord>
    private var isHasProstration = false
    private var quranArabic: String? = null
    private lateinit var quranTranslate: String
    var spannableverse: SpannableString? = null
    var ar_irab_two: String? = null
    var tafsir_kathir: String? = null
    var topictitle: String? = null
    var has_prostration: String? = null
    var en_transliteration: String? = null
    var en_jalalayn: String? = null
    var en_arberry: String? = null
    var ur_jalalayn: String? = null
    var ur_junagarhi: String? = null
    var passage_no = 0

    constructor()
    constructor(
        word: ArrayList<CorpusWbwWord>,
        hasProstration: Boolean,
        quranArabic: String?,
        quranTranslate: String,
        spannableverse: SpannableString?
    ) {
        this.word = word
        isHasProstration = hasProstration
        this.quranArabic = quranArabic
        this.quranTranslate = quranTranslate
        this.spannableverse = spannableverse
    }
}