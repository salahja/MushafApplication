package com.example.mushafconsolidated.Entities

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "verbcorpus")
class oVerbCorpus constructor(
    @Ignore
    var count: Int = 0,
    var chapterno: Int,
    var verseno: Int,
    var wordno: Int,
    var token: Int,
    var root_a: String?,
    var form: String?,
    var thulathibab: String?,
    var tag: String?,
    var details: String?,
    var POS: String?,
    var tense: String?,
    var voice: String?,
    var lemma_b: String?,
    var root_b: String?,
    var gendernumber: String?,
    var mood_kananumbers: String?,
    var kana_mood: String?,
    var lemma_a: String?,
    @field:PrimaryKey(
        autoGenerate = true
    ) var id: Int
) {


}