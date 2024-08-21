package com.example.mushafconsolidated.DAO

import androidx.room.Dao
import androidx.room.Query
import com.example.mushafconsolidated.Entities.LiajlihiEnt


//.LiajlihiEnt


@Dao
interface liajlihiDao {
    @Query("SELECT * FROM liajlihi ORDER BY surah,ayah")
    fun getall(): List<LiajlihiEnt>

    @Query("SELECT * FROM liajlihi where surah=:surah and ayah=:ayah and wordno=:wordno")
    fun getMafoolLiajlihi(surah: Int, ayah: Int, wordno: Int): List<LiajlihiEnt>

    @Query("SELECT * FROM liajlihi where surah=:surah ORDER by surah,ayah")
    fun getMafoolLiajlihisurah(surah: Int): List<LiajlihiEnt>
}