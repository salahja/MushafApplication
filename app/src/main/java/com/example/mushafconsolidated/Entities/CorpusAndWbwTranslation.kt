//primaryKeys ={"translation_id","verse_id"}
package com.example.mushafconsolidated.Entities

import androidx.room.Entity

@Entity(tableName = "CorpusExpand")
class CorpusAndWbwTranslation {
    var en: String? = null
    var surah: Int = 0
    var ayah: Int = 0
    var wordno: Int = 0
    var wordcount: Int = 0
    var araone: String? = null
    var aratwo: String? = null
    var arathree: String? = null
    var arafour: String? = null
    var arafive: String? = null
}