//primaryKeys ={"translation_id","verse_id"}
package com.example.mushafconsolidated.Entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "CorpusExpand")
class CorpusEntity constructor(
    var surah: Int,
    var ayah: Int,
    var wordno: Int,
    var wordcount: Int,
    var araone: String?,
    var aratwo: String?,
    var arathree: String?,
    var arafour: String?,
    var arafive: String?,
    var tagone: String?,
    var tagtwo: String?,
    var tagthree: String?,
    var tagfour: String?,
    var tagfive: String?,
    var rootaraone: String?,
    var rootaratwo: String?,
    var rootarathree: String?,
    var rootarafour: String?,
    var rootarafive: String?,
    var lemaraone: String?,
    var lemaratwo: String?,
    var lemarathree: String?,
    var lemarafour: String?,
    var lemarafive: String?,
    var lemma_b_one: String?,
    var form_one: String?,
    var form_two: String?,
    var form_three: String?,
    var form_four: String?,
    var form_five: String?,
    var detailsone: String?,
    var detailstwo: String?,
    var detailsthree: String?,
    var detailsfour: String?,
    var detailsfive: String?,
    var lemma_b_two: String?,
    var lemma_b_three: String?,
    var lemma_b_four: String?,
    var lemma_b_five: String?,
    @field:PrimaryKey(
        autoGenerate = true
    ) var id: Int,
    var juz: Int
)