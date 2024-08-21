package database.Dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import database.entity.AllahNames

@Dao
interface NamesDao {
    @Query(value = "SELECT * FROM Names")
    fun ALLAH_NAMES_LIST(): List<AllahNames>

    @Query(value = "SELECT * FROM Names")
    fun ALLAH_NAMES_LISTS(): LiveData<List<AllahNames>>
}