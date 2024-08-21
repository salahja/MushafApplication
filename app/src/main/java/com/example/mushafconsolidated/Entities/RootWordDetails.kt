package com.example.mushafconsolidated.Entities

import android.text.SpannableString
import androidx.room.Ignore


class RootWordDetails {
    @Ignore
    var verses: SpannableString? = null
    var arabic: String? = null
    var lemma: String? = null
    var araone: String? = null
    var aratwo: String? = null
    var arathree: String? = null
    var arafour: String? = null
    var arafive: String? = null
    var tagone: String? = null
    var tagtwo: String? = null
    var tagthree: String? = null
    var tagfour: String? = null
    var tagfive: String? = null
    var tag: String? = null
    var wordcount: Int = 0
    var surah: Int = 0
    var ayah: Int = 0
    var wordno: Int = 0
    var rootarabic: String? = null
    var en: String? = null
    var abjadname: String? = null
    var namearabic: String? = null
    var nameenglish: String? = null

    constructor()
    constructor(
        arabic: String?,
        lemma: String?,
        araone: String?,
        aratwo: String?,
        arathree: String?,
        arafour: String?,
        arafive: String?,
        tagone: String?,
        tagtwo: String?,
        tagthree: String?,
        tagfour: String?,
        tagfive: String?,
        tag: String?,
        wordcount: Int,
        surah: Int,
        ayah: Int,
        wordno: Int,
        rootarabic: String?,
        en: String?,
        abjadname: String?,
        namearabic: String?,
        nameenglish: String?
    ) {
        this.arabic = arabic
        this.lemma = lemma
        this.araone = araone
        this.aratwo = aratwo
        this.arathree = arathree
        this.arafour = arafour
        this.arafive = arafive
        this.tagone = tagone
        this.tagtwo = tagtwo
        this.tagthree = tagthree
        this.tagfour = tagfour
        this.tagfive = tagfive
        this.tag = tag
        this.wordcount = wordcount
        this.surah = surah
        this.ayah = ayah
        this.wordno = wordno
        this.rootarabic = rootarabic
        this.en = en
        this.abjadname = abjadname
        this.namearabic = namearabic
        this.nameenglish = nameenglish
    }
}