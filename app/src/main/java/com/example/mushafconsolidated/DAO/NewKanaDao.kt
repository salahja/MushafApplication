package com.example.mushafconsolidated.DAO

import androidx.room.Dao
import androidx.room.Query
import com.example.mushafconsolidated.Entities.NewKanaEntity


//.NewKanaEntity


@Dao
interface NewKanaDao {
    @get:Query("SELECT * FROM newkana ORDER BY surah,ayah")
    val kanaall: List<NewKanaEntity>

    @Query("SELECT * FROM newkana where surah=:id ORDER BY surah,ayah")
    fun getkanabysurah(id: Int): List<NewKanaEntity>

    @Query("SELECT * FROM newkana where surah=:id and ayah=:aid ORDER BY surah,ayah")
    fun getkanabysurahAyah(id: Int, aid: Int): List<NewKanaEntity>
}