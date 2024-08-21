package com.example.mushafconsolidated.DAO

import androidx.room.Dao
import androidx.room.Query
import com.example.mushafconsolidated.Entities.SifaEntity


//.SifaEntity


@Dao
interface SifaDao {
    @get:Query("SELECT * FROM sifa order by surah,ayah,wordno ")
    val sifaindexesAll: List<SifaEntity>

    @Query("SELECT * FROM sifa where surah=:id order by surah,ayah,wordno ")
    fun getSifaindexesBySurah(id: Int): List<SifaEntity>

    @Query("SELECT * FROM sifa where surah=:id and ayah=:ayd order by surah,ayah,wordno ")
    fun getSifaindexesBySurahAyah(id: Int, ayd: Int): List<SifaEntity>
}