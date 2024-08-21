//primaryKeys ={"translation_id","verse_id"}
package com.example.mushafconsolidated.Entities

import android.text.SpannableString


import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "newmudhaf")
class MudhafPOJO constructor(
    var surah: Int,
    var ayah: Int,
    var wordno: Int,
    var startindex: Int,
    var endindex: Int,
    var status: Int,
    var comment: String,
    @field:PrimaryKey(
        autoGenerate = true
    ) var id: Int,
    var qurantext: String,
    var translation: String
) {
    @Ignore
    var spannedverse: SpannableString? = null

}