package com.example.mushafconsolidated.Entities

import android.text.SpannableString
import androidx.room.Ignore


//primaryKeys ={"translation_id","verse_id"}
class TameezPojoList constructor(
    var surah: Int,
    var ayah: Int,
    var wordno: Int,
    var word: String,
    var qurantext: String,
    var translation: String
) {
    @Ignore
    var spannedverse: SpannableString? = null

}