package sj.hisnul.entity
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "namedetails")
class AllahNamesDetails constructor(
    @field:PrimaryKey var id: Int,
    var title: String,
    var summary: String,
    var details: String,
    var ref: String?
)