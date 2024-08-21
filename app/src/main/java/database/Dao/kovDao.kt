package database.Dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import database.entity.kov

@Dao
interface kovDao {
    @get:Query(value = "SELECT * FROM kov order by id ")
    val rules: List<kov?>?
    @Query("SELECT * FROM kov order by id ")
    fun getkovlive(): LiveData<List<kov>>
}