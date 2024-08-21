package sj.hisnul.Dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import sj.hisnul.entity.hcategoryEnt

@Dao
interface hDuaCategoryDao {
    @Query("SELECT * FROM hcategory ORDER BY id")
    fun getcatetory(): LiveData<List<hcategoryEnt>>
}