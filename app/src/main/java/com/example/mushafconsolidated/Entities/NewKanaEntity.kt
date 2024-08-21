//primaryKeys ={"translation_id","verse_id"}
package com.example.mushafconsolidated.Entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "newkana")
class NewKanaEntity constructor(
    var surah: Int,
    var ayah: Int,
    var indexstart: Int,
    var indexend: Int,
    var khabarstart: Int,
    var khabarend: Int,
    var ismkanastart: Int,
    var ismkanaend: Int,
    var harfwordno: Int,
    var ismwordo: Int,
    var ismendword: Int,
    var khabarstartwordno: Int,
    var khabarendwordno: Int,
    var mahdoof: Int,
    var comment: String?,
    @field:PrimaryKey(
        autoGenerate = true
    ) var id: Int
)