package com.example.mushafconsolidated.Entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "buckwater")
class BuckWater constructor(
    @field:PrimaryKey var decimel: Int,
    var hex: String,
    var ascii: String,
    var orthography: String,
    var arabic: String
)