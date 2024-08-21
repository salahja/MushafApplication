package com.example.mushafconsolidated.Entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "onlylugat")
class lughat constructor(
    var surah: Int,
    var ayah: Int,
    var surahname: String,
    var wordno: Int,
    var rootword: String,
    var hansweir: String?,
    var arabicword: String,
    var ur_lughat: String,
    var meaning: String,
    var letter: String,
    var en_lughat: String,
    var comments: String?,
    @field:PrimaryKey(
        autoGenerate = true
    ) var id: Int
)