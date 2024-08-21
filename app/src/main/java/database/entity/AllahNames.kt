package database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Names")
class AllahNames(
    @field:PrimaryKey var id: Int,
    var arabic: String,
    var trans: String,
    var meaning: String
)