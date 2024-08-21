package sj.hisnul.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "hduanames")
class hduanamesEnt(
    @field:PrimaryKey(autoGenerate = true) var dua_global_id: Int,
    var book_id: Int,
    var chap_id: Int,
    var dua_id: String?,
    var chapname: String?,
    var duaname: String,
    var tags: String?,
    var ID: String,
    var category: String,
    var fav: Int
)