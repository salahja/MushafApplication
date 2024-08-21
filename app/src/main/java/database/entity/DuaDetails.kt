package database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dua")
class DuaDetails(
    @field:PrimaryKey private var _id: Int, var group_id: Int, @field:ColumnInfo(
        defaultValue = "0"
    ) var fav: Int, var ar_dua: String, var en_translation: String, var en_reference: String
) {

    fun get_id(): Int {
        return _id
    }

    fun set_id(_id: Int) {
        this._id = _id
    }
}