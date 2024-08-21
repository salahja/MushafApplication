package com.example.mushafconsolidated


import android.content.Context
import android.os.AsyncTask
import android.util.Log
import androidx.sqlite.db.SimpleSQLiteQuery
import com.example.mushafconsolidated.DAO.BookMarkDao
import com.example.mushafconsolidated.DAO.BookMarksPojo
import com.example.mushafconsolidated.Entities.BookMarks
import com.example.mushafconsolidated.Entities.ChaptersAnaEntity
import com.example.mushafconsolidated.Entities.CorpusExpandWbwPOJO
import com.example.mushafconsolidated.Entities.CorpusNounWbwOccurance
import com.example.mushafconsolidated.Entities.CorpusVerbWbwOccurance
import com.example.mushafconsolidated.Entities.GrammarRules
import com.example.mushafconsolidated.Entities.NasbListingPojo
import com.example.mushafconsolidated.Entities.NewKanaEntity
import com.example.mushafconsolidated.Entities.NewMudhafEntity
import com.example.mushafconsolidated.Entities.NewNasbEntity
import com.example.mushafconsolidated.Entities.NewShartEntity
import com.example.mushafconsolidated.Entities.NounCorpus
import com.example.mushafconsolidated.Entities.NounCorpusBreakup
import com.example.mushafconsolidated.Entities.Qari
import com.example.mushafconsolidated.Entities.QuranEntity
import com.example.mushafconsolidated.Entities.RootVerbDetails
import com.example.mushafconsolidated.Entities.RootWordDetails
import com.example.mushafconsolidated.Entities.ShartListingPojo
import com.example.mushafconsolidated.Entities.SifaEntity
import com.example.mushafconsolidated.Entities.SifaListingPojo
import com.example.mushafconsolidated.Entities.VerbCorpus
import com.example.mushafconsolidated.Entities.VerbCorpusBreakup
import com.example.mushafconsolidated.Entities.hanslexicon
import com.example.mushafconsolidated.Entities.jsonsurahentity
import com.example.mushafconsolidated.Entities.lanelexicon
import com.example.mushafconsolidated.Entities.lughat
import com.example.mushafconsolidated.Entities.qurandictionary
import com.example.mushafconsolidated.Entities.quranexplorer
import com.example.mushafconsolidated.Entities.wbwentity
import com.example.mushafconsolidated.model.Juz
import com.example.mushafconsolidated.model.QuranCorpusWbw
import database.entity.AllahNames
import mufradat.MufradatEntity
import sj.hisnul.entity.hduanamesEnt


//import com.example.mushafconsolidated.Entities.JoinVersesTranslationDataTranslation;
class Utils {
    private var thiscontext: Context? = null

    constructor(context: Context?) {
        /*   database = context?.let { QuranAppDatabase.Companion.getDatabase(context) }!!
          database= QuranAppDatabase.getDatabase(context)
          thiscontext = context*/

        database = context?.let { QuranAppDatabase.getInstance(it) }!!
        thiscontext = context

    }

    constructor()

    private class deleteWordAsyncTask(private val mAsyncTaskDao: BookMarkDao) :
        AsyncTask<BookMarks?, Void?, Void?>() {


        override fun doInBackground(vararg params: BookMarks?): Void? {
            mAsyncTaskDao.deletebookmark(params[0])
            return null
        }
    }

    fun getMudhafSurahNew(id: Int): List<NewMudhafEntity?>? {
        return database.NewMudhafDao().getMudhafSurah(id)
    }
    fun getShaikhTafseer(id: Int): MutableList<MufradatEntity>? {
        Log.d(TAG, "getShaikhTafseer: started")
        return database.MufradatDao().getShaikTafseer(id)
    }

    fun getQuranCorpusWbwbysurah(id: Int): List<QuranCorpusWbw> {
        return database.QuranDao().getQuranCorpusWbwbysurah(id)
    }

    fun getQuranCorpusWord(id: Int, aid: Int, wid: Int): List<QuranCorpusWbw> {
        return database.QuranDao().getQuranCorpusWbw(id, aid, wid)
    }


    fun getMudhafSurahAyahNew(id: Int, aid: Int): List<NewMudhafEntity>? {
        return database.NewMudhafDao().getMudhafSurahAyah(id, aid)
    }

    fun getAllAnaChapters(): List<ChaptersAnaEntity?>? {

        return database.AnaQuranChapterDao().chapterslist()
    }

    fun getSingleChapter(id: Int): List<ChaptersAnaEntity?>? {
        Log.d(TAG, "getSingleChapter: started")
        return database.AnaQuranChapterDao().getSingleChapters(id)
    }

    fun getQuranRoot(id: Int, verseid: Int, wordid: Int): List<VerbCorpus?>? {
        Log.d(TAG, "getQuranRoot: started")
        return database.VerbCorpusDao().getVerbRootsurahayahwordid(id, verseid, wordid)
    }

    fun getSurahJson(sid:    Int): List<jsonsurahentity?>? {
        Log.d(TAG, "getQuranRoot: started")
        return database.JasonSurahDao().getSurahJson(sid)
    }
    fun getSurahJson(): List<jsonsurahentity> {
        Log.d(TAG, "getQuranRoot: started")
        return database.JasonSurahDao().getSurahJsonall()
    }

    fun getQuranNouns(id: Int, verseid: Int, wordid: Int): List<NounCorpus?>? {
        Log.d(TAG, "getQuranNouns: started")
        return database.NounCorpusDao().getQuranNounsBysurahayahword(id, verseid, wordid)
    }

