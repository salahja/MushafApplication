package com.example.mushafconsolidated.DAO

import androidx.room.Dao
import androidx.room.Query
import com.example.mushafconsolidated.Entities.quranexplorer

//.quranexplorer


@Dao
interface QuranExplorerDao {
    @Query("SELECT * FROM quranexplorer where title LIKE '%' || :search || '%'")
    fun getFilter(search: String?): List<quranexplorer>?

    @get:Query("SELECT * FROM quranexplorer order by title")
    val aLL: List<quranexplorer>?
}