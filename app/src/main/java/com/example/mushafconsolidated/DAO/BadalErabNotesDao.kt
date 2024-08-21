package com.example.mushafconsolidated.DAO

import androidx.room.Dao
import androidx.room.Query
import com.example.mushafconsolidated.Entities.BadalErabNotesEnt


@Dao
interface BadalErabNotesDao {
    @Query("SELECT * FROM BADAL where surah=:surah and ayah=:ayah ORDER by surah,ayah")
    fun getBadalNotesSurahAyah(surah: Int, ayah: Int): List<BadalErabNotesEnt>

    @Query("SELECT * FROM badal where surah=:surah ORDER by surah,ayah")
    fun getBadalNotesSurah(surah: Int): List<BadalErabNotesEnt>
}