package com.example.mushafconsolidated.DAO

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.example.mushafconsolidated.Entities.surahsummary


//.surahsummary


@Dao
interface surahsummaryDao {
    @Query("SELECT * FROM surahsummary where surahid=:id   ORDER BY surahid")
    fun getSurahSummary(id: Int): LiveData<List<surahsummary>>
}