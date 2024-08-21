package com.example.mushafconsolidated.Entities

import android.text.SpannableString

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "shart")
class ShartPOJO {
    @Ignore
    var spannedverse: SpannableString? = null
    var surah: Int = 0
    var ayah: Int = 0
    var indexstart: Int = 0
    var indexend: Int = 0
    var shartindexstart: Int = 0
    var shartindexend: Int = 0
    var jawabshartindexstart: Int = 0
    var jawabshartindexend: Int = 0
    var sharttype: String? = null
    var comment: String? = null
    var qurantext: String? = null
    var translation: String? = null

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    constructor(
        surah: Int,
        ayah: Int,
        indexstart: Int,
        indexend: Int,
        shartindexstart: Int,
        shartindexend: Int,
        jawabshartindexstart: Int,
        jawabshartindexend: Int,
        sharttype: String,
        comment: String?,
        qurantext: String?,
        translation: String?,
        id: Int
    ) {
        this.surah = surah
        this.ayah = ayah
        this.indexstart = indexstart
        this.indexend = indexend
        this.shartindexstart = shartindexstart
        this.shartindexend = shartindexend
        this.jawabshartindexstart = jawabshartindexstart
        this.jawabshartindexend = jawabshartindexend
        this.sharttype = sharttype
        this.comment = comment
        this.qurantext = qurantext
        this.translation = translation
        this.id = id
    }

    constructor()
}