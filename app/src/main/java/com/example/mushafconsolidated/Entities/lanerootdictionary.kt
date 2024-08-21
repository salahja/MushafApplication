//primaryKeys ={"translation_id","verse_id"}

package com.example.mushafconsolidated.Entities

import androidx.room.Entity
import androidx.room.PrimaryKey


//primaryKeys ={"translation_id","verse_id"}
@Entity(tableName = "lanesrootdictionary")
class lanerootdictionary(
    var rootarabic: String, var rootbuckwater: String, var definition: String?, @field:PrimaryKey(
        autoGenerate = true
    ) var id: Int
)