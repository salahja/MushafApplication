//primaryKeys ={"translation_id","verse_id"}
package com.example.mushafconsolidated.Entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "newnasb")
class NewNasbEntity constructor(
    var surah: Int,
    var ayah: Int,
    var indexstart: Int,
    var indexend: Int,
    var ismstart: Int,
    var ismend: Int,
    var khabarstart: Int,
    var khabarend: Int,
    var harfwordno: Int,
    var ismstartwordno: Int,
    var ismendwordno: Int,
    var khabarstartwordno: Int,
    var khabarendwordno: Int,
    var mahdoof: Int,
    var comment: String?,
    @field:PrimaryKey(
        autoGenerate = true
    ) var id: Int
)