    fun insertBookMark(entity: BookMarks?) {
        //    database?.BookMarkDao().deleteAllBookMakrs();
        AsyncTask.execute {
            AsyncTask.execute(object : Runnable {
                override fun run() {
                    // and deleting
                    database.BookMarkDao().insertBookmark(entity)
                    //  runOnUiThread(new Runnable() {
                    //  public void run() {
                    //   itemTextView.setText("item deleted");
                    //    }
                    //  });
                }
            })
        }
    }

    fun getCorpusWbwBySurah(tid: Int): List<CorpusExpandWbwPOJO?>? {
        val sqlverb: String =
            ("SELECT CorpusExpand.rootaraone || rootaratwo || rootarathree || rootarafour || rootarafive AS root_a,\n" +
                    "       CorpusExpand.surah,\n" +
                    "       CorpusExpand.ayah,\n" +
                    "       CorpusExpand.wordno,\n" +
                    "       CorpusExpand.wordcount,\n" +
                    "       Qurans.translation,\n" +
                    "       Qurans.passage_no,\n" +
                    "       Qurans.ar_irab_two,\n" +
                    "       Qurans.tafsir_kathir,\n" +
                    "       Qurans.en_transliteration,\n" +
                    "       Qurans.en_jalalayn,\n" +
                    "       Qurans.en_arberry,\n" +
                    "       Qurans.ur_jalalayn,\n" +
                    "       Qurans.ur_junagarhi,\n" +
                    "       CorpusExpand.araone,\n" +
                    "       CorpusExpand.aratwo,\n" +
                    "       CorpusExpand.arathree,\n" +
                    "       CorpusExpand.arafour,\n" +
                    "       CorpusExpand.arafive,\n" +
                    "       CorpusExpand.tagone,\n" +
                    "       CorpusExpand.tagtwo,\n" +
                    "       CorpusExpand.tagthree,\n" +
                    "       CorpusExpand.tagfour,\n" +
                    "       CorpusExpand.tagfive,\n" +
                    "       CorpusExpand.detailsone,\n" +
                    "       CorpusExpand.detailstwo,\n" +
                    "       CorpusExpand.detailsthree,\n" +
                    "       CorpusExpand.detailsfour,\n" +
                    "       CorpusExpand.detailsfive,\n" +
                    "       wbw.en,\n" +
                    "       wbw.bn,\n" +
                    "       wbw.[in],\n" +
                    "       wbw.ur,\n" +
                    "       qurans.qurantext\n" +
                    "  FROM corpusexpand,\n" +
                    "       qurans,\n" +
                    "       wbw\n" +
                    " WHERE CorpusExpand.surah == \""
                    + tid + "\""
                    + "AND \n" +
                    "       corpusexpand.surah = wbw.surah AND \n" +
                    "       corpusexpand.ayah = wbw.ayah AND \n" +
                    "       corpusexpand.wordno = wbw.wordno AND\n" +
                    "       corpusexpand.surah = qurans.surah AND \n" +
                    "       corpusexpand.ayah = qurans.ayah  \n" +
                    " ORDER BY corpusexpand.surah,\n" +
                    "          corpusexpand.ayah")
        val query: SimpleSQLiteQuery = SimpleSQLiteQuery(sqlverb)
        //  List<Book> result = booksDao.getBooks(query);
        return database.RawDao().getCorpusWbw(query)
    }
    fun getJsonstr(tid:Int):List<jsonsurahentity>{
        val sqlshart:String=("select * from surahjson where surah ==  \""
                + tid + "\"")

        val query: SimpleSQLiteQuery = SimpleSQLiteQuery(sqlshart)
        //  List<Book> result = booksDao.getBooks(query);
        return database.RawDao().getJsonstr(query)


    }
    fun getShart(tid:Int):List<ShartListingPojo>{
        val sqlshart:String=("select newshart.surah,newshart.ayah,newshart.indexstart,newshart.indexend,newshart.shartindexstart,newshart.shartindexend,\n" +
                "newshart.jawabshartindexstart,newshart.jawabshartindexend,newshart.harfwordno,newshart.shartstatwordno,newshart.shartendwordno,\n" +
                "newshart.jawabstartwordno,newshart.jawabendwordno,qurans.page,qurans.passage_no,qurans.qurantext,qurans.has_prostration,qurans.translation,\n" +
                "qurans.en_transliteration,qurans.en_arberry,qurans.en_jalalayn,qurans.ur_jalalayn,qurans.tafsir_kathir,qurans.ur_junagarhi,qurans.ar_irab_two\n" +
                " from newshart,qurans where newshart.surah=qurans.surah and newshart.ayah=qurans.ayah and " +
                "newshart.surah ==  \""
                + tid + "\"")

        val query: SimpleSQLiteQuery = SimpleSQLiteQuery(sqlshart)
        //  List<Book> result = booksDao.getBooks(query);
        return database.RawDao().getShartListing(query)


    }
    fun getSifalisting(tid:Int):List<SifaListingPojo>{
        val sqlshart:String=("select sifa.surah,sifa.ayah,sifa.startindex,sifa.endindex,sifa.wordno,qurans.page,qurans.passage_no,qurans.qurantext,qurans.has_prostration,qurans.translation,\n" +
                "qurans.en_transliteration,qurans.en_arberry,qurans.en_jalalayn,qurans.ur_jalalayn,qurans.tafsir_kathir,qurans.ur_junagarhi,qurans.ar_irab_two\n" +
                " from sifa,qurans where sifa.surah=qurans.surah and sifa.ayah=qurans.ayah and sifa.surah ==  \""
                + tid + "\"")

        val query: SimpleSQLiteQuery = SimpleSQLiteQuery(sqlshart)
        //  List<Book> result = booksDao.getBooks(query);
        return database.RawDao().getSifaListing(query)


    }




