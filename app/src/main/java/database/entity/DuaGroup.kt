package database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dua_group")
class DuaGroup(
    var ar_title: String,
    var en_title: String,
    var fr_title: String,
    @field:PrimaryKey private var _id: Int
) {

    fun get_id(): Int {
        return _id
    }

    fun set_id(_id: Int) {
        this._id = _id
    }
}