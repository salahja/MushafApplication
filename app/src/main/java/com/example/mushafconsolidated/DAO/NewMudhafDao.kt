package com.example.mushafconsolidated.DAO

import androidx.room.Dao
import androidx.room.Query
import com.example.mushafconsolidated.Entities.NewMudhafEntity


//.NewMudhafEntity


@Dao
interface NewMudhafDao {
    @get:Query("SELECT * FROM newmudhaf ORDER BY surah,ayah")
    val mudhafAll: List<NewMudhafEntity>

    @Query("SELECT * FROM newmudhaf where surah=:id ORDER BY surah,ayah")
    fun getMudhafSurah(id: Int): List<NewMudhafEntity>

    @Query("SELECT * FROM newmudhaf where surah=:id and ayah=:aid ORDER BY surah,ayah")
    fun getMudhafSurahAyah(id: Int, aid: Int): List<NewMudhafEntity>

    @Query("SELECT * FROM newmudhaf where surah=:id and ayah=:aid and wordfrom=:wordno ORDER BY surah,ayah")
    fun getMudhafSurahAyahWord(id: Int, aid: Int, wordno: Int): List<NewMudhafEntity>
}