package database.verbrepo

import android.content.Context
import database.VerbDatabase

object VerbGraph {

    lateinit var db:VerbDatabase
        private set

    val repository by lazy {


        VerbRepository(
           mazeeddao = db.mazeedDao(),
            mujarradao=db.mujarradDao(),
            kovdao=db.kovDao()

       /*     buckwaterDao = BuckwaterDao(),
            quranVerbsDao = QuranVerbsDao(),
            quranicVerbsDao = QuranicVerbsDao(),
            verbcorpusDao = verbcorpusDao(),
            kovDao = kovDao(),
            mujarradDao = mujarradDao(),
            mazeeddao = mazeedDao(),*/


        )
    }



    fun provide(context: Context){
        db = VerbDatabase.getInstance(context)!!
    }

}