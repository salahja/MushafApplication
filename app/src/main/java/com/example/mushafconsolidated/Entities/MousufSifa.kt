package com.example.mushafconsolidated.Entities

import android.text.SpannableString


class MousufSifa {
    var surah: Int = 0
    var ayah: Int = 0
    var wordno: Int = 0
    var startindex: Int = 0
    var endindex: Int = 0
    private var wordfrom: Int = 0
    private var wordto: Int = 0
    var verse: SpannableString? = null
    private var translations: String? = null
    private var phrasetype: String? = null
    private var disconnected: Int = 0
    var comment: String? = null

    constructor(
        surah: Int,
        ayah: Int,
        startindex: Int,
        endindex: Int,
        wordfrom: Int,
        wordto: Int,
        disconnected: Int,
        comment: String?
    ) {
        this.surah = surah
        this.ayah = ayah
        this.startindex = startindex
        this.endindex = endindex
        this.wordfrom = wordfrom
        this.wordto = wordto
        this.disconnected = disconnected
        this.comment = comment
    }

    constructor(
        surah: Int,
        ayah: Int,
        wordno: Int,
        startindex: Int,
        endindex: Int,
        verse: SpannableString?,
        translations: String?,
        phrasetype: String?,
        disconnected: Int,
        comment: String?
    ) {
        this.surah = surah
        this.ayah = ayah
        this.wordno = wordno
        this.startindex = startindex
        this.endindex = endindex
        this.verse = verse
        this.translations = translations
        this.phrasetype = phrasetype
        this.disconnected = disconnected
        this.comment = comment
    }

    constructor(
        surah: Int,
        ayah: Int,
        wordno: Int,
        startindex: Int,
        endindex: Int,
        disconnected: Int,
        comment: String?
    ) {
        this.surah = surah
        this.ayah = ayah
        this.wordno = wordno
        this.startindex = startindex
        this.endindex = endindex
        this.disconnected = disconnected
        this.comment = comment
    }

    constructor()
}