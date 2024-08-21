package database.verbrepo






import androidx.lifecycle.LiveData
import database.Dao.kovDao
import database.Dao.mazeedDao
import database.Dao.mujarradDao
import database.entity.MazeedEntity
import database.entity.MujarradVerbs


//import com.example.mushafconsolidated.Entities.JoinVersesTranslationDataTranslation;
class VerbRepository(
    /* val buckwaterDao: BuckwaterDao,
     val quranVerbsDao: QuranVerbsDao,
     val quranicVerbsDao: QuranicVerbsDao,
     val verbcorpusDao: verbcorpusDao,
     val kovDao: kovDao,
    ,*/
    val mazeeddao: mazeedDao,
    val mujarradao: mujarradDao,
   val kovdao: kovDao,


    ) {

    val kov=  kovdao.getkovlive()
    val mujarradall=  mujarradao.getverbTriAlllive()

      val mazeedall=  mazeeddao.getMazeedAlllive()


    fun getMujarradroot(root : String): LiveData<List<MujarradVerbs>>
            =mujarradao.getverbTrilive(root)

    fun getMujarradWeakness(kov : String): LiveData<List<MujarradVerbs>>
            =mujarradao.getMujarradWeaknesslive(kov)
    suspend fun insertlive(entity : MujarradVerbs) {
        mujarradao.insertlive(entity)
    }



    fun getMazeedroot(root : String): LiveData<List<MazeedEntity>>    =mazeeddao.getMazeedRootlive(root)
    fun getMazeedroolist(root : String): List<MazeedEntity>     =mazeeddao.getMazeedRootlist(root)


   fun getMujarradrootlist(root : String): List<MujarradVerbs>     =mujarradao.getMujarradList(root)
    fun getMazeedWeakness(kov : String): LiveData<List<MazeedEntity>>       =mazeeddao.getMazeedWeaknesslive(kov)
    suspend fun insertlive(entity : MazeedEntity) {
        mazeeddao.insertlive(entity)
    }




}