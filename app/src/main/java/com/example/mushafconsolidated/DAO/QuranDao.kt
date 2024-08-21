package com.example.mushafconsolidated.DAO

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.example.mushafconsolidated.Entities.QuranEntity
import com.example.mushafconsolidated.model.QuranCorpusWbw


//.QuranEntity


@Dao
interface QuranDao {


    @Transaction
    @Query("SELECT * FROM CorpusExpand JOIN wbw ON wbw.id = CorpusExpand.id where corpusexpand.surah=:surahid and corpusexpand.ayah=:ayahid and corpusexpand.wordno=:wordno")

    fun getQuranCorpusWbw(surahid: Int, ayahid: Int, wordno: Int): List<QuranCorpusWbw>

    @Transaction
    @Query("SELECT * FROM CorpusExpand JOIN wbw ON wbw.id = CorpusExpand.id where corpusexpand.surah=:surahid")

    fun getQuranCorpusWbwbysurah(surahid: Int): List<QuranCorpusWbw>

    @Transaction
    @Query("SELECT * FROM CorpusExpand JOIN wbw ON wbw.id = CorpusExpand.id where  CorpusExpand.rootaraone||CorpusExpand.rootaratwo ||CorpusExpand.rootarathree||CorpusExpand.rootarafour||CorpusExpand.rootarafive=:root")


    fun getQuranCorpusWbwbyRoot(root: String): List<QuranCorpusWbw>

    @Transaction
    @Query("SELECT * FROM CorpusExpand JOIN wbw ON wbw.id = CorpusExpand.id where corpusexpand.surah=:surahid and corpusexpand.ayah=:ayahid")

    fun getQuranCorpusWbwSurhAyah(surahid: Int, ayahid: Int): List<QuranCorpusWbw>


    /*
        @Transaction
        @Query("SELECT * FROM qurans,CorpusExpand,wbw where CorpusExpand.surah=:surahid and CorpusExpand.ayah=:ayahid and CorpusExpand.wordno=:wordno")
        fun getQuranCorpusWbw(surahid: Int,ayahid: Int,wordno: Int): List<QuranCorpusWbw>
    */


    /* @Transaction
     @Query("  SELECT * FROM qurans,CorpusExpand,wbw,nouncorpus JOIN corpusexpand ON CorpusExpand.id = qurans.docid JOIN wbw ON wbw.id = corpusexpand.id  join nouncorpus on wbw.id=nouncorpus.id where corpusexpand.surah=:surahid and corpusexpand.ayah=:ayahid and corpusexpand.wordno=:wordno")
     fun getQuranCorpusWbwNounCorpus(surahid: Int,ayahid: Int,wordno: Int): List<QuranCorpusWbwNounBreakup>
 */


    /*
    SELECT * FROM qurans JOIN corpusexpand ON corpusexpand.id = qurans.docid JOIN wbw ON wbw.id = corpusexpand.id  join nouncorpus on wbw.id=nouncorpus.id
where CorpusExpand.surah=1 and CorpusExpand.ayah=1 and CorpusExpand.wordno=2
     */
    @Query("SELECT * FROM qurans where surah=:surahid")
    fun getQuranVersesBySurah(surahid: Int): List<QuranEntity>?

    @Query("SELECT * FROM qurans order by surah,ayah")
    fun allQuran(): List<QuranEntity>

    @Query("SELECT * FROM qurans where juz=:part")
    fun getQuranbyJuz(part: Int): List<QuranEntity?>?

    @Query("SELECT * FROM qurans where surah=:surahid and ayah=:ayahid")
    fun getsurahayahVerses(surahid: Int, ayahid: Int): List<QuranEntity?>?

    @Query("select * from qurans where  surah =:sura and page = :pageno order by ayah")
    fun getAyahsByPage(sura: Int, pageno: Int): List<QuranEntity?>?

    @Query("select * from qurans where  surah =:juz and page = :pageno order by ayah")
    fun getAyahsByPagejuz(juz: Int, pageno: Int): List<QuranEntity?>?

    @Query("select * from qurans where surah = :surahid and ayah>=:from and ayah<=:toid order by ayah  ")
    fun getQuranbySurahAyahrange(surahid: Int, from: Int, toid: Int): List<QuranEntity?>?


    //


    @Query("SELECT * FROM qurans order by surah,ayah")
    fun allQuranl(): LiveData<List<QuranEntity>>

    @Query("SELECT * FROM qurans where juz=:part")
    fun getQuranbyJuzl(part: Int): LiveData<List<QuranEntity>>

    @Query("SELECT * FROM qurans where surah=:surahid and ayah=:ayahid")
    fun getsurahayahVersesl(surahid: Int, ayahid: Int): List<QuranEntity>

    @Query("SELECT * FROM qurans where surah=:surahid and ayah=:ayahid")
    fun getsurahayahVerseslist(surahid: Int, ayahid: Int): List<QuranEntity>

    @Query("SELECT * FROM qurans where surah=:surahid")
    fun getQuranVersesBySurahl(surahid: Int): List<QuranEntity>

    @Query("SELECT * FROM qurans where surah=:surahid")
    fun getquranbyRoot(surahid: Int): List<QuranEntity>


    @Query("select * from qurans where  surah =:sura and page = :pageno order by ayah")
    fun getAyahsByPagel(sura: Int, pageno: Int): LiveData<List<QuranEntity>>

    @Query("select * from qurans where  surah =:juz and page = :pageno order by ayah")
    fun getAyahsByPagejuzl(juz: Int, pageno: Int): LiveData<List<QuranEntity>>

    @Query("select * from qurans where surah = :surahid and ayah>=:from and ayah<=:toid order by ayah  ")
    fun getQuranbySurahAyahrangel(surahid: Int, from: Int, toid: Int): LiveData<List<QuranEntity>>


    //getQuranbySurahAyahrange
    //select * from qurans where ayah>=50 and ayah<=78 and surah=9

    /*  @Query("select page from qurans where surah = :pos order by ayah limit 1 ")
      fun getSuraStartpage(pos: Int): Int*/


    /* @Query("select * from qurans where docid  between :start and :end")
     fun getVersesByPart(start: Int, end: Int): List<QuranEntity?>?
 */
    /*    @Query("SELECT * FROM qurans where surah=:surahid")
    fun QuranSurahbyid(surahid: Int): Flow<List<QuranEntity>>
    @Query("SELECT * FROM qurans where surah=:surahid")
    fun getTranslation(surahid: Int): List<QuranEntity?>?*/


}