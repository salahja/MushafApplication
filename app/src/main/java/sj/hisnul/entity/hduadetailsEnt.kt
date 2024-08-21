package sj.hisnul.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "hduadetails")
class hduadetailsEnt(
    var book_id: Int,
    var dua_global_id: Int,
    var ID: String,
    var dua_segment_id: Int,
    var top: String?,
    var arabic_diacless: String?,
    var arabic: String?,
    var transliteration: String?,
    var translations: String?,
    var bottom: String?,
    var reference: String?,
    var app_reference: String?,
    @field:PrimaryKey(
        autoGenerate = true
    ) var pid: Int
)