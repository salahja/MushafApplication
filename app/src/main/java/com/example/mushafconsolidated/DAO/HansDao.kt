package com.example.mushafconsolidated.DAO

import androidx.room.Dao
import androidx.room.Query
import com.example.mushafconsolidated.Entities.hanslexicon


@Dao
interface HansDao {
    @Query("SELECT * FROM hansdictionary where rootword=:root")
    fun getHansDefinition(root: String?): List<hanslexicon?>?

    @Query("SELECT * FROM hansdictionary where rootword=:root")
    fun getHansDefinitionl(root: String): List<hanslexicon>


}