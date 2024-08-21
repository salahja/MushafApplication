package com.example.mushafconsolidated.DAO

import androidx.room.Dao
import androidx.room.Query
import com.example.mushafconsolidated.Entities.lanelexicon


//.lanelexicon


@Dao
interface LaneDao {
    @Query("SELECT * FROM laneslexicon where rootword=:root")
    fun getLanesDefinition(root: String?): List<lanelexicon?>?
}