package com.example.mushafconsolidated.DAO

import androidx.room.Dao
import androidx.room.Query
import com.example.mushafconsolidated.Entities.jsonsurahentity


//.lanelexicon


@Dao
interface JasonSurahDao {
    @Query("SELECT * FROM surahjson where surah=:sid")
    fun getSurahJson(sid: Int): List<jsonsurahentity>?

    @Query("SELECT * FROM surahjson ")
    fun getSurahJsonall(): List<jsonsurahentity>

}

