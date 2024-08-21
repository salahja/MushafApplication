//primaryKeys ={"translation_id","verse_id"}
package com.example.mushafconsolidated.Entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "shart")
class ShartEntity constructor(
    var surah: Int,
    var ayah: Int,
    var indexstart: Int,
    var indexend: Int,
    var shartindexstart: Int,
    var shartindexend: Int,
    var jawabshartindexstart: Int,
    var jawabshartindexend: Int,
    var sharttype: String,
    var comment: String,
    @field:PrimaryKey(
        autoGenerate = true
    ) var id: Int
)