    fun getNasb(tid:Int):List<NasbListingPojo>{
        val sqlshart:String=("select newnasb.surah,newnasb.ayah,newnasb.indexstart,newnasb.indexend,newnasb.ismstart,newnasb.ismend,\n" +
                "newnasb.khabarstart,newnasb.khabarend,newnasb.harfwordno,newnasb.ismstartwordno,newnasb.ismendwordno,\n" +
                "newnasb.khabarstartwordno,newnasb.khabarendwordno,newnasb.mahdoof,qurans.page,qurans.passage_no,qurans.qurantext,qurans.has_prostration,qurans.translation,\n" +
                "qurans.en_transliteration,qurans.en_arberry,qurans.en_jalalayn,qurans.ur_jalalayn,qurans.tafsir_kathir,qurans.ur_junagarhi,qurans.ar_irab_two\n" +
                " from newnasb,qurans where newnasb.surah=qurans.surah and newnasb.ayah=qurans.ayah and " +
                "newnasb.surah ==  \""
                + tid + "\"")

        val query: SimpleSQLiteQuery = SimpleSQLiteQuery(sqlshart)
        //  List<Book> result = booksDao.getBooks(query);
        return database.RawDao().getNasbListing(query)


    }

    fun getSIfa(tid:Int):List<SifaListingPojo>{
        val sqlshart:String=("select  sifa.surah,sifa.ayah,sifa.startindex,sifa.endindex,sifa.wordno,qurans.en_arberry,qurans.en_jalalayn,qurans.ur_jalalayn,qurans.tafsir_kathir,qurans.ur_junagarhi,qurans.ar_irab_two\n" +
                " from newnasb,qurans where newnasb.surah=qurans.surah and newnasb.ayah=qurans.ayah and " +
                "newnasb.surah ==  \""
                + tid + "\"")

        val query: SimpleSQLiteQuery = SimpleSQLiteQuery(sqlshart)
        //  List<Book> result = booksDao.getBooks(query);
        return database.RawDao().getSifaListing(query)


    }



    fun getConditional(tid: Int): List<CorpusExpandWbwPOJO> {
        val sqlverb: String =
            ("SELECT CorpusExpand.rootaraone || rootaratwo || rootarathree || rootarafour || rootarafive AS root_a,\n" +
                    "       CorpusExpand.surah,\n" +
                    "       CorpusExpand.ayah,\n" +
                    "       CorpusExpand.wordno,\n" +
                    "       CorpusExpand.wordcount,\n" +
                    "       Qurans.translation,\n" +
                    "       Qurans.passage_no,\n" +
                    "       Qurans.ar_irab_two,\n" +
                    "       Qurans.tafsir_kathir,\n" +
                    "       Qurans.en_transliteration,\n" +
                    "       Qurans.en_jalalayn,\n" +
                    "       Qurans.en_arberry,\n" +
                    "       Qurans.ur_jalalayn,\n" +
                    "       Qurans.ur_junagarhi,\n" +
                    "       CorpusExpand.araone,\n" +
                    "       CorpusExpand.aratwo,\n" +
                    "       CorpusExpand.arathree,\n" +
                    "       CorpusExpand.arafour,\n" +
                    "       CorpusExpand.arafive,\n" +
                    "       CorpusExpand.tagone,\n" +
                    "       CorpusExpand.tagtwo,\n" +
                    "       CorpusExpand.tagthree,\n" +
                    "       CorpusExpand.tagfour,\n" +
                    "       CorpusExpand.tagfive,\n" +
                    "       CorpusExpand.detailsone,\n" +
                    "       CorpusExpand.detailstwo,\n" +
                    "       CorpusExpand.detailsthree,\n" +
                    "       CorpusExpand.detailsfour,\n" +
                    "       CorpusExpand.detailsfive,\n" +
                    "       wbw.en,\n" +
                    "       wbw.bn,\n" +
                    "       wbw.[in],\n" +
                    "       wbw.ur,\n" +
                    "       qurans.qurantext\n" +
                    "  FROM corpusexpand,\n" +
                    "       qurans,\n" +
                    "       wbw\n" +
                    " WHERE CorpusExpand.surah == \""
                    + tid + "\""
                    + "AND \n" +
                    "(CorpusExpand.tagone=\"T\" OR CorpusExpand.tagtwo=\"T\" OR CorpusExpand.tagthree=\"T\"\tOR CorpusExpand.tagfour=\"T\" or CorpusExpand.tagfive=\"T\" or CorpusExpand.tagone=\"COND\" OR CorpusExpand.tagtwo=\"COND\" OR CorpusExpand.tagthree=\"COND\"\tOR CorpusExpand.tagfour=\"COND\" or CorpusExpand.tagfive=\"COND\") AND \n"+
                    "       corpusexpand.surah = wbw.surah AND \n" +
                    "       corpusexpand.ayah = wbw.ayah AND \n" +
                    "       corpusexpand.wordno = wbw.wordno AND\n" +
                    "       corpusexpand.surah = qurans.surah AND \n" +
                    "       corpusexpand.ayah = qurans.ayah  \n" +
                    " ORDER BY corpusexpand.surah,\n" +
                    "          corpusexpand.ayah")
        val query: SimpleSQLiteQuery = SimpleSQLiteQuery(sqlverb)
        //  List<Book> result = booksDao.getBooks(query);
        return database.RawDao().getCorpusWbwSurahAyah(query)
    }


