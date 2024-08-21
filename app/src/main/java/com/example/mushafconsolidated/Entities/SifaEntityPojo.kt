package com.example.mushafconsolidated.Entities

import android.text.SpannableString
import androidx.room.Ignore


//primaryKeys ={"translation_id","verse_id"}
class SifaEntityPojo constructor(
    var surah: Int,
    var ayah: Int,
    var wordno: Int,
    var startindex: Int,
    var endindex: Int,
    var phrasetype: String,
    var qurantext: String,
    var translation: String
) {

    @Ignore
    var spannedVerse: SpannableString? = null

}