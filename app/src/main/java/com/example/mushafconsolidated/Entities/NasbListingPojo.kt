//primaryKeys ={"translation_id","verse_id"}
package com.example.mushafconsolidated.Entities

import android.text.SpannableString
import android.text.SpannableStringBuilder
import androidx.room.Ignore
import androidx.room.PrimaryKey


class NasbListingPojo constructor(){
    var surah: Int=0
    var ayah: Int=0
    var indexstart: Int=0
    var indexend: Int=0
    var ismstart: Int=0
    var ismend: Int=0
    var khabarstart: Int=0
    var khabarend: Int=0
    var harfwordno: Int=0
    var ismstartwordno: Int=0
    var ismendwordno: Int=0
    var khabarstartwordno: Int=0
    var khabarendwordno: Int=0
    var mahdoof: String? = null
    var comment: String? = null
    var has_prostration: Int=0
    var qurantext: String? = null
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