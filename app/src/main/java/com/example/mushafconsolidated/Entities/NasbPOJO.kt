//primaryKeys ={"translation_id","verse_id"}
package com.example.mushafconsolidated.Entities

import android.text.SpannableString


import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "nasb")
class NasbPOJO constructor(
    var surah: Int,
    var ayah: Int,
    var indexstart: Int,
    var indexend: Int,
    var ismstart: Int,
    var ismend: Int,
    var khabarstart: Int,
    var khabarend: Int,
    var mahdoof: Int,
    var comment: String,
    var qurantext: String,
    var translation: String,
    @field:PrimaryKey(
        autoGenerate = true
    ) var id: Int
) {
    @Ignore
    var spannedverse: SpannableString? = null

}