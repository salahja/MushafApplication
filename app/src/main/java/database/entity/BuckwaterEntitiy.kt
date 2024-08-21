package database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "buckwater")
class BuckwaterEntitiy(
    @field:PrimaryKey var decimel: Int,
    var hex: String,
    var ascii: String,
    var orthography: String,
    var arabic: String?=null
)