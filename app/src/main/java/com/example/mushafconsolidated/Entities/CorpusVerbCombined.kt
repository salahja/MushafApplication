package com.example.mushafconsolidated.Entities

import androidx.room.PrimaryKey

class CorpusVerbCombined constructor(
    var root_a: String,
    var surah: Int,
    var ayah: Int,
    var wordno: Int,
    var wordcount: Int,
    var translation: String,
    var araone: String,
    var aratwo: String,
    var arathree: String,
    var arafour: String,
    var arafive: String,
    var tagone: String,
    var tagtwo: String,
    var tagthree: String,
    var tagfour: String,
    var tagfive: String,
    var detailsone: String,
    var detailstwo: String,
    var detailsthree: String,
    var detailsfour: String,
    var detailsfive: String,
    var en: String,
    var bn: String,
    var `in`: String
) {
    var rootaraone: String? = null
    var rootaratwo: String? = null
    var rootarathree: String? = null
    var rootarafour: String? = null
    var rootarafive: String? = null
    var lemaraone: String? = null
    var lemaratwo: String? = null
    var lemarathree: String? = null
    var lemarafour: String? = null
    var lemarafive: String? = null
    var form_one: String? = null
    var form_two: String? = null
    var form_three: String? = null
    var form_four: String? = null
    var form_five: String? = null
    var qurantext: String? = null

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

}