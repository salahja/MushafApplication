package com.example.mushafconsolidated.DAO

import androidx.room.Dao
import androidx.room.Query
import com.example.mushafconsolidated.Entities.qurandictionary


//.qurandictionary


@Dao
interface qurandictionaryDao {
    @get:Query("select *  from qurandictionary  group by rootarabic  ")
    val dictionary: List<qurandictionary?>?

    @Query("select *  from qurandictionary where rootarabic=:root  order by rootarabic  ")
    fun getDictionaryroot(root: String?): List<qurandictionary?>?

    @Query("select * from qurandictionary where rootarabic  LIKE  :arg  group by rootarabic order by rootarabic  ")
    fun getByfirstletter(arg: String?): List<qurandictionary>
}