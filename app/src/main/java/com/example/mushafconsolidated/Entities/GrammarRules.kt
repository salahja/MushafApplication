package com.example.mushafconsolidated.Entities

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "rules")
class GrammarRules(
    var harf: String, var worddetails: String?, var detailsrules: String, @field:PrimaryKey(
        autoGenerate = true
    ) var id: Int
)