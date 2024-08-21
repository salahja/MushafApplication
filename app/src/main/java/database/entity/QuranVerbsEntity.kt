package database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "quranverbs")
class QuranVerbsEntity(
    var verb: String?,
    var root: String?,
    var thulathibab: String?,
    var form: String?,
    var frequency: String?,
    var meaning: String?,
    @field:PrimaryKey var id: Int
)