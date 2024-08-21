package sj.hisnul.newepository

import androidx.lifecycle.LiveData
import com.example.mushafconsolidated.Entities.NamesDetailsDao
import database.Dao.NamesDao
import sj.hisnul.Dao.hDuaCategoryDao
import sj.hisnul.Dao.hDuaItemDao
import sj.hisnul.Dao.hDuaNamesDao
import sj.hisnul.entity.hduadetailsEnt
import sj.hisnul.entity.hduanamesEnt

class NewRepository(

    val hduaItemDao: hDuaItemDao,
    val hduaCategoryDao: hDuaCategoryDao,
    val hduaNamesDao: hDuaNamesDao,
    val namesDetailsDao: NamesDetailsDao,
    val TheNames : NamesDao
) {
    val getduanames=hduaNamesDao.duanameslive()
    val duacateogry=hduaCategoryDao.getcatetory()
    val duaitem=hduaItemDao.getAllDuaItems()
    val allnames =TheNames.ALLAH_NAMES_LISTS()
    fun getNames(TheName : Int)=namesDetailsDao.ALLAH_NAMES_DETAILS_DETAILSL(TheName)

    fun getdualistbychapter(cid: Int): LiveData<List<hduanamesEnt>>
            = hduaNamesDao.getdualistbychapter(cid)


    fun getDunamesbyid(id: String?):   LiveData<List<hduanamesEnt>>
            = hduaNamesDao.getDuanamesid(id)


    fun getDunamesbyCatId(id: String?):   LiveData<List<hduanamesEnt>>
            =hduaNamesDao.getDunamesbyCatId(id!!)


    fun getDunamesbyCatIdnew(id: String?):   LiveData<List<hduanamesEnt>>
            =hduaNamesDao.getDunamesbyCatIdnew(id)


    fun getDuanamesDetails(id: String?):   LiveData<List<hduanamesEnt>>
            =hduaNamesDao.getDuanamesByID(id)


    fun getBookmarked(id: Int):   LiveData<List<hduanamesEnt>>
            =hduaNamesDao.getBookmarked(id)


    fun getIsmarked(id: String?):   LiveData<List<hduanamesEnt>>
            =hduaNamesDao.isBookmarked(id)

    suspend fun updateFav(fav: Int, id: Int) {
        hduaNamesDao.updateFav(fav, id)
    }

    fun getDuaitems(id: String?):   LiveData<List<hduadetailsEnt>>
            = hduaItemDao.getDitem(id)

}