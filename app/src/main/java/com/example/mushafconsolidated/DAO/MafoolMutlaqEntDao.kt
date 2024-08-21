package com.example.mushafconsolidated.DAO

import androidx.room.Dao
import androidx.room.Query
import com.example.mushafconsolidated.Entities.MafoolMutlaqEnt


//.MafoolMutlaqEnt


@Dao
interface MafoolMutlaqEntDao {
    @Query("SELECT * FROM mutlaqword ORDER BY surah,ayah")
    fun getall(): List<MafoolMutlaqEnt>

    @Query("SELECT * FROM mutlaqword where surah=:surah and ayah=:ayah and wordno=:wordno")
    fun getMafoolbihiword(surah: Int, ayah: Int, wordno: Int): List<MafoolMutlaqEnt>

    @Query("SELECT * FROM mutlaqword where surah=:surah ")
    fun getMutlaqsurah(surah: Int): List<MafoolMutlaqEnt>
}