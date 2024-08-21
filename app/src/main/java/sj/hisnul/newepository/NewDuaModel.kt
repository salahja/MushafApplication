package sj.hisnul.newepository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mushafconsolidated.Utils
import com.example.utility.QuranGrammarApplication
import database.entity.AllahNames
import kotlinx.coroutines.launch
import sj.hisnul.entity.AllahNamesDetails
import sj.hisnul.entity.hcategoryEnt
import sj.hisnul.entity.hduadetailsEnt
import sj.hisnul.entity.hduanamesEnt

class NewDuaModel(
    private val newrepository: NewRepository=Graph.repository
) :ViewModel(){


    private var alldua: LiveData<List<hduanamesEnt>> = MutableLiveData()
    private val duanames: MutableLiveData<List<hduanamesEnt>> = MutableLiveData()
    private var duachapter: LiveData<List<hduanamesEnt>> = MutableLiveData()
    val util = Utils(QuranGrammarApplication.context)
    private var allduaitem: LiveData<List<hduadetailsEnt>> = MutableLiveData()
    private var duacategory: LiveData<List<hcategoryEnt>> = MutableLiveData()
    private var AllahSWT: LiveData<List<AllahNames>> = MutableLiveData()
    private var Namesd: LiveData<List<AllahNamesDetails>> = MutableLiveData()
    fun getNames(): LiveData<List<AllahNames>> {


        viewModelScope.launch {
            AllahSWT=        newrepository.allnames
        }


        return AllahSWT
    }

    fun AllahNames(cat: Int): LiveData<List<AllahNamesDetails>> {

        viewModelScope.launch {
            Namesd= newrepository.getNames(cat)
        }


        return Namesd
    }

    fun loadLists(): LiveData<List<hduanamesEnt>> {


        viewModelScope.launch {
            alldua=        newrepository.getduanames
        }


        return alldua
    }
    fun duaCategory(): LiveData<List<hcategoryEnt>> {


        viewModelScope.launch {
            duacategory = newrepository.duacateogry
        }


        return duacategory
    }
    fun Duacatnames(cat: String): LiveData<List<hduanamesEnt>> {

        viewModelScope.launch {
            duanames.value = util.getDuaCATNAMES(cat)
        }


        return duanames
    }

    fun Duadetailsbychapter(chapter: Int): LiveData<List<hduanamesEnt>> {


        viewModelScope.launch {
            duachapter = newrepository.getdualistbychapter(chapter)
        }

        return duachapter
    }

    fun update(fav: Int, id: Int) = viewModelScope.launch {

        newrepository.updateFav(fav, id)
    }

    fun DuaItembyId(cat: String): LiveData<List<hduadetailsEnt>> {

        viewModelScope.launch {
            allduaitem = newrepository.getDuaitems(cat)
        }


        return allduaitem
    }



}

