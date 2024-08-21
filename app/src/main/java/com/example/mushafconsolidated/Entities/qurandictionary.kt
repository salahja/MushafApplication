//primaryKeys ={"translation_id","verse_id"}
package com.example.mushafconsolidated.Entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "qurandictionary")
class qurandictionary constructor(
    var surah: Int,
    var ayah: Int,
    var wordno: Int,
    var rootarabic: String,
    var rootbuckwater: String,
    @field:PrimaryKey(
        autoGenerate = true
    ) var id: Int
)