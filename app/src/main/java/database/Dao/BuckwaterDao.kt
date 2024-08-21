package database.Dao

import androidx.room.Dao
import androidx.room.Query
import database.entity.BuckwaterEntitiy

@Dao
interface BuckwaterDao {
    @get:Query(value = "SELECT * FROM buckwater")
    val arabic: List<BuckwaterEntitiy?>?
}