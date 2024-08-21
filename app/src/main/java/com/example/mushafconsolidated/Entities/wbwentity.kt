package com.example.mushafconsolidated.Entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "wbw")
class wbwentity constructor(
    var surah: Int,
    var ayah: Int,
    var wordno: Int,
    var wordcount: Int,
    var araone: String?,
    var aratwo: String?,
    var arathree: String?,
    var arafour: String?,
    var arafive: String?,
    var en: String,
    var bn: String,
    var `in`: String,
    var ur: String?,
    @field:PrimaryKey(autoGenerate = true) var id: Int,
    var juz: Int
)