package com.example.mushafconsolidated.Entities

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey


//primaryKeys ={"translation_id","verse_id"}

@Entity(tableName = "quranexplorer")
class quranexplorer constructor(
    var title: String, var ayahref: String?, @field:PrimaryKey(
        autoGenerate = true
    ) var id: Int
) {

    @Ignore
    var isSelected: Boolean = false

}