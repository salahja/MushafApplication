//primaryKeys ={"translation_id","verse_id"}
package com.example.mushafconsolidated.Entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "newmudhaf")
class NewMudhafEntity constructor(
    var surah: Int,
    var ayah: Int,
    var startindex: Int,
    var endindex: Int,
    var wordfrom: Int,
    var wordto: Int,
    var disconnected: Int,
    var comment: String?,
    @field:PrimaryKey(
        autoGenerate = true
    ) var id: Int
)