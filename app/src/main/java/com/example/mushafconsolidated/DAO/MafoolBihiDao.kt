package com.example.mushafconsolidated.DAO

import androidx.room.Dao
import androidx.room.Query
import com.example.mushafconsolidated.Entities.MafoolBihi


@Dao
interface MafoolBihiDao {
    @Query("SELECT * FROM mafoolbihi ORDER BY surah,ayah")
    fun getall(): List<MafoolBihi>?

    @Query("SELECT * FROM mafoolbihi where surah=:surah ORDER BY surah,ayah,wordno")
    fun getBySurah(surah: Int): List<MafoolBihi>

    @Query("SELECT * FROM mafoolbihi where surah=:surah and ayah=:ayah and wordno=:wordno")
    fun getMafoolbihi(surah: Int, ayah: Int, wordno: Int): List<MafoolBihi>

    @Query(value = "UPDATE mafoolbihi set wordno=:no where id=:id")
    fun updateMafoolWord(no: Int, id: Int): Int

    @get:Query(value = "select * from mafoolbihi a,qurans b where a.surah=b.surah and a.ayah=b.ayah")
    val mafoolbihiq: List<MafoolBihi>?
}