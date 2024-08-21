//primaryKeys ={"translation_id","verse_id"}
package com.example.mushafconsolidated.Entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "surahsummary")
class surahsummary constructor(
    var title: String, var summary: String, var versesrange: String, @field:PrimaryKey(
        autoGenerate = true
    ) var surahid: Int
)