    fun getaccusatives(tid: Int): List<CorpusExpandWbwPOJO> {
        val sqlverb: String =
            ("SELECT CorpusExpand.rootaraone || rootaratwo || rootarathree || rootarafour || rootarafive AS root_a,\n" +
                    "       CorpusExpand.surah,\n" +
                    "       CorpusExpand.ayah,\n" +
                    "       CorpusExpand.wordno,\n" +
                    "       CorpusExpand.wordcount,\n" +
                    "       Qurans.translation,\n" +
                    "       Qurans.passage_no,\n" +
                    "       Qurans.ar_irab_two,\n" +
                    "       Qurans.tafsir_kathir,\n" +
                    "       Qurans.en_transliteration,\n" +
                    "       Qurans.en_jalalayn,\n" +
                    "       Qurans.en_arberry,\n" +
                    "       Qurans.ur_jalalayn,\n" +
                    "       Qurans.ur_junagarhi,\n" +
                    "       CorpusExpand.araone,\n" +
                    "       CorpusExpand.aratwo,\n" +
                    "       CorpusExpand.arathree,\n" +
                    "       CorpusExpand.arafour,\n" +
                    "       CorpusExpand.arafive,\n" +
                    "       CorpusExpand.tagone,\n" +
                    "       CorpusExpand.tagtwo,\n" +
                    "       CorpusExpand.tagthree,\n" +
                    "       CorpusExpand.tagfour,\n" +
                    "       CorpusExpand.tagfive,\n" +
                    "       CorpusExpand.detailsone,\n" +
                    "       CorpusExpand.detailstwo,\n" +
                    "       CorpusExpand.detailsthree,\n" +
                    "       CorpusExpand.detailsfour,\n" +
                    "       CorpusExpand.detailsfive,\n" +
                    "       wbw.en,\n" +
                    "       wbw.bn,\n" +
                    "       wbw.[in],\n" +
                    "       wbw.ur,\n" +
                    "       qurans.qurantext\n" +
                    "  FROM corpusexpand,\n" +
                    "       qurans,\n" +
                    "       wbw\n" +
                    " WHERE CorpusExpand.surah == \""
                    + tid + "\""
                    + "AND \n" +
                    "(CorpusExpand.tagone=\"ACC\" OR CorpusExpand.tagtwo=\"ACC\" OR CorpusExpand.tagthree=\"ACC\"\tOR CorpusExpand.tagfour=\"ACC\" or CorpusExpand.tagfive=\"ACC\") AND \n"+
                    "       corpusexpand.surah = wbw.surah AND \n" +
                    "       corpusexpand.ayah = wbw.ayah AND \n" +
                    "       corpusexpand.wordno = wbw.wordno AND\n" +
                    "       corpusexpand.surah = qurans.surah AND \n" +
                    "       corpusexpand.ayah = qurans.ayah  \n" +
                    " ORDER BY corpusexpand.surah,\n" +
                    "          corpusexpand.ayah")
        val query: SimpleSQLiteQuery = SimpleSQLiteQuery(sqlverb)
        //  List<Book> result = booksDao.getBooks(query);
        return database.RawDao().getCorpusWbwSurahAyah(query)
    }

    fun getnounoccuranceHarfNasbZarf(tid: String): List<CorpusNounWbwOccurance?>? {
        val sqlverb: String =
            ("SELECT CorpusExpand.rootaraone || rootaratwo || rootarathree || rootarafour || rootarafive as root_a,\n" +
                    "       CorpusExpand.surah,\n" +
                    "       CorpusExpand.ayah,\n" +
                    "       CorpusExpand.wordno,\n" +
                    "       CorpusExpand.wordcount,\n" +
                    "         Qurans.qurantext,\n" +
                    "       qurans.translation,\n" +
                    "       CorpusExpand.araone,\n" +
                    "       CorpusExpand.aratwo,\n" +
                    "       CorpusExpand.arathree,\n" +
                    "       CorpusExpand.arafour,\n" +
                    "       CorpusExpand.arafive,\n" +
                    "       CorpusExpand.tagone,\n" +
                    "       CorpusExpand.tagtwo,\n" +
                    "       CorpusExpand.tagthree,\n" +
                    "       CorpusExpand.tagfour,\n" +
                    "       CorpusExpand.tagfive,\n" +
                    "       CorpusExpand.detailsone,\n" +
                    "       CorpusExpand.detailstwo,\n" +
                    "       CorpusExpand.detailsthree,\n" +
                    "       CorpusExpand.detailsfour,\n" +
                    "       CorpusExpand.detailsfive,\n" +
                    "       nouncorpus.tag,\n" +
                    "       nouncorpus.propone ,\n" +
                    "       nouncorpus.proptwo,\n" +
                    "       nouncorpus.form,\n" +
                    "       nouncorpus.gendernumber,\n" +
                    "       nouncorpus.type,\n" +
                    "       nouncorpus.cases,\n" +
                    "       wbw.en\n" +
                    "      FROM corpusexpand,nouncorpus,\n" +
                    "       wbw,qurans\n" +
                    "    where   nouncorpus.tag = \""
                    + tid + "\""
                    + "    AND   corpusexpand.surah = wbw.surah AND  corpusexpand.surah = nouncorpus.surah AND \n" +
                    "       corpusexpand.ayah = wbw.ayah AND      corpusexpand.ayah = nouncorpus.ayah AND \n" +
                    "       corpusexpand.wordno = wbw.wordno   AND  corpusexpand.wordno = nouncorpus.wordno " +
                    "and corpusexpand.surah = qurans.surah AND   corpusexpand.ayah = qurans.ayah  order by corpusexpand.surah,corpusexpand.ayah")
        val query: SimpleSQLiteQuery = SimpleSQLiteQuery(sqlverb)
        //  List<Book> result = booksDao.getBooks(query);
        return database.RawDao().getnounoccurance(query)
    }

