package com.example.mushafconsolidated.quranrepo

import android.content.Context
import com.example.mushafconsolidated.QuranAppDatabase

object QuranGraph {

    lateinit var db: QuranAppDatabase
        private set

    val repository by lazy {


        QuranRepository(

            qurandao = db.QuranDao(),
            ssummary = db.surahsummaryDao(),
            chaptersdao = db.AnaQuranChapterDao(),
            mafoolb = db.MafoolBihiDao(),

            jumlahaliya = db.HaliyaDao(),
            tammezent = db.tameezDao(),
            mutlaqent = db.MafoolMutlaqEntDao(),
            liajlihient = db.liajlihiDao(),
            badalErabNotesEnt = db.BadalErabNotesDao(),
            bookm = db.BookMarkDao(),
            hansdao = db.HansDao(),
            lanesdao = db.LaneRootDao(),
            ajlihiworddao = db.liajlihiDao(),
            mutlaqworddao = db.MafoolMutlaqEntDao(),
            tameezword = db.tameezDao(),

            nouncorpusdao = db.NounCorpusDao(),
            mafoolbihi = db.MafoolBihiDao(),
            verbcorpusdao = db.VerbCorpusDao(),
            kanaDao = db.NewKanaDao(),
            shartDAO = db.NewShartDAO(),
            nasbDao = db.NewNasbDao(),
            mousufSifa = db.SifaDao(),
            mudhafDao = db.NewMudhafDao(),
            wbwdao = db.wbwDao(),
            lughatdao = db.LughatDao(),
            grammarrulesDao = db.grammarRulesDao(),


            /*     buckwaterDao = BuckwaterDao(),
            quranVerbsDao = QuranVerbsDao(),
            quranicVerbsDao = QuranicVerbsDao(),
            verbcorpusDao = verbcorpusDao(),
            kovDao = kovDao(),
            mujarradDao = mujarradDao(),
            mazeeddao = mazeedDao(),*/


        )
    }


    fun provide(context: Context) {
        db = QuranAppDatabase.getInstance(context)!!
    }

}