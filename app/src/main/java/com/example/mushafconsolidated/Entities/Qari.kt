package com.example.mushafconsolidated.Entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "audio")
class Qari constructor(
    @field:PrimaryKey(autoGenerate = true) var id: Int,
    var name: String,
    var name_english: String,
    var audiotype: Int,
    var url: String
)