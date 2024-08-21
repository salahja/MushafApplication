package com.example.mushafconsolidated.Entities

import android.text.SpannableString
import androidx.room.Ignore
import com.example.mushafconsolidated.model.CorpusWbwWord
import com.example.mushafconsolidated.model.Word
import com.example.mushafconsolidated.model.WordSpan


class CorpusVerbWbwOccurance {
    @Ignore
    var arabicword: String? = null

    @Ignore
    var spannedverb: SpannableString? = null

    @Ignore
    var corpusWbwWordsword: ArrayList<CorpusWbwWord>? = null

    @Ignore
    var word: ArrayList<Word>? = null

    @Ignore
    var wordspan: ArrayList<WordSpan>? = null
    var root_a: String? = null
    var surah: Int = 0
    var ayah: Int = 0
    var wordno: Int = 0
    var wordcount: Int = 0


    @Ignore
    var quranversesSpannable: SpannableString? = null
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
    var voice: String? = null
    var form: String? = null
    var thulathibab: String? = null
    var tense: String? = null
    var gendernumber: String? = null
    var mood_kananumbers: String? = null
    var kana_mood: String? = null
    var en: String? = null
    var translation: String? = null
    var ur_jalalayn: String? = null
    var en_jalalayn: String? = null
    var en_arberry: String? = null


    @Ignore
    var verses: SpannableString? = null


    @Ignore
    var spannedarabicverb: SpannableString? = null

    constructor()
    constructor(
        tagone: String?,
        tagtwo: String?,
        tagthree: String?,
        tagfour: String?,
        tagfive: String?,
        araone: String?,
        aratwo: String?,
        arathree: String?,
        arafour: String?,
        arafive: String?,
        arabicword: String?,
        translation: String?,
        ur_jalalayn: String?,
        en_jalalayn: String?,
        qurantext: String?,
        quranversesSpannable: SpannableString?,
        root_a: String,
        surah: Int,
        ayah: Int,
        wordno: Int,
        wordcount: Int,
        voice: String?,
        form: String?,
        thulathibab: String?,
        tenseStr: String?,
        genderNumberdetails: String?,
        mood_kananumbers: String?,
        kana_mood: String?,
        en: String?,
        setWordSpanNew: SpannableString?,
        detailsone: String?,
        detailstwo: String?,
        detailsthree: String?,
        detailsfour: String?,
        detailsfive: String?
    ) {
        // this.word = word;
        // this.wordspanDark = wordspan;
        this.arabicword = arabicword
        this.root_a = root_a
        this.surah = surah
        this.ayah = ayah
        this.wordno = wordno
        this.wordcount = wordcount
        this.quranversesSpannable = quranversesSpannable
        this.qurantext = qurantext
        this.translation = translation
        this.ur_jalalayn = ur_jalalayn
        this.en_jalalayn = en_jalalayn
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
        this.voice = voice
        this.form = form
        this.thulathibab = thulathibab
        this.mood_kananumbers = mood_kananumbers
        this.kana_mood = kana_mood
        this.en = en
    }
}