    fun getVerbOccuranceBreakVerses(tid: String): List<CorpusVerbWbwOccurance?>? {
        val sqlverb: String =
            ("SELECT CorpusExpand.rootaraone || rootaratwo || rootarathree || rootarafour || rootarafive as root_a,\n" +
                    "       CorpusExpand.surah,\n" +
                    "       CorpusExpand.ayah,\n" +
                    "       CorpusExpand.wordno,\n" +
                    "       CorpusExpand.wordcount,\n" +
                    "       Qurans.qurantext,\n" +
                    "       Qurans.translation,\n" +
                    "       Qurans.ur_jalalayn,\n" +
                    "       Qurans.en_jalalayn,\n" +
                    "       Qurans.en_arberry,\n" +
                    "       CorpusExpand.araone,\n" +
                    "       CorpusExpand.aratwo,\n" +
                    "       CorpusExpand.arathree,\n" +
                    "       CorpusExpand.arafour,\n" +
                    "       CorpusExpand.arafive,\n" +
                    "       CorpusExpand.tagone,\n" +
                    "       CorpusExpand.tagtwo,\n" +
                    "       CorpusExpand.tagthree,\n" +
                    "       CorpusExpand.tagfour,\n" +
                    "       CorpusExpand.tagfive,\n" +
                    "       CorpusExpand.detailsone,\n" +
                    "       CorpusExpand.detailstwo,\n" +
                    "       CorpusExpand.detailsthree,\n" +
                    "       CorpusExpand.detailsfour,\n" +
                    "       CorpusExpand.detailsfive,\n" +
                    "       verbcorpus.voice,\n" +
                    "       verbcorpus.form ,\n" +
                    "       verbcorpus.thulathibab,\n" +
                    "       verbcorpus.tense,\n" +
                    "       verbcorpus.gendernumber,\n" +
                    "       verbcorpus.mood_kananumbers,\n" +
                    "       verbcorpus.kana_mood,\n" +
                    "       wbw.en\n" +
                    "      FROM corpusexpand,verbcorpus,\n" +
                    "       wbw,qurans\n" +
                    " WHERE (corpusexpand.tagone = \"V\" OR \n" +
                    "        corpusexpand.tagtwo = \"V\" OR \n" +
                    "        corpusexpand.tagthree = \"V\" OR \n" +
                    "        Corpusexpand.tagfour = \"V\" OR \n" +
                    "        corpusexpand.tagfive = \"V\") AND \n" +
                    "       corpusexpand.lemaraone||corpusexpand.lemaratwo||corpusexpand.lemarathree||corpusexpand.lemarafour||corpusexpand.lemarafive=  \""
                    + tid + "\""
                    + "    AND   corpusexpand.surah = wbw.surah AND  corpusexpand.surah = verbcorpus.chapterno AND \n" +
                    "       corpusexpand.ayah = wbw.ayah AND      corpusexpand.ayah = verbcorpus.verseno AND \n" +
                    "       corpusexpand.wordno = wbw.wordno   AND  corpusexpand.wordno = verbcorpus.wordno and corpusexpand.surah = qurans.surah AND   corpusexpand.ayah = qurans.ayah order by corpusexpand.surah,corpusexpand.ayah")
        val query: SimpleSQLiteQuery = SimpleSQLiteQuery(sqlverb)
        //  List<Book> result = booksDao.getBooks(query);
        return database.RawDao().getVerbOccuranceBreakVerses(query)
    }

    fun getNounOccuranceBreakVerses(tid: String): List<CorpusNounWbwOccurance?>? {
        val sqlverb: String =
            ("SELECT CorpusExpand.rootaraone || rootaratwo || rootarathree || rootarafour || rootarafive as root_a,\n" +
                    "       CorpusExpand.surah,\n" +
                    "       CorpusExpand.ayah,\n" +
                    "       CorpusExpand.wordno,\n" +
                    "       CorpusExpand.wordcount,\n" +
                    "       Qurans.qurantext,\n" +
                    "       qurans.translation,\n" +
                    "       Qurans.ur_jalalayn,\n" +
                    "       Qurans.en_jalalayn,\n" +
                    "       Qurans.en_arberry,\n" +
                    "       CorpusExpand.araone,\n" +
                    "       CorpusExpand.aratwo,\n" +
                    "       CorpusExpand.arathree,\n" +
                    "       CorpusExpand.arafour,\n" +
                    "       CorpusExpand.arafive,\n" +
                    "       CorpusExpand.tagone,\n" +
                    "       CorpusExpand.tagtwo,\n" +
                    "       CorpusExpand.tagthree,\n" +
                    "       CorpusExpand.tagfour,\n" +
                    "       CorpusExpand.tagfive,\n" +
                    "       CorpusExpand.detailsone,\n" +
                    "       CorpusExpand.detailstwo,\n" +
                    "       CorpusExpand.detailsthree,\n" +
                    "       CorpusExpand.detailsfour,\n" +
                    "       CorpusExpand.detailsfive,\n" +
                    "       nouncorpus.tag,\n" +
                    "       nouncorpus.propone ,\n" +
                    "       nouncorpus.proptwo,\n" +
                    "       nouncorpus.form,\n" +
                    "       nouncorpus.gendernumber,\n" +
                    "       nouncorpus.type,\n" +
                    "       nouncorpus.cases,\n" +
                    "       wbw.en\n" +
                    "      FROM corpusexpand,nouncorpus,\n" +
                    "       wbw,qurans\n" +
                    "    where  corpusexpand.lemaraone||corpusexpand.lemaratwo||corpusexpand.lemarathree||corpusexpand.lemarafour||corpusexpand.lemarafive=  \""
                    + tid + "\""
                    + "    AND   corpusexpand.surah = wbw.surah AND  corpusexpand.surah = nouncorpus.surah AND \n" +
                    "       corpusexpand.ayah = wbw.ayah AND      corpusexpand.ayah = nouncorpus.ayah AND \n" +
                    "       corpusexpand.wordno = wbw.wordno   AND  corpusexpand.wordno = nouncorpus.wordno" +
                    " and corpusexpand.surah = qurans.surah AND   corpusexpand.ayah = qurans.ayah order by corpusexpand.surah,corpusexpand.ayah")
        val query: SimpleSQLiteQuery = SimpleSQLiteQuery(sqlverb)
        //  List<Book> result = booksDao.getBooks(query);
        return database.RawDao().getnounoccurance(query)
    }

