//primaryKeys ={"translation_id","verse_id"}
package com.example.mushafconsolidated.Entities

import android.text.SpannableString
import android.text.SpannableStringBuilder
import androidx.room.Ignore
import androidx.room.PrimaryKey


class ShartListingPojo constructor(){
    var surah: Int=0
    var ayah: Int=0
    var indexstart: Int=0
    var indexend: Int=0
    var shartindexstart: Int=0
    var shartindexend: Int=0
    var jawabshartindexstart: Int=0
    var jawabshartindexend: Int=0
    var harfwordno: Int=0
    var shartstatwordno: Int=0
    var shartendwordno: Int=0
    var jawabstartwordno: Int=0
    var jawabendwordno: Int=0
    var comment: String? = null
    var qurantext: String? = null
    var has_prostration: Int=0
    var translation: String? = null
    var en_transliteration: String? = null
    var en_jalalayn: String? = null
    var en_arberry: String? = null
    var ar_irab_two: String? = null
    var ur_jalalayn: String? = null
    var ur_junagarhi: String? = null
    var passage_no: String? = null
    var tafsir_kathir: String? = null
    @Ignore
    var spannableVerse: SpannableString? = null
    @Ignore
    var erabspnabble: SpannableStringBuilder? = null


    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

}