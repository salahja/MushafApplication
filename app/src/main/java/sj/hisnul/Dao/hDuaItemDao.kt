package sj.hisnul.Dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import sj.hisnul.entity.hduadetailsEnt

@Dao
interface hDuaItemDao {
    @get:Query("SELECT * FROM hduadetails ORDER BY id")
    val duaItemsALL: Flow<List<hduadetailsEnt>>

    @Query("SELECT * FROM hduadetails where ID=:aid")
    fun getDitem(aid: String?): LiveData<List<hduadetailsEnt>>

    @Query("SELECT * FROM hduadetails ORDER BY id")
    fun getAllDuaItems(): Flow<List<hduadetailsEnt>>



}