package database.verbrepo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mushafconsolidated.Utils
import com.example.utility.QuranGrammarApplication
import database.entity.MazeedEntity
import database.entity.MujarradVerbs
import database.entity.kov
import kotlinx.coroutines.launch
import sj.hisnul.entity.hcategoryEnt
import sj.hisnul.entity.hduadetailsEnt
import sj.hisnul.entity.hduanamesEnt

class VerbModel(
    private val newrepository: VerbRepository = VerbGraph.repository
) :ViewModel(){
    private var mazeelist: MutableLiveData<List<MazeedEntity>> = MutableLiveData()
    private var mujarradlist: MutableLiveData<List<MujarradVerbs>> = MutableLiveData()
    private var mazeedall: LiveData<List<MazeedEntity>> = MutableLiveData()
    private var mujaradall: LiveData<List<MujarradVerbs>> = MutableLiveData()
    var duachapter: LiveData<List<hduanamesEnt>> = MutableLiveData()
    val util = Utils(QuranGrammarApplication.context)
    var allduaitem: LiveData<List<hduadetailsEnt>> = MutableLiveData()
    var duacategory: LiveData<List<hcategoryEnt>> = MutableLiveData()

    private var kovs: LiveData<List<kov>> = MutableLiveData()


    fun getMazeelist(root : String): MutableLiveData<List<MazeedEntity>> {
        mazeelist.value= newrepository.getMazeedroolist(root)
        return    mazeelist
    }

    fun getMujjarlist(root : String): MutableLiveData<List<MujarradVerbs>> {
        mujarradlist.value= newrepository.getMujarradrootlist(root)
        return    mujarradlist
    }

    fun getKov(): LiveData<List<kov>> {


        viewModelScope.launch {
            kovs=        newrepository.kov
        }


        return kovs
    }

    fun getMujarradll(): LiveData<List<MujarradVerbs>> {


        viewModelScope.launch {
            mujaradall=        newrepository.mujarradall
        }


        return mujaradall
    }
    fun getMujarradRoot(root : String): LiveData<List<MujarradVerbs>> {


        viewModelScope.launch {
            mujaradall = newrepository.getMujarradroot(root)
        }


        return mujaradall
    }

    fun getMujarradWeakness(root : String): LiveData<List<MujarradVerbs>> {


        viewModelScope.launch {
            mujaradall = newrepository.getMujarradWeakness(root)
        }


        return mujaradall
    }




    fun getMazeedAll(): LiveData<List<MazeedEntity>> {


        viewModelScope.launch {
            mazeedall=        newrepository.mazeedall
        }


        return mazeedall
    }
    fun getMazeedRoot(root : String): LiveData<List<MazeedEntity>> {


        viewModelScope.launch {
            mazeedall = newrepository.getMazeedroot(root)
        }


        return mazeedall
    }
    fun getMazeedRootLIST(root : String): LiveData<List<MazeedEntity>> {


        viewModelScope.launch {
            mazeedall = newrepository.getMazeedroot(root)
        }


        return mazeedall
    }


    fun getMazeedWeakness(root : String): LiveData<List<MazeedEntity>> {


        viewModelScope.launch {
            mazeedall = newrepository.getMazeedWeakness(root)
        }


        return mazeedall
    }




/*    fun update(fav: Int, id: Int) = viewModelScope.launch {

        newrepository.updateFav(fav, id)
    }

    fun DuaItembyId(cat: String): LiveData<List<hduadetailsEnt>> {

        viewModelScope.launch {
            allduaitem = newrepository.getDuaitems(cat)
        }


        return allduaitem
    }*/



}

