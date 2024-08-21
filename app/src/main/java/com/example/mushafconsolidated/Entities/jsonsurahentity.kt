//primaryKeys ={"translation_id","verse_id"}
package com.example.mushafconsolidated.Entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "surahjson")
class jsonsurahentity constructor(

    var surah: Int,
    var jasonstr: String,
    @field:PrimaryKey(
        autoGenerate = true
    ) var docid: Int
)