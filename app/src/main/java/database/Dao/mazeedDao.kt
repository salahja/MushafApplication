package database.Dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import database.entity.MazeedEntity
import database.entity.MujarradVerbs

@Dao
interface mazeedDao {
    @Query(value = "SELECT * FROM mazeed where root=:root")
    fun getMazeedRoot(root: String?): List<MazeedEntity?>?

    @get:Query(value = "SELECT * FROM mazeed order by root")
    val mazeedAll: List<MazeedEntity?>?

    @Query(value = "select DISTINCT root,form,babname ,verbtype,kov,kovname,id from mazeed where kov=:kov order by root limit 30")
    fun getMazeedWeakness(kov: String?): List<MazeedEntity?>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(entity: MujarradVerbs?): Long



    ///


    @Query(value = "SELECT * FROM mazeed where root=:root")
    fun getMazeedRootlive(root: String?): LiveData<List<MazeedEntity>>
    @Query(value = "SELECT * FROM mazeed where root=:root")
    fun getMazeedRootlist(root: String?): List<MazeedEntity>
    @Query(value = "SELECT * FROM mazeed order by root")
    fun getMazeedAlllive(): LiveData<List<MazeedEntity>>

    @Query(value = "select DISTINCT root,form,babname ,verbtype,kov,kovname,id from mazeed where kov=:kov order by root limit 30")
    fun getMazeedWeaknesslive(kov: String?): LiveData<List<MazeedEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertlive(entity: MazeedEntity?): Long




}