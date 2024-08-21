package com.example.mushafconsolidated.DAO

import androidx.room.Dao
import androidx.room.Query
import com.example.mushafconsolidated.Entities.lanerootdictionary


@Dao
interface LaneRootDao {
    @Query("SELECT * FROM lanesrootdictionary where rootarabic=:root")
    fun getLanesRootDefinition(root: String): List<lanerootdictionary>
}