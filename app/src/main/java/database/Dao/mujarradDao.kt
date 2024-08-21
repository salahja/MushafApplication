package database.Dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import database.entity.MujarradVerbs

@Dao
interface mujarradDao {
    @Query(value = "SELECT * FROM mujarrad where root=:root")
    fun getverbTri(root: String?): List<MujarradVerbs?>?

    @Query(value = "SELECT * FROM mujarrad order by root")
    fun getverbTriAll(): List<MujarradVerbs?>?

    @Query(value = "select DISTINCT root,bab,babname ,verbtype,kov,kovname,id,verb from mujarrad where kov=:kov order by root limit 30")
    fun getMujarradWeakness(kov: String?): List<MujarradVerbs?>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(entity: MujarradVerbs?): Long



    //
    @Query(value = "SELECT * FROM mujarrad where root=:root")
    fun getverbTrilive(root: String?): LiveData<List<MujarradVerbs>>

    @Query(value = "SELECT * FROM mujarrad where root=:root")
    fun getMujarradList(root: String?): List<MujarradVerbs>

    @Query(value = "SELECT * FROM mujarrad order by root")
    fun getverbTriAlllive(): LiveData<List<MujarradVerbs>>

    @Query(value = "select DISTINCT root,bab,babname ,verbtype,kov,kovname,id,verb from mujarrad where kov=:kov order by root limit 30")
    fun getMujarradWeaknesslive(kov: String?): LiveData<List<MujarradVerbs>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertlive(entity: MujarradVerbs?): Long

}