    fun getRootDetails(tid: String): List<RootWordDetails?>? {
        val sqlverb: String =
            ("SELECT CorpusExpand.araone ||CorpusExpand. aratwo ||CorpusExpand. arathree || CorpusExpand.arafour ||CorpusExpand. arafive as arabic,\n" +
                    "CorpusExpand.lemaraone ||CorpusExpand. lemaratwo ||CorpusExpand. lemarathree || CorpusExpand.lemarafour ||CorpusExpand. lemarafive as lemma,\n" +
                    "CorpusExpand.araone,CorpusExpand.aratwo,CorpusExpand.arathree,CorpusExpand.arafour,CorpusExpand.arafive,\n" +
                    "CorpusExpand.tagone,CorpusExpand.tagtwo,CorpusExpand.tagthree,CorpusExpand.tagfour,CorpusExpand.tagfive,CorpusExpand.wordno,\n" +
                    "CorpusExpand.tagone||\"-\" ||CorpusExpand. tagtwo||\"-\" ||CorpusExpand. tagthree ||\"-\"|| CorpusExpand.tagfour ||CorpusExpand. tagfive as tag,\n" +
                    "       qurandictionary.surah,\n" +
                    "       qurandictionary.ayah,\n" +
                    "       qurandictionary.rootarabic,\n" +
                    "\t   wbw.en,\n" +
                    "\t   chaptersana.abjadname,chaptersana.namearabic,chaptersana.nameenglish \n" +
                    "\t  \n" +
                    " \n" +
                    "      FROM corpusexpand,qurandictionary,wbw,chaptersana\n" +
                    "\t  where  qurandictionary.surah = CorpusExpand.surah AND  qurandictionary.ayah = CorpusExpand.ayah  \n" +
                    "\tand qurandictionary.wordno = CorpusExpand.wordno  AND qurandictionary.surah=wbw.surah and qurandictionary.ayah=wbw.ayah\n" +
                    "and qurandictionary.wordno=wbw.wordno and qurandictionary.surah=chaptersana.chapterid and qurandictionary.rootarabic=  \""
                    + tid + "\"")
        val query: SimpleSQLiteQuery = SimpleSQLiteQuery(sqlverb)
        //  List<Book> result = booksDao.getBooks(query);
        return database.RawDao().getrootdetails(query)
    }

    fun getRootVerbDetails(tid: String): List<RootVerbDetails?>? {
        val sqlverb: String =
            ("SELECT CorpusExpand.araone ||CorpusExpand. aratwo ||CorpusExpand. arathree || CorpusExpand.arafour ||CorpusExpand.arafive as arabic,\n" +
                    "CorpusExpand.lemaraone ||CorpusExpand. lemaratwo ||CorpusExpand. lemarathree || CorpusExpand.lemarafour ||CorpusExpand. lemarafive as lemma,\n" +
                    "CorpusExpand.araone,CorpusExpand.aratwo,CorpusExpand.arathree,CorpusExpand.arafour,CorpusExpand.arafive,\n" +
                    "CorpusExpand.tagone,CorpusExpand.tagtwo,CorpusExpand.tagthree,CorpusExpand.tagfour,CorpusExpand.tagfive,\n" +
                    "       qurandictionary.surah,\n" +
                    "       qurandictionary.ayah,\n" +
                    "       qurandictionary.rootarabic,qurandictionary.wordno,\n" +
                    "\t   wbw.en,\n" +
                    "\t   chaptersana.abjadname,chaptersana.namearabic,chaptersana.nameenglish,\n" +
                    "\t   verbcorpus.form,verbcorpus.thulathibab,verbcorpus.gendernumber,verbcorpus.tense,verbcorpus.voice,verbcorpus.mood_kananumbers,verbcorpus.lemma_a\n" +
                    "\t  \n" +
                    " \n" +
                    "      FROM corpusexpand,qurandictionary,wbw,chaptersana,verbcorpus\n" +
                    "\t  where (CorpusExpand.tagone=\"V\" OR CorpusExpand.tagtwo=\"V\" OR CorpusExpand.tagthree=\"V\" OR CorpusExpand.tagfour=\"V\" \n" +
                    "\t or CorpusExpand.tagfive==\"V\" )and qurandictionary.surah = CorpusExpand.surah AND  qurandictionary.ayah = CorpusExpand.ayah  \n" +
                    "\t \tand qurandictionary.wordno = verbcorpus.wordno  AND qurandictionary.surah=verbcorpus.chapterno and qurandictionary.ayah=verbcorpus.verseno\n" +
                    "\tand qurandictionary.wordno = CorpusExpand.wordno  AND qurandictionary.surah=wbw.surah and qurandictionary.ayah=wbw.ayah\n" +
                    "and qurandictionary.wordno=wbw.wordno and qurandictionary.surah=chaptersana.chapterid and qurandictionary.rootarabic=  \""
                    + tid + "\"")
        val query: SimpleSQLiteQuery = SimpleSQLiteQuery(sqlverb)
        //  List<Book> result = booksDao.getBooks(query);
        return database.RawDao().getverbdetails(query)
    }


