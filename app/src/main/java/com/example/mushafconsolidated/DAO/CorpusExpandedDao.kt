package com.example.mushafconsolidated.DAO

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.example.mushafconsolidated.Entities.CorpusEntity


//.CorpusEntity


@Dao
interface CorpusExpandedDao {
    @Query("SELECT * FROM CorpusExpand WHERE surah=:id")
    fun getVersesBySurah(id: Int): List<CorpusEntity?>?

    @Query("SELECT * FROM CorpusExpand WHERE surah=:id and ayah=:ayahid")
    fun getVersesBySurahAndAya(id: Int, ayahid: Int): List<CorpusEntity?>?

    //select surah,count(ayah) from CorpusExpand where surah=1 group by ayah
    @Query("select * from CorpusExpand where ayah  between :start and :end")
    fun getVersesByPart(start: Int, end: Int): List<CorpusEntity?>?

    @Query("SELECT * FROM CorpusExpand WHERE surah=:id and ayah=:ayahid and wordno=:wordid")
    fun getCorpusWord(
        id: Int,
        ayahid: Int,
        wordid: Int
    ): List<CorpusEntity?>? //   SELECT VerseNew.chapter_no, VerseNew.verse_no, Translation.author_name, TranslationData.translation FROM TranslationData INNER JOIN Translation
    //  ON Translation.translation_id = TranslationData.translation_id INNER JOIN VerseNew ON VerseNew.verseID = TranslationData.verse_id    WHERE TranslationData.translation_id ="en_sahih"   and VerseNew.chapter_no= 2


    @Query("SELECT * FROM CorpusExpand WHERE surah=:id")
    fun getVersesBySurahLive(id: Int): LiveData<List<CorpusEntity>>

}