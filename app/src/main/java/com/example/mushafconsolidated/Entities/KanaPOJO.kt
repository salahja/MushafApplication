package com.example.mushafconsolidated.Entities

import android.text.SpannableString
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "kana")
class KanaPOJO constructor(
    var surah: Int,
    var ayah: Int,
    var indexstart: Int,
    var indexend: Int,
    var khabarstart: Int,
    var khabarend: Int,
    var ismkanastart: Int,
    var ismkanaend: Int,
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