package com.example.mushafconsolidated.Entities

import android.text.SpannableString
import androidx.room.Ignore

data class NounCorpusBreakup(
    var count: Int,
    var id: Int,
    var root_a: String?,
    var lemma_a: String?,
    var araword: String?,
    var surah: Int,
    var ayah: Int,
    var wordno: Int,
    var token: Int,
    var words: String?,
    var tag: String?,
    var propone: String?,
    var proptwo: String?,
    var form: String?,
    var lemma: String?,
    var root: String?,
    var gendernumber: String?,
    var type: String?,
    var cases: String?
) {

    @Ignore
    var spannedWord: SpannableString? = null

}
