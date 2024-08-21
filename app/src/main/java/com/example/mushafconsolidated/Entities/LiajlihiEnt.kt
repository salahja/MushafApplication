//primaryKeys ={"translation_id","verse_id"}
package com.example.mushafconsolidated.Entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "liajlihi")
class LiajlihiEnt constructor(
    var surah: Int, var ayah: Int, var wordno: Int, var word: String, @field:PrimaryKey(
        autoGenerate = true
    ) var id: Int
)