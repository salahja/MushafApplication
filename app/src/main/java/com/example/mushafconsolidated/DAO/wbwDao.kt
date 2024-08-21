package com.example.mushafconsolidated.DAO

import androidx.room.Dao
import androidx.room.Query
import com.example.mushafconsolidated.Entities.wbwentity


//.wbwentity


@Dao
interface wbwDao {
    @Query("SELECT * FROM wbw WHERE surah=:id")
    fun getwbwQuran(id: Int): List<wbwentity>

    @Query("SELECT * FROM wbw WHERE juz=:chapterno")
    fun getwbwQuranByjuz(chapterno: Int): List<wbwentity>

    @Query("SELECT * FROM wbw WHERE surah=:chapterno and ayah=:aya and wordno=:word")
    fun getwbwQuranBySurahAyahWord(chapterno: Int, aya: Int, word: Int): List<wbwentity>

    @Query("SELECT * FROM wbw WHERE surah=:chapterno and ayah=:aya and wordno>=:fw and wordno<=:lw")
    fun getwbwQuranbTranslationbyrange(chapterno: Int, aya: Int, fw: Int, lw: Int): List<wbwentity>

    @Query("SELECT * FROM wbw WHERE surah=:id and ayah=:aid ")
    fun getwbwQuranBySurahAyah(id: Int, aid: Int): List<wbwentity>

    @Query("SELECT * FROM wbw where surah=:surahid and ayah=:ayahid and wordno=:wordno")
    fun getwbwTranslationbywordno(surahid: Int, ayahid: Int, wordno: Int): List<wbwentity>
}