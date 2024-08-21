package com.example.mushafconsolidated.DAO

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.example.mushafconsolidated.Entities.ChaptersAnaEntity


@Dao
interface AnaQuranChapterDao {
    @Query("SELECT * FROM chaptersana ORDER BY chapterid")
    fun chapterslist(): List<ChaptersAnaEntity>

    @Query("SELECT * FROM chaptersana ORDER BY chapterid")
    fun chaptersl(): LiveData<List<ChaptersAnaEntity>>


    @Query("SELECT * FROM chaptersana where chapterid=:id")
    fun getSingleChapters(id: Int): List<ChaptersAnaEntity?>?
}

