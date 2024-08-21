//primaryKeys ={"translation_id","verse_id"}
package com.example.mushafconsolidated.Entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "mafoolbihi")
class MafoolBihi constructor(
    var surah: Int,
    var ayah: Int,
    var wordno: Int,
    var word: String,
    var objectpronoun: String?,
    var comment: String,
    @field:PrimaryKey(
        autoGenerate = true
    ) var id: Int
)