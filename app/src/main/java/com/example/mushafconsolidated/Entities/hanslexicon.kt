//primaryKeys ={"translation_id","verse_id"}
package com.example.mushafconsolidated.Entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "hansdictionary")
class hanslexicon constructor(
    var rootword: String,
    var word: String,
    var parentid: Int,
    var definition: String,
    @field:PrimaryKey(
        autoGenerate = true
    ) var id: Int
) {

    override fun toString(): String {
        return ("lanelexicon{" +
                "rootword='" + rootword + '\'' +
                ", word='" + word + '\'' +
                ", parentid=" + parentid +
                ", definition='" + definition + '\'' +
                ", id=" + id +
                '}')
    }
}