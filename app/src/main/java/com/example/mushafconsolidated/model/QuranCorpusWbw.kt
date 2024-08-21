package com.example.mushafconsolidated.model

import androidx.room.Embedded
import androidx.room.Relation
import com.example.mushafconsolidated.Entities.CorpusEntity
import com.example.mushafconsolidated.Entities.wbwentity

data class QuranCorpusWbw constructor(


    @Embedded val corpus: CorpusEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "id"
    )
    val wbw: wbwentity,


    ) {

}
