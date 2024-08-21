package com.example.mushafconsolidated.Entities

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "bookmark")
class BookMarks constructor(

    var header: String?,
    var verseno: String,
    var chapterno: String,
    var surahname: String,
    var datetime: String?,
    @field:PrimaryKey(
        autoGenerate = true
    ) var id: Int


) {

    @Ignore
    var count: String? = null

    constructor() : this("dummy title", "dummy author", "dummy abstract", "", "", 0)

    /*
          constructor(


                header: String?,
                verseno: String,
                chapterno: String,
                surahname: String,
                datetime: String?,

                count: String?
            ) : this(header, verseno, chapterno, surahname, datetime, count) */


}