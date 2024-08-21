package com.example.mushafconsolidated.DAO

import androidx.room.Dao
import androidx.room.Query
import com.example.mushafconsolidated.Entities.lughat


@Dao
interface LughatDao {
    @Query("SELECT * FROM onlylugat ORDER BY surah,ayah")
    fun getall(): List<lughat>

    @Query("SELECT * FROM onlylugat where rootword=:root")
    fun getRootWordDictionary(root: String?): List<lughat>

    @Query("SELECT * FROM onlylugat where arabicword=:root")
    fun getArabicWord(root: String?): List<lughat>
}