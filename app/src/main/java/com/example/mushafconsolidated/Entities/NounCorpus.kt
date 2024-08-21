package com.example.mushafconsolidated.Entities

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey


@Entity(tableName = "nouncorpus")
class NounCorpus {
    @Ignore
    var count: Int = 0
    var root_a: String?
    var lemma_a: String?
    var araword: String?
    var surah: Int
    var ayah: Int
    var wordno: Int
    var token: Int
    var words: String?
    var tag: String
    var propone: String?
    var proptwo: String?
    var form: String?
    var lemma: String?
    var root: String?
    var gendernumber: String?
    var type: String?
    var cases: String?

    @PrimaryKey(autoGenerate = true)
    var id: Int
    var details: String

    constructor(
        root_a: String?,
        lemma_a: String?,
        araword: String?,
        surah: Int,
        ayah: Int,
        wordno: Int,
        token: Int,
        words: String?,
        tag: String,
        propone: String?,
        proptwo: String?,
        form: String?,
        lemma: String?,
        root: String?,
        gendernumber: String?,
        type: String?,
        cases: String?,
        id: Int,
        details: String
    ) {
        this.root_a = root_a
        this.lemma_a = lemma_a
        this.araword = araword
        this.surah = surah
        this.ayah = ayah
        this.wordno = wordno
        this.token = token
        this.words = words
        this.tag = tag
        this.propone = propone
        this.proptwo = proptwo
        this.form = form
        this.lemma = lemma
        this.root = root
        this.gendernumber = gendernumber
        this.type = type
        this.cases = cases
        this.id = id
        this.details = details
    }

    @Ignore
    constructor(
        count: Int,
        root_a: String?,
        lemma_a: String?,
        araword: String?,
        surah: Int,
        ayah: Int,
        wordno: Int,
        token: Int,
        words: String?,
        tag: String,
        propone: String?,
        proptwo: String?,
        form: String?,
        lemma: String?,
        root: String?,
        gendernumber: String?,
        type: String?,
        cases: String?,
        id: Int,
        details: String
    ) {
        this.count = count
        this.root_a = root_a
        this.lemma_a = lemma_a
        this.araword = araword
        this.surah = surah
        this.ayah = ayah
        this.wordno = wordno
        this.token = token
        this.words = words
        this.tag = tag
        this.propone = propone
        this.proptwo = proptwo
        this.form = form
        this.lemma = lemma
        this.root = root
        this.gendernumber = gendernumber
        this.type = type
        this.cases = cases
        this.id = id
        this.details = details
    }
}