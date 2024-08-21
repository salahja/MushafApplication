package com.example.mushafconsolidated.Entities

import android.text.SpannableString
import androidx.room.Ignore
import com.example.mushafconsolidated.model.WordSpan


class CorpusNounWbwOccurance {

    @Ignore
    var verses: SpannableString? = null

    @Ignore
    var wordspan: ArrayList<WordSpan>? = null
    var root_a: String? = null
    var surah: Int = 0
    var ayah: Int = 0
    var wordno: Int = 0
    var wordcount: Int = 0
    var qurantext: String? = null
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
    var detailsone: String? = null
    var detailstwo: String? = null
    var detailsthree: String? = null
    var detailsfour: String? = null
    var detailsfive: String? = null
    var tag: String? = null
    var propone: String? = null
    var proptwo: String? = null
    var form: String? = null
    var gendernumber: String? = null
    var type: String? = null
    var cases: String? = null
    var en: String? = null
    var translation: String? = null
    var ur_jalalayn: String? = null
    var en_jalalayn: String? = null
    var en_arberry: String? = null


    @Ignore
    var spannableNoun: SpannableString? = null


    @Ignore
    var lemma: String? = null


    @Ignore
    var lemmacount: Int = 0


    @Ignore
    var arabicword: String? = null

    constructor()
    constructor(
        root_a: String,
        surah: Int,
        ayah: Int,
        wordno: Int,
        wordcount: Int,
        translation: String?,
        ur_jalalayn: String?,
        en_jalalayn: String?,
        qurantext: String?,
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
        detailsone: String?,
        detailstwo: String?,
        detailsthree: String?,
        detailsfour: String?,
        detailsfive: String?,
        tag: String?,
        propone: String?,
        proptwo: String?,
        form: String?,
        gendernumber: String?,
        type: String?,
        cases: String?,
        en: String?
    ) {
        this.root_a = root_a
        this.surah = surah
        this.ayah = ayah
        this.wordno = wordno
        this.wordcount = wordcount
        this.translation = translation
        this.ur_jalalayn = ur_jalalayn
        this.en_jalalayn = en_jalalayn
        this.qurantext = qurantext
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
        this.detailsone = detailsone
        this.detailstwo = detailstwo
        this.detailsthree = detailsthree
        this.detailsfour = detailsfour
        this.detailsfive = detailsfive
        this.tag = tag
        this.propone = propone
        this.proptwo = proptwo
        this.form = form
        this.gendernumber = gendernumber
        this.type = type
        this.cases = cases
        this.en = en
    }

    @Ignore
    constructor(
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
        translation: String?,
        qurantext: String?,
        verses: SpannableString?,
        root_a: String,
        surah: Int,
        ayah: Int,
        wordno: Int,
        wordcount: Int,
        spannableNoun: SpannableString?,
        tag: String?,
        propone: String?,
        proptwo: String?,
        form: String?,
        gendernumber: String?,
        type: String?,
        cases: String?,
        en: String?
    ) {
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
        this.translation = translation
        this.qurantext = qurantext
        this.verses = verses
        this.root_a = root_a
        this.surah = surah
        this.ayah = ayah
        this.wordno = wordno
        this.wordcount = wordcount
        this.spannableNoun = spannableNoun
        this.tag = tag
        this.propone = propone
        this.proptwo = proptwo
        this.form = form
        this.gendernumber = gendernumber
        this.type = type
        this.cases = cases
        this.en = en
    }

    @Ignore
    constructor(
        root_a: String,
        surah: Int,
        ayah: Int,
        wordno: Int,
        wordcount: Int,
        lemma: String?,
        lemmacount: Int,
        arabicword: String?,
        en: String?
    ) {
        this.root_a = root_a
        this.surah = surah
        this.ayah = ayah
        this.wordno = wordno
        this.wordcount = wordcount
        this.lemma = lemma
        this.lemmacount = lemmacount
        this.arabicword = arabicword
        this.en = en
    }
}