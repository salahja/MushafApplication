package com.example.mushafconsolidated.DAO

import androidx.room.Dao
import androidx.room.Query
import com.example.mushafconsolidated.Entities.Qari


//.Qari


@Dao
interface QariDao {
    @get:Query("SELECT * FROM audio ORDER BY id")
    val qaris: List<Qari>

    @Query("SELECT * FROM audio where name=:id")
    fun getSelectedQari(id: String?): List<Qari>
}