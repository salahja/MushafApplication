package com.example.mushafconsolidated.Entities

import android.text.SpannableString
import android.text.SpannableStringBuilder
import androidx.room.Ignore
import androidx.room.PrimaryKey

//primaryKeys ={"translation_id","verse_id"}
class CorpusExpandWbwPOJO constructor(
    var root_a: String,
    var surah: Int,
    var ayah: Int,
    var wordno: Int,
    var wordcount: Int,
    var translation: String,
    var araone: String,
    var aratwo: String,
    var arathree: String,
    var arafour: String,
    var arafive: String,
    var tagone: String,
    var tagtwo: String,
    var tagthree: String,
    var tagfour: String,
    var tagfive: String,
    var detailsone: String,
    var detailstwo: String,
    var detailsthree: String,
    var detailsfour: String,
    var detailsfive: String,
    var en: String,
    var bn: String,
    var `in`: String,
    var ur: String?
) {
    var qurantext: String? = null
    var passage_no: Int = 0
    var ar_irab_two: String? = null
    var tafsir_kathir: String? = null
    @Ignore
    var spannableVerse: SpannableString? = null
    @Ignore
    var erabspnabble: SpannableStringBuilder? = null
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
    var en_transliteration: String? = null
    var en_jalalayn: String? = null
    var en_arberry: String? = null
    var ur_jalalayn: String? = null
    var ur_junagarhi: String? = null

}