package com.example.mushafconsolidated.DAO

import androidx.room.Dao
import androidx.room.Query
import com.example.mushafconsolidated.Entities.NewNasbEntity


//.NewNasbEntity


@Dao
interface NewNasbDao {
    @Query("SELECT * FROM newnasb where surah=:id order by surah,ayah ")
    fun getHarfNasbIndices(id: Int): List<NewNasbEntity>

    @Query("SELECT * FROM newnasb where surah=:id and ayah=:aid order by surah,ayah ")
    fun getHarfNasbIndicesSurahAyah(id: Int, aid: Int): List<NewNasbEntity>?

    @get:Query("SELECT * FROM newnasb   order by surah,ayah ")
    val harfNasbIndAll: List<NewNasbEntity>
}