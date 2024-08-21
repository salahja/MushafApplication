//primaryKeys ={"translation_id","verse_id"}
package com.example.mushafconsolidated.Entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "jumlahaliy")
class HalEnt constructor(
    var surah: Int, var ayah: Int, var status: Int, var text: String, @field:PrimaryKey(
        autoGenerate = true
    ) var id: Int
)