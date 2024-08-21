package com.example.mushafconsolidated.DAO

import androidx.room.Dao
import androidx.room.Query
import com.example.mushafconsolidated.Entities.HalEnt

@Dao
interface HaliyaDao {
    @Query("SELECT * FROM jumlahaliy where surah=:surah and ayah=:ayah and status=1")
    fun getHaliya(surah: Int, ayah: Int): List<HalEnt>

    @Query("SELECT * FROM jumlahaliy where surah=:surah and status=1")
    fun getHaliyaSurah(surah: Int): List<HalEnt>
}