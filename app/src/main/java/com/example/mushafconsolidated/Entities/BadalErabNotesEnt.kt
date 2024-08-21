//primaryKeys ={"translation_id","verse_id"}
package com.example.mushafconsolidated.Entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "badal")
class BadalErabNotesEnt constructor(
    var surah: Int, var ayah: Int, var text: String, @field:PrimaryKey(
        autoGenerate = true
    ) var id: Int
)