    fun getHarfNasbIndexesnew(id: Int): List<NewNasbEntity?>? {
        return database.NewNasbDao().getHarfNasbIndices(id)
    }

    fun getHarfNasbIndicesSurahAyah(id: Int, aid: Int): List<NewNasbEntity?>? {
        return database.NewNasbDao().getHarfNasbIndicesSurahAyah(id, aid)
    }


    //  List<Book> result = booksDao.getBooks(query);
    /*    val mudhaf: List<MudhafPOJO?>?
            get() {
                val sqlverb: String =
                    "select newmudhaf.surah,newmudhaf.ayah,newmudhaf.startindex,newmudhaf.endindex, newmudhaf.comment,qurans.qurantext,qurans.translation from newmudhaf,qurans where \n" +
                            "newmudhaf.surah=qurans.surah and newmudhaf.ayah=qurans.ayah"
                val query: SimpleSQLiteQuery = SimpleSQLiteQuery(sqlverb)
                //  List<Book> result = booksDao.getBooks(query);
                return database.RawDao().getMudhaf(query)
            }

        //     "shart.ayah=qurans.ayah and shart.sharttype=\"laula\" order by shart.surah";
        val tameez: List<TameezPojoList?>?
            get() {
                val sqlverb: String = ("select tameez.surah,tameez.ayah,tameez.wordno,tameez.word \n" +
                        "                 ,qurans.qurantext,qurans.translation from tameez,qurans where tameez.surah=qurans.surah and  \n" +
                        "               tameez.ayah=qurans.ayah  order by tameez.surah,tameez.ayah")
                //     "shart.ayah=qurans.ayah and shart.sharttype=\"laula\" order by shart.surah";
                val query: SimpleSQLiteQuery = SimpleSQLiteQuery(sqlverb)
                //  List<Book> result = booksDao.getBooks(query);
                return database.RawDao().getTameez(query)
            }*/

    fun getSifabySurah(id: Int): List<SifaEntity?>? {
        return database.SifaDao().getSifaindexesBySurah(id)
    }

    fun getSifabySurahAyah(id: Int, aid: Int): List<SifaEntity>? {
        return database.SifaDao().getSifaindexesBySurahAyah(id, aid)
    }

    fun getShartSurahNew(id: Int): List<NewShartEntity?>? {
        return database.NewShartDAO().getShartBySurah(id)
    }

    fun getRootDictionary(id: String): List<lughat?>? {
        return database.LughatDao().getRootWordDictionary(id.trim { it <= ' ' })
    }

    fun getLanesDifinition(id: String): List<lanelexicon?>? {
        return database.LaneDao()?.getLanesDefinition(id.trim { it <= ' ' })
    }

    fun getHansDifinition(id: String): List<hanslexicon?>? {
        return database.HansDao().getHansDefinition(id.trim { it <= ' ' })
    }

    fun getArabicWord(id: String?): List<lughat?>? {
        return database.LughatDao().getArabicWord(id)
    }

    val quranDictionary: List<qurandictionary?>?
        get() {
            return database.qurandictionaryDao().dictionary
        }

    fun getQuranDictionarybyroot(root: String?): List<qurandictionary?>? {
        return database.qurandictionaryDao().getDictionaryroot(root)
    }

    val topicSearchAll: List<quranexplorer>?
        get() {
            return database.QuranExplorerDao()?.aLL
        }

    fun getKananew(id: Int): List<NewKanaEntity>? {
        return database.NewKanaDao().getkanabysurah(id)
    }

    fun getKananewSurahAyah(id: Int, aid: Int): List<NewKanaEntity>? {
        return database.NewKanaDao().getkanabysurahAyah(id, aid)
    }

    fun getShartSurahAyahNew(id: Int, ayah: Int): List<NewShartEntity>? {
        return database.NewShartDAO().getShartBySurahAyah(id, ayah)
    }

    fun getwbwQuranBySurahAyah(id: Int, aid: Int): List<wbwentity>? {
        return database.wbwDao().getwbwQuranBySurahAyah(id, aid)
    }

    fun getwbwQuranbTranslation(
        sid: Int,
        aid: Int,
        firstwordindex: Int,
        lastwordindex: Int,
    ): List<wbwentity>? {
        return database.wbwDao()
            .getwbwQuranbTranslationbyrange(sid, aid, firstwordindex, lastwordindex)
    }

    fun getNounBreakup(tid: String): List<NounCorpusBreakup>? {
        val sqlverb: String =
            ("SELECT count(root_a) as count,id,surah,ayah, lemma_a,form,araword,tag,propone,proptwo FROM nouncorpus where root_a =\""
                    + tid + "\""
                    + " group by lemma_a,root_a,tag,propone,proptwo order by surah,ayah")
        val query: SimpleSQLiteQuery = SimpleSQLiteQuery(sqlverb)
        //  List<Book> result = booksDao.getBooks(query);
        return database.RawDao().getNounBreakup(query)
    }

    fun getVerbBreakUp(tid: String): List<VerbCorpusBreakup?>? {
        val sqlverb: String =
            ("SELECT count(root_a) as count,root_a,lemma_a,form,thulathibab,tense,voice FROM verbcorpus where root_a =\""
                    + tid + "\""
                    + " group by root_a,form order by chapterno,verseno")
        val query: SimpleSQLiteQuery = SimpleSQLiteQuery(sqlverb)
        //  List<Book> result = booksDao.getBooks(query);
        return database.RawDao().getVerbBreakup(query)
    }

    val grammarRules: List<GrammarRules?>?
        get() {
            return database.grammarRulesDao().grammarRules
        }

    fun getQuranbySurah(id: Int): List<QuranEntity>? {
        return database.QuranDao().getQuranVersesBySurah(id)
    }

    fun getVerbBreakUps(id: String): List<VerbCorpus> {
        return database.VerbCorpusDao().getVerbBreakUp(id)
    }

    fun getNounBreakups(id: String): List<NounCorpus> {
        return database.NounCorpusDao().getNounBreakup(id)
    }


    fun getAyahsByPageQuran(surah: Int, pageno: Int): List<QuranEntity?>? {
        return database.QuranDao().getAyahsByPage(surah, pageno)
    }

    fun getsurahayahVerses(id: Int, aid: Int): List<QuranEntity?>? {
        return database.QuranDao().getsurahayahVerses(id, aid)
    }

    fun getQuranbySurahAyahrange(surahid: Int, from: Int, to: Int): List<QuranEntity?>? {
        return database.QuranDao().getQuranbySurahAyahrange(surahid, from, to)
    }

    val names: ArrayList<AllahNames>
        get() {
            return database.NamesDao()?.ALLAH_NAMES_LIST() as ArrayList<AllahNames>
        }


    fun getDuaCATNAMES(tid: String?): List<hduanamesEnt> {
        val verb: String = String.format(
            "select * from hduanames where (category = '%s'   or category like '%%,%s'   or category like '%s, %% 'or category like '%%,%s,%%'" +
                    "", tid, tid, tid, tid
        )
        val fs: String = "$verb)"
        val query: SimpleSQLiteQuery = SimpleSQLiteQuery(fs)
        return database.RawDao().getDuaCATNAMES(query)
    }


    /*

        fun gethDuadetailsitems(id: String?): List<hduadetailsEnt?>? {
            return database.hDuaItemDao().getDitem(id)
        }
    */

    //muslim mate


    //  List<Book> result = booksDao.getBooks(query);
    val collectionC: List<BookMarksPojo?>?
        get() {
            val sql: String =
                "select  count(*) as count, * from bookmark where header != \"pins\" group by header"
            val query: SimpleSQLiteQuery = SimpleSQLiteQuery(sql)
            //  List<Book> result = booksDao.getBooks(query);
            return database.RawDao().getCollectionCount(query)
        }

    //  List<Book> result = booksDao.getBooks(query);
    val juz: List<Juz>
        get() {
            val sql: String =
                "select  a.nameenglish,a.namearabic ,a.chapterid , b. juz, min(b.page) page,b.ayah,b.qurantext from chaptersana a, qurans b where a.chapterid=b.surah group by juz"
            val query: SimpleSQLiteQuery = SimpleSQLiteQuery(sql)
            //  List<Book> result = booksDao.getBooks(query);
            return database.RawDao().getJuz(query)
        }

    val qaris: List<Qari>
        get() {
            return database.QariDao().qaris
        }

    fun getByfirstletter(id: String): List<qurandictionary> {
        return database.qurandictionaryDao().getByfirstletter(id)
    }

    fun getQuranVerbsByfirstletter(id: String): List<VerbCorpus> {
        return database.VerbCorpusDao().getQuranVerbsByfirstletter(id)
    }

    fun getQuranCorpusWbwSurhAyah(cid: Int, aid: Int, wid: Int): List<QuranCorpusWbw> {
        return database.QuranDao().getQuranCorpusWbw(cid, aid, wid)
    }

    companion object {
        private const val TAG: String = "Utils"
        private lateinit var database: QuranAppDatabase
        fun getsurahayahVerses(id: Int, aid: Int): List<QuranEntity?>? {
            return database.QuranDao().getsurahayahVerses(id, aid)
        }


        fun getQuranbySurahAyahrange(surahid: Int, from: Int, to: Int): List<QuranEntity?>? {
            return database.QuranDao().getQuranbySurahAyahrange(surahid, from, to)
        }

        //select * from qurans where ayah>=50 and ayah<=78 and surah=9


        /*
                fun quran() :List<QuranEntity> {
                    return  database.QuranDao().allQuran()

                }

        */
        fun getQuranbyJuz(juz: Int): List<QuranEntity?>? {
            return database.QuranDao().getQuranbyJuz(juz)
        }

        fun getAyahsByPagejuz(juz: Int, pageno: Int): List<QuranEntity?>? {
            return database.QuranDao().getAyahsByPagejuz(juz, pageno)
        }
    }
}


