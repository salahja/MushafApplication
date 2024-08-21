package com.example.mushafconsolidated.DAO

import androidx.room.Dao
import androidx.room.Query
import com.example.mushafconsolidated.Entities.GrammarRules


@Dao
interface grammarRulesDao {
    @get:Query("select *  from rules   ")
    val grammarRules: List<GrammarRules>?

    @Query("select *  from rules where harf=:harf")
    fun getGrammarRulesByHarf(harf: String?): List<GrammarRules>?
}