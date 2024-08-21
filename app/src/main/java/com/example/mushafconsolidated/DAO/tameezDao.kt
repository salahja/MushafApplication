package com.example.mushafconsolidated.DAO

import androidx.room.Dao
import androidx.room.Query
import com.example.mushafconsolidated.Entities.TameezEnt


//.TameezEnt

@Dao
interface tameezDao {
    @Query("SELECT * FROM tameez ORDER BY surah,ayah")
    fun getall(): List<TameezEnt>

    @Query("SELECT * FROM tameez where surah=:surah Order by surah,ayah")
    fun getTameezSurah(surah: Int): List<TameezEnt>

    @Query("SELECT * FROM tameez where surah=:surah and ayah=:ayah and wordno=:wordno")
    fun getTameezWord(surah: Int, ayah: Int, wordno: Int): List<TameezEnt>
}