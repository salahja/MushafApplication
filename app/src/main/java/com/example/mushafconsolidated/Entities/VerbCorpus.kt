package com.example.mushafconsolidated.Entities

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "verbcorpus")
class VerbCorpus {
    @Ignore
    var count: Int = 0
    var chapterno: Int
    var verseno: Int
    var wordno: Int
    var token: Int
    var root_a: String?
    var form: String?
    var thulathibab: String?
    var tag: String?
    var details: String?
    var POS: String?
    var tense: String?
    var voice: String?
    var lemma_b: String?
    var root_b: String?
    var gendernumber: String?
    var mood_kananumbers: String?
    var kana_mood: String?
    var lemma_a: String?

    @PrimaryKey(autoGenerate = true)
    var id: Int

    constructor(

        chapterno: Int,
        verseno: Int,
        wordno: Int,
        token: Int,
        root_a: String?,
        form: String?,
        thulathibab: String?,
        tag: String?,
        details: String?,
        POS: String?,
        tense: String?,
        voice: String?,
        lemma_b: String?,
        root_b: String?,
        gendernumber: String?,
        mood_kananumbers: String?,
        kana_mood: String?,
        lemma_a: String?,
        id: Int
    ) {
        this.chapterno = chapterno
        this.verseno = verseno
        this.wordno = wordno
        this.token = token
        this.root_a = root_a
        this.form = form
        this.thulathibab = thulathibab
        this.tag = tag
        this.details = details
        this.POS = POS
        this.tense = tense
        this.voice = voice
        this.lemma_b = lemma_b
        this.root_b = root_b
        this.gendernumber = gendernumber
        this.mood_kananumbers = mood_kananumbers
        this.kana_mood = kana_mood
        this.lemma_a = lemma_a
        this.id = id
    }

    @Ignore
    constructor(
        count: Int,
        chapterno: Int,
        verseno: Int,
        wordno: Int,
        token: Int,
        root_a: String?,
        form: String?,
        thulathibab: String?,
        tag: String?,
        details: String?,
        POS: String?,
        tense: String?,
        voice: String?,
        lemma_b: String?,
        root_b: String?,
        gendernumber: String?,
        mood_kananumbers: String?,
        kana_mood: String?,
        lemma_a: String?,
        id: Int
    ) {
        this.count = count
        this.chapterno = chapterno
        this.verseno = verseno
        this.wordno = wordno
        this.token = token
        this.root_a = root_a
        this.form = form
        this.thulathibab = thulathibab
        this.tag = tag
        this.details = details
        this.POS = POS
        this.tense = tense
        this.voice = voice
        this.lemma_b = lemma_b
        this.root_b = root_b
        this.gendernumber = gendernumber
        this.mood_kananumbers = mood_kananumbers
        this.kana_mood = kana_mood
        this.lemma_a = lemma_a
        this.id = id
    }
}