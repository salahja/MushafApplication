//primaryKeys ={"translation_id","verse_id"}
package com.example.mushafconsolidated.Entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "sifa")
class SifaEntity constructor(
    var surah: Int,
    var ayah: Int,
    var wordno: Int,
    var startindex: Int,
    var endindex: Int,
    var phrasetype: String,
    var ismousufconnected: Int,
    var comment: String?,
    @field:PrimaryKey(
        autoGenerate = true
    ) var